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
package org.jpos.ee.pm.struts.converter;

import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.EntityContainer;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.struts.PMStrutsContext;

/**
 * Converter for weak entities.
 *
 * Properties:
 *
 * <b>weak-entity</b> Id of the weak entity
 * <b>show-list</b> If true (default) show the list of items
 * <b>show-modify</b> If true (default) show a button to edit screen
 *
 * @author jpaoletti
 */
public class WeakConverter extends StrutsEditConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final String weakEntityId = getConfig("weak-entity");
        final EntityContainer weakContainer = ctx.getEntityContainer(weakEntityId);
        final Entity weak = weakContainer.getEntity();
        final StringBuilder sb = new StringBuilder();
        sb.append("weak_converter.jsp?weakid=");
        sb.append(weakEntityId);
        sb.append("&showlist=");
        sb.append(getConfig("show-list", "true"));
        sb.append("&showbutton=");
        sb.append(getConfig("show-modify", "true"));
        sb.append("&property=");
        sb.append(ctx.getField().getProperty());
        sb.append("&buttontext=");
        sb.append(getConfig("button-text", "pm.struts.weak.converter.edit"));

        ctx.put("weakContainer", weakContainer);
        ctx.put("weak", weak);
        ctx.put("woperation", weak.getOperations().getOperation("list"));
        return super.visualize(sb.toString());
    }
}
