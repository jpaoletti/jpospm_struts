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
import javax.servlet.jsp.JspTagException;
import org.jpos.ee.pm.core.EntityContainer;
import org.jpos.ee.pm.struts.PMEntitySupport;

/**
 * Navigation list tag
 * 
 * @author jpaoletti
 */
public class NavigationTag extends PMTags {

    private EntityContainer container;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getNavigationList(getContainer()));
        } catch (Exception ex) {
            throw new JspTagException("NavigationTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public String getNavigationList(final EntityContainer c) {
        final StringBuilder sb = new StringBuilder();
        if (c != null && c.getSelected() != null) {
            sb.append(getNavigationList(c.getOwner()));
            sb.append("&nbsp; &gt; &nbsp;");
            sb.append("<a href='");
            sb.append(getContextPath());
            sb.append("/");
            sb.append(c.getOperation().getId());
            sb.append(".do?pmid=");
            sb.append(c.getEntity().getId()).append("' >");
            final Object inst = c.getSelected().getInstance();
            if (inst == null) {
                sb.append("");
            } else {
                sb.append(PMEntitySupport.toHtml(inst.toString()));
            }
            sb.append("</a>");
        }
        return sb.toString();
    }

    public EntityContainer getContainer() {
        return container;
    }

    public void setContainer(EntityContainer container) {
        this.container = container;
    }
}
