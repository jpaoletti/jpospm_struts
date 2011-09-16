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

import javax.servlet.jsp.JspException;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.Operation;

/**
 * Tag that execute the body only if the field is displayed on the operation
 * 
 * @author jpaoletti
 */
public class DisplaysTag extends PMTags {

    private Field field;
    private Operation operation;
    private String operationId;

    @Override
    public int doStartTag() throws JspException {
        final String display = getField().getDisplay();
        if (display.contains(getOperationId()) || "all".equalsIgnoreCase(display)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String getOperationId() {
        if (getOperation() != null) {
            return getOperation().getId();
        }
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
