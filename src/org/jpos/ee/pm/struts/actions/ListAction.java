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

import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.operations.ListOperation;
import org.jpos.ee.pm.struts.PMStrutsContext;

public class ListAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("order", ctx.getParameter("order"));
        ctx.put("desc", ctx.getParameter("desc") != null && ctx.getParameter("order").equals("true"));
        ctx.put("page", (ctx.getParameter("page") == null) ? null : Integer.parseInt((String) ctx.getParameter("page")));
        ctx.put("rowsPerPage", (ctx.getParameter("rowsPerPage") == null) ? null : Integer.parseInt((String) ctx.getParameter("rowsPerPage")));
        ListOperation op = new ListOperation("list");
        op.execute(ctx);
    }
}
