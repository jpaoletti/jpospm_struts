/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2010 Alejandro P. Revilla
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

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpos.ee.pm.core.PMCoreConstants;
import org.jpos.ee.pm.core.PMSession;
import org.jpos.ee.pm.core.PresentationManager;
import org.jpos.ee.pm.core.operations.OperationCommandSupport;
import org.jpos.ee.pm.struts.PMEntitySupport;
import org.jpos.ee.pm.struts.PMStrutsConstants;
import org.jpos.ee.pm.struts.PMStrutsContext;

public class GeneralFilter implements Filter, PMCoreConstants, PMStrutsConstants {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("UTF-8");
        /**
         * Optimization to avoid unused connections to data source.
         */
        if (isIgnored(req.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            final PresentationManager pm = PresentationManager.getPm();
            req.setAttribute("pm", pm);
            if (pm == null) {
                chain.doFilter(request, response);
                return;
            }
            final PMEntitySupport o = (PMEntitySupport) req.getSession().getAttribute(ENTITY_SUPPORT);
            if (o == null) {
                PMEntitySupport es = PMEntitySupport.getInstance();
                es.setContext_path(req.getContextPath());
                req.getSession().setAttribute(ENTITY_SUPPORT, es);
            }
            final PMSession pmsession = PMEntitySupport.getPMSession(req);
            final String sessionId = (pmsession != null) ? pmsession.getId() : "";
            final PMStrutsContext ctx = new PMStrutsContext(sessionId);
            req.setAttribute("ctx", ctx);
            ctx.setRequest(req);
            ctx.setResponse((HttpServletResponse) response);
            ctx.getRequest().setAttribute(PM_CONTEXT, ctx);
            
            for (Object object : req.getParameterMap().entrySet()) {
                Map.Entry entry = (Map.Entry) object;
                ctx.put("param_" + entry.getKey(), entry.getValue());
            }

            final Object pmid = ctx.getParameter("pmid");
            ctx.put(OperationCommandSupport.PM_ID, pmid);
            ctx.getRequest().setAttribute("pmid", pmid);

            final Object item = ctx.getParameter("item");
            ctx.put(OperationCommandSupport.PM_ITEM, item);
            ctx.getRequest().setAttribute("item", item);

            try {
                ctx.getPersistenceManager(); // deprecated. Used to back compat
                chain.doFilter(request, response);
            } catch (ServletException e) {
                error(ctx, e);
                throw e;
            } catch (Exception e) {
                error(ctx, e);
            } finally {
                try {
                    ctx.getPersistenceManager().finish(ctx);
                } catch (Exception e) {
                    error(ctx, e);
                }
            }
        }
    }

    protected void error(PMStrutsContext ctx, Exception e) {
        if (ctx.getPresentationManager() != null) {
            ctx.getPresentationManager().error(e);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private boolean isIgnored(String s) {
        if (PresentationManager.getPm() != null) {
            final String[] ignoredExtensions = PresentationManager.getPm().getCfg().get("struts-ignored-extensions", "css,gif,png,jpg,js").split(",");
            for (String ext : ignoredExtensions) {
                if (s.endsWith("." + ext.trim())) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
