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
package org.jpos.ee.pm.struts.tags;

import javax.servlet.jsp.tagext.TagSupport;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.Highlight;
import org.jpos.ee.pm.core.Operation;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.PaginatedList;
import org.jpos.ee.pm.core.PresentationManager;
import org.jpos.ee.pm.core.operations.OperationScope;
import org.jpos.ee.pm.struts.PMEntitySupport;
import org.jpos.ee.pm.struts.PMStrutsContext;
import org.jpos.util.DisplacedList;

/**
 * Helper tags for Presentation Manager
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version 1.2
 *
 */
public class PMTags extends TagSupport {

    public static String listItemOperations(PMStrutsContext ctx, DisplacedList list, Object item) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("<span style='white-space: nowrap;' class='operationspopup'>");
            for (Operation itemOperation : ctx.getOperations(item, ctx.getOperation()).getOperations()) {
                //If we have permission
                if (ctx.getPMSession().getUser().hasPermission(itemOperation.getPerm())) {
                    //if operation is at item scope
                    if (OperationScope.ITEM.is(itemOperation.getScope())) {
                        String furl = "";
                        if (itemOperation.getUrl() != null) {
                            furl = itemOperation.getUrl();
                        } else {
                            furl = getContextPath() + "/" + itemOperation.getId() + ".do?pmid=" + ctx.getEntity().getId() + "&item=" + list.indexOf(item);
                        }
                        sb.append("<a class='confirmable_");
                        sb.append(itemOperation.getConfirm());
                        sb.append("' href='");
                        sb.append(furl);
                        sb.append("' id='operation");
                        sb.append(itemOperation.getId());
                        sb.append("' title='");
                        sb.append(PresentationManager.getMessage("operation." + itemOperation.getId()));
                        sb.append("'><img src='");
                        sb.append(getContextPath());
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
        } catch (PMException ex) {
            PresentationManager.getPm().error(ex);
            return "?";
        }
    }

    public static String highlight(Entity entity, Field field, Object item, Object field_value) {
        final Highlight highlight = entity.getHighlight(field, item);
        if (highlight != null) {
            return highlight.getStyle();
        } else {
            return "";
        }
    }

    public static String rowNumber(PaginatedList pmlist, Object item) {
        if (pmlist.isShowRowNumber()) {
            return String.format("[%0" + pmlist.getListTotalDigits() + "d]", pmlist.getContents().indexOf(item));
        } else {
            return "";
        }
    }

    /**
     * This method show a tooltip if the key is defined
     * @param key Key
     */
    public static String tooltip(Entity entity, Field field) {
        final String key = "pm.field." + entity.getId() + "." + field.getId() + ".tooltip";
        if (key == null) {
            return "";
        }
        final String message = PresentationManager.getMessage(key);
        if (key.equals(message)) {
            return "";
        }
        return "<img class='tooltip' title='" + message + "' alt='?' src='" + getContextPath() + "/templates/" + getTemplate() + "/img/tooltip.gif' />";
    }

    protected static String getTemplate() {
        return PresentationManager.getPm().getTemplate();
    }

    protected static String getContextPath() {
        return PMEntitySupport.getInstance().getContext_path();
    }
}
