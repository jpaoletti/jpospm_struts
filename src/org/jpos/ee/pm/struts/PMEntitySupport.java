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
package org.jpos.ee.pm.struts;

import java.util.HashMap;
import java.util.Map;
import org.jpos.ee.pm.core.EntityContainer;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.util.MessageResources;

import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.EntityFilter;
import org.jpos.ee.pm.core.EntitySupport;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.Highlight;
import org.jpos.ee.pm.core.Operation;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMCoreConstants;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.PMSession;
import org.jpos.ee.pm.core.PaginatedList;
import org.jpos.ee.pm.core.PresentationManager;

/**
 * Helper class for internal use.
 * 
 * @author jpaoletti
 * @see EntitySupport
 */
public class PMEntitySupport extends EntitySupport implements PMCoreConstants, PMStrutsConstants {

    private String context_path;
    private static PMEntitySupport instance;
    private HttpServletRequest request;
    public static final Map<String, String> htmlConversions = new HashMap<String, String>();

    /* TODO Externalize this values into a resource */
    static {
        htmlConversions.put("á", "&aacute;");
        htmlConversions.put("é", "&eacute;");
        htmlConversions.put("í", "&iacute;");
        htmlConversions.put("ó", "&oacute;");
        htmlConversions.put("ú", "&uacute;");
        htmlConversions.put("Á", "&Aacute;");
        htmlConversions.put("É", "&Eacute;");
        htmlConversions.put("Í", "&Iacute;");
        htmlConversions.put("Ó", "&Oacute;");
        htmlConversions.put("Ú", "&Uacute;");
        htmlConversions.put("ñ", "&ntilde;");
        htmlConversions.put("Ñ", "&Ntilde;");
        htmlConversions.put("º", "&ordm;");
        htmlConversions.put("ª", "&ordf;");
        htmlConversions.put("ü", "&uuml;");
        htmlConversions.put("Ü", "&Uuml;");
        htmlConversions.put("ç", "&ccedil;");
        htmlConversions.put("Ç", "&Ccedil;");
    }

    /**
     * Singleton getter
     * @return The PMEntitySupport
     */
    public synchronized static PMEntitySupport getInstance() {
        if (instance == null) {
            instance = new PMEntitySupport();
        }
        return instance;
    }

    /**
     * Return the container that is in the given request
     *
     * @param request The request
     * @return The container
     */
    public EntityContainer getContainer() throws PMStrutsException {
        if (request == null) {
            throw new PMStrutsException("request.not.found");
        }
        String pmid = (String) request.getAttribute(PM_ID);
        return getPMSession().getContainer(pmid);
    }

    public PMSession getPMSession() throws PMStrutsException {
        if (request == null) {
            throw new PMStrutsException("request.not.found");
        }
        return (PMSession) request.getSession().getAttribute(PMSESSION);
    }

    /**
     * Inserts the container entity into the request
     *
     * @param request The request
     * @return The entity
     * @throws PMStrutsException when the request was not setted
     */
    public Entity getEntity() throws PMStrutsException {
        EntityContainer container = getContainer();
        if (container != null) {
            return container.getEntity();
        }
        return null;
    }

    /**
     * Inserts the container list into the request
     * @param request The request
     * @return The list
     * @throws PMStrutsException when request has no container
     */
    public PaginatedList getList() throws PMStrutsException {
        EntityContainer container = getContainer();
        if (container == null) {
            throw new PMStrutsException("container.not.found");
        }
        PaginatedList list = container.getList();
        return list;
    }

    /**
     * Insert the container selected instance into the request
     * @param request The request
     * @return The list
     * @throws PMStrutsException when request has no container
     */
    public Object getSelected() throws PMStrutsException {
        EntityContainer container = getContainer();
        if (container == null) {
            throw new PMStrutsException("container.not.found");
        }
        Object r = container.getSelected().getInstance();
        return r;
    }

    /**
     * Returns the filter applied
     * 
     * @return The filter
     * @throws PMStrutsException when request has no container
     */
    public EntityFilter getFilter() throws PMStrutsException {
        EntityContainer container = getContainer();
        if (container == null) {
            throw new PMStrutsException("container.not.found");
        }
        return container.getFilter();
    }

    /**
     * Setter for context path
     *
     * @param context_path The context_path
     */
    public void setContext_path(String context_path) {
        this.context_path = context_path;
    }

