/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2011 Alejandro P. Revilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpos.ee.pm.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jpos.ee.pm.core.PMCoreConstants;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.exception.NotAuthorizedException;
import org.jpos.ee.pm.core.PresentationManager;
import org.jpos.ee.pm.core.exception.NotAuthenticatedException;
import org.jpos.ee.pm.core.message.MessageFactory;
import org.jpos.ee.pm.struts.PMEntitySupport;
import org.jpos.ee.pm.struts.PMForwardException;
import org.jpos.ee.pm.struts.PMStrutsConstants;
import org.jpos.ee.pm.struts.PMStrutsContext;
import org.jpos.ee.pm.struts.PMStrutsService;

/**
 * A super class for all actions with some helpers and generic stuff
 *
 * @author jpaoletti
 */
public abstract class ActionSupport extends Action implements PMCoreConstants, PMStrutsConstants {

    protected abstract void doExecute(PMStrutsContext ctx) throws PMException;

    /**Forces execute to check if any user is logged in*/
    protected boolean checkUser() {
        return true;
    }

    protected boolean prepare(PMStrutsContext ctx) throws PMException {
        if (checkUser() && ctx.getPmsession() == null) {
            //Force logout
            final PMEntitySupport es = PMEntitySupport.getInstance();
            ctx.getSession().invalidate();
            es.setContext_path(ctx.getRequest().getContextPath());
            ctx.getSession().setAttribute(ENTITY_SUPPORT, es);
            ctx.getRequest().setAttribute("reload", 1);
            throw new NotAuthenticatedException();
        }
        return true;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PMStrutsContext ctx = (PMStrutsContext) request.getAttribute(PM_CONTEXT);
        ctx.setMapping(mapping);
        ctx.setForm(form);
        try {
            boolean step = prepare(ctx);
            if (step) {
                excecute(ctx);
                if (ctx.getOperation() != null && ctx.getOperation().getFollows() != null) {
                    return new ActionForward("/" + ctx.getOperation().getFollows() + ".do");
                }
            }
            return mapping.findForward(SUCCESS);
        } catch (PMForwardException e) {
            if (e.getActionForward() != null) {
                return e.getActionForward();
            } else {
                return mapping.findForward(e.getKey());
            }
        } catch (NotAuthenticatedException e) {
            return ctx.fwdLogin();
        } catch (NotAuthorizedException e) {
            return ctx.fwdDeny();
        } catch (PMException e) {
            ctx.getPresentationManager().debug(this, e);
            if (e.getKey() != null) {
                ctx.addMessage(MessageFactory.error(e.getKey()));
            }
            return mapping.findForward(FAILURE);
        }
    }

    protected void excecute(PMStrutsContext ctx) throws PMException {
        doExecute(ctx);
    }

    protected PMStrutsService getPMService() throws PMException {
        try {
            return (PMStrutsService) PresentationManager.getPm().getService();
        } catch (Exception e) {
            throw new PMException();
        }
    }
}
