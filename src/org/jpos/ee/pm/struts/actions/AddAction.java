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

import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.operations.AddOperation;
import org.jpos.ee.pm.struts.PMForwardException;
import org.jpos.ee.pm.struts.PMStrutsContext;

public class AddAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("editable", true);
        final boolean finish = ctx.getParameter("finish") == null;
        if (finish) {
            ctx.put("clean_selected", true);
            ctx.put("validate", false);
        }

        final AddOperation op = new AddOperation("add");
        op.execute(ctx);

        if (finish) {
            throw new PMForwardException(CONTINUE);
        }
    }
}