    /**
     * Getter for context path
     * 
     * @return The context_path
     */
    public String getContext_path() {
        return context_path;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Integer getListTotalDigits() {
        try {
            return (getList().getTotal() == null || getList().getTotal() == 0) ? 1 : (int) Math.log10(getList().getTotal()) + 1;
        } catch (PMStrutsException ex) {
            return 0;
        }
    }

    public String getWelcomePage() {
        return PresentationManager.getPm().getCfg().get("welcome-page", "pages/welcome.jsp");
    }

    public String getNavigationList(final EntityContainer container) {
        final StringBuilder sb = new StringBuilder();
        if (container != null && container.getSelected() != null) {
            sb.append(getNavigationList(container.getOwner()));
            sb.append("&nbsp; &gt; &nbsp;");
            sb.append("<a href='");
            sb.append(getContext_path());
            sb.append("/");
            sb.append(container.getOperation().getId());
            sb.append(".do?pmid=");
            sb.append(container.getEntity().getId()).append("' >");
            final Object inst = container.getSelected().getInstance();
            if (inst == null) {
                sb.append("");
            } else {
                sb.append(toHtml(inst.toString()));
            }
            sb.append("</a>");
        }
        return sb.toString();
    }

    public String getListItemOperations(final PMStrutsContext ctx, MessageResources messages, Object item, int i) throws PMException {
        final StringBuilder sb = new StringBuilder();
        sb.append("<span style='white-space: nowrap;' class='operationspopup'>");
        for (Operation itemOperation : ctx.getOperations(item, ctx.getOperation()).getOperations()) {
            //If we have permission
            if (ctx.getPMSession().getUser().hasPermission(itemOperation.getPerm())) {
                //if operation is at item scope
                if (Operation.SCOPE_ITEM.equals(itemOperation.getScope())) {
                    String furl = "";
                    if (itemOperation.getUrl() != null) {
                        furl = itemOperation.getUrl();
                    } else {
                        furl = getContext_path() + "/" + itemOperation.getId() + ".do?pmid=" + ctx.getEntity().getId() + "&item=" + i;
                    }
                    sb.append("<a class='confirmable_");
                    sb.append(itemOperation.getConfirm());
                    sb.append("' href='");
                    sb.append(furl);
                    sb.append("' id='operation");
                    sb.append(itemOperation.getId());
                    sb.append("' title='");
                    sb.append(messages.getMessage("operation." + itemOperation.getId()));
                    sb.append("'><img src='");
                    sb.append(getContext_path());
                    sb.append("/templates/");
                    sb.append(ctx.getPresentationManager().getTemplate());
                    sb.append("/img/").append(itemOperation.getId());
                    sb.append(".gif' alt='");
                    sb.append(itemOperation.getId());
                    sb.append("' /></a>");
                }
            }
        }
        sb.append("&nbsp;</span>");
        return sb.toString();
    }

    public PMContext prepareForConversion(Field field, Object item, Object field_value) {
        final PMContext ctx = (PMContext) request.getAttribute(PM_CONTEXT);
        ctx.put(PM_FIELD, field);
        if (field_value != null) {
            ctx.put(PM_FIELD_VALUE, field_value);
        } else {
            ctx.put(PM_FIELD_VALUE, ctx.getPresentationManager().get(item, field.getProperty()));
        }
        ctx.setEntityInstance(item);
        ctx.put(PM_EXTRA_DATA, "");
        request.setAttribute("ctx", ctx);
        return ctx;
    }

    public String getHighlight(Entity entity, Field field, Object item, Object field_value) {
        final Highlight highlight = entity.getHighlight(field, item);
        if (highlight != null) {
            return highlight.getStyle();
        } else {
            return "";
        }
    }

    public static String toHtml(final String s) {
        if (s == null) {
            return null;
        }
        if (PresentationManager.getPm().getCfg().getBoolean("html-convert", true)) {
            String tmp = s;
            for (Map.Entry<String, String> entry : htmlConversions.entrySet()) {
                tmp = tmp.replace(entry.getKey(), entry.getValue());
            }
            return tmp;
        } else {
            return s;
        }
    }

    /**
     * This method show a tooltip if the key is defined
     * @param key Key
     */
    public static String getTooltip(final String key) {
        if (key == null) {
            return "";
        }
        final String message = PresentationManager.getMessage(key);
        if (key.equals(message)) {
            return "";
        }
        return "<img class='tooltip' title='" + message + "' alt='?' src='" + getInstance().getContext_path() + "/templates/" + getInstance().getPM().getTemplate() + "/img/tooltip.gif' />";
    }

    /**
     * Getter for PMSession from http session
     */
    public static PMSession getPMSession(final HttpServletRequest request) {
        return (PMSession) request.getSession().getAttribute(PMSESSION);
    }
}
