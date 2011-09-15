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

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.Operation;
import org.jpos.ee.pm.core.Operations;
import org.jpos.ee.pm.core.PMSession;
import org.jpos.ee.pm.core.PresentationManager;

/**
 * Display an html div bar with the operations
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version v1.2
 *
 */
public class OperationsTag extends PMTags {

    private boolean labels = true;
    private PMSession pmsession;
    private Entity entity;
    private Operations operations;

    @Override
    public int doStartTag() throws JspException {
        try {
            if (getOperations() != null && getOperations().getOperations() != null && !getOperations().getOperations().isEmpty()) {
                pageContext.getOut().println("<div id='operation_bar'>");
                for (Operation operation : getOperations().getOperations()) {
                    if (getPmsession().getUser().hasPermission(operation.getPerm())) {
                        processOperation(pageContext.getOut(), operation);
                    }
                }
                pageContext.getOut().println("</div>");
            } else {
                pageContext.getOut().print("");
            }
        } catch (Exception ex) {
            throw new JspTagException("OperationsTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    public PMSession getPmsession() {
        return pmsession;
    }

    public void setPmsession(PMSession pmsession) {
        this.pmsession = pmsession;
    }

    private void processOperation(JspWriter out, Operation operation) throws IOException {
        final String opid = operation.getId();

        final String onclick = (operation.getConfirm())
                ? "onclick=\"return confirm('" + PresentationManager.getMessage("pm.operation.confirm.question", "operation." + opid, "pm.entity.${entity.id}") + "');\""
                : "";
        final String style = "background-image:url(" + getContextPath() + "/templates/" + getTemplate() + "/img/" + opid + ".gif);";
        final String hreff = (operation.getUrl() != null)
                ? operation.getUrl()
                : getContextPath() + "/" + opid + ".do";

        out.print("<a href='" + hreff + "' class='button' style=" + style + " id='operation" + opid + "' " + onclick + ">&nbsp;");
        if (isLabels()) {
            out.print(PresentationManager.getMessage("operation." + opid, "pm.entity.${entity.id}"));
        }
        out.println("</a>");
    }

    public boolean isLabels() {
        return labels;
    }

    public void setLabels(boolean labels) {
        this.labels = labels;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
