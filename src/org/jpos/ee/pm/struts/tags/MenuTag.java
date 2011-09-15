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
import javax.servlet.jsp.JspWriter;
import org.jpos.ee.pm.core.PMSession;
import org.jpos.ee.pm.core.PresentationManager;
import org.jpos.ee.pm.menu.Menu;
import org.jpos.ee.pm.menu.MenuItem;
import org.jpos.ee.pm.menu.MenuList;
import org.jpos.ee.pm.struts.MenuItemContext;

/**
 * Display the menu
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version v1.2
 *
 */
public class MenuTag extends PMTags {

    private PMSession pmsession;

    @Override
    public int doStartTag() throws JspException {
        try {

            pageContext.getOut().println("<div id='menu' class='jqueryslidemenu'>");
            pageContext.getOut().println("<ul>");

            if (pmsession != null && pmsession.getMenu() != null) {
                final MenuList list = (MenuList) pmsession.getMenu();
                for (Menu m : list.getSubmenus()) {
                    printMenu(m, pageContext.getOut());
                }
            }
            pageContext.getOut().println("</ul>");
            pageContext.getOut().println("</div>");
        } catch (Exception ex) {
            throw new JspTagException("MessageTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    protected void printMenu(final Menu m, final JspWriter out) {
        try {
            //Base case
            if (m instanceof MenuItem) {
                final MenuItem item = (MenuItem) m;
                out.print("<li>");
                if (item.getLocation() == null) {
                    out.print("<a href='#'>" + PresentationManager.getMessage(m.getText()) + "</a>");
                } else {
                    final MenuItemContext ctx = (MenuItemContext) item.getLocation().build(item, getContextPath());
                    out.print(ctx.getPrefix());
                    out.print(PresentationManager.getMessage(ctx.getValue()));
                    out.print(ctx.getSufix());
                }
                out.print("</li>");
            } else {
                final MenuList list = (MenuList) m;
                out.print("<li>");
                out.print("<a href='#'>" + PresentationManager.getMessage(m.getText()) + "</a>");
                out.print("<ul>");
                for (Menu sm : list.getSubmenus()) {
                    printMenu(sm, out);
                }
                out.print("</ul>");
                out.print("</li>");
            }
        } catch (Exception e) {
            PresentationManager.getPm().error(e);
        }
    }

    public PMSession getPmsession() {
        return pmsession;
    }

    public void setPmsession(PMSession pmsession) {
        this.pmsession = pmsession;
    }
}
