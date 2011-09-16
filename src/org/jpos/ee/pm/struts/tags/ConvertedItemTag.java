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
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.EntityContainer;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.Operation;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.struts.PMEntitySupport;

/**
 * Tag for converted item
 * 
 * @author jpaoletti
 */
public class ConvertedItemTag extends PMTags {

    private PMContext ctx;
    private EntityContainer entityContainer;
    private Field field;
    private Operation operation;
    private Object item;
    private Object fieldValue;
    //old context values
    private EntityContainer oldEntityContainer;
    private Field oldField;
    private Object oldFieldValue;
    private Object oldEntityInstance;

    @Override
    public int doStartTag() throws JspException {
        try {
            final PMEntitySupport es = PMEntitySupport.getInstance();
            final String fieldId = getField().getId();
            try {
                prepareContext();
                final String highlight = highlight(getEntity(), getField(), getItem(), getFieldValue());
                print("<div id='f_" + fieldId + "_div' class='cell " + highlight + "'>");
                pageContext.include("../converters/"
                        + getField().visualize(getCtx(), getOperation(), getEntity())
                        + "&f=" + fieldId);

                println("</div>", "<span class='field_message_container_" + getEntity().getId() + "_" + fieldId + "' />");
            } catch (Exception e) {
                es.getPM().error(e);
                println("<img width='16px' src='" + getContextPath() + "/templates/" + getTemplate() + "/images/m_error.png' alt='error' />");
            }
        } catch (IOException ex) {
            throw new JspTagException("ConvertedItemTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    protected void prepareContext() throws PMException {
        oldEntityContainer = getCtx().getEntityContainer(true);
        oldField = getCtx().getField();
        oldFieldValue = getCtx().getFieldValue();
        oldEntityInstance = getCtx().getEntityInstance();

        getCtx().setEntityContainer(getEntityContainer());
        getCtx().setField(getField());
        getCtx().setFieldValue(getFieldValue());
        getCtx().setEntityInstance(getItem());
    }

    @Override
    public int doEndTag() {
        getCtx().setEntityContainer(oldEntityContainer);
        getCtx().setField(oldField);
        getCtx().setFieldValue(oldFieldValue);
        getCtx().setEntityInstance(oldEntityInstance);
        return EVAL_PAGE;
    }

    public EntityContainer getEntityContainer() throws PMException {
        if (entityContainer == null) {
            return getCtx().getEntityContainer();
        }
        return entityContainer;
    }

    public void setEntityContainer(EntityContainer entityContainer) {
        this.entityContainer = entityContainer;
    }

    public Object getFieldValue() throws PMException {
        if (fieldValue == null) {
            return getCtx().getPresentationManager().get(getItem(), getField().getProperty());
        }
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getItem() throws PMException {
        if (item == null) {
            if (getCtx().getSelected() != null) {
                return getCtx().getSelected().getInstance();
            }
        }
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Operation getOperation() {
        if (operation == null) {
            return getCtx().getOperation();
        }
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    private Entity getEntity() throws PMException {
        return getEntityContainer().getEntity();
    }

    public PMContext getCtx() {
        return ctx;
    }

    public void setCtx(PMContext ctx) {
        this.ctx = ctx;
    }
}
