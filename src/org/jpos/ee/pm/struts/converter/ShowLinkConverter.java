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
import org.jpos.ee.pm.converter.ShowStringConverter;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.PMContext;

/**
 * Link converter shows a link with the property set in "display" as the text
 * of the link and a reference to another operation (set with "operation" poperty)
 * with an identified referenciation to the given "entity" and the given
 * identified "property".
 *
 * <br>
 * <pre>
 * {@code
 * <converter class="org.jpos.ee.pm.converter.ShowLinkConverter" operations="show list">
 *     <properties>
 *         <property name="entity"    value="other-entity" />
 *         <property name="property"  value="other-entity-id" />
 *         <property name="display"   value="other-entity-showable-prop" />
 *         <property name="operation" value="some-operation-of-other-entity" />
 *     <properties>
 * </converter>
 * }
 * </pre>
 * @author J.Paoletti
 *
 */
public class ShowLinkConverter extends ShowStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final Object einstance = (Object) ctx.get(PM_ENTITY_INSTANCE);
        final Field field = (Field) ctx.get(PM_FIELD);
        final String property = getConfig("property");
        final String display = getConfig("display");
        final Object otherObject = ctx.getPresentationManager().get(einstance, field.getProperty());
        ctx.put("display", ctx.getPresentationManager().getAsString(otherObject, display));
        ctx.put("identified", property + ":" + ctx.getPresentationManager().getAsString(otherObject, property));
        ctx.put("other_entity", getConfig("entity"));
        ctx.put("other_operation", getConfig("operation", "show"));
        return super.visualize("link_converter.jsp?", "");
    }
}
