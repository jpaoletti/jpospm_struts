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

import java.util.Collection;
import org.jpos.ee.pm.converter.Converter;
import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.converter.IgnoreConvertionException;

import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.struts.PMEntitySupport;

/**Converter for a collection (1..* aggregation).<br>
 * <pre>
 * {@code
 * <converter class="org.jpos.ee.pm.converter.ShowCollectionConverter">
 *     <operationId>show</operationId>
 * </converter>
 * }
 * </pre>
 * @author J.Paoletti jeronimo.paoletti@gmail.com
 * */
public class ShowCollectionConverter extends Converter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException();
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        try {
            final Collection<?> list = (Collection<?>) getValue(ctx.getEntityInstance(), ctx.getField());
            final StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (Object o : list) {
                sb.append("<li>");
                sb.append(o.toString());
                sb.append("</li>");
            }
            sb.append("</ul>");
            ctx.put(PM_VOID_TEXT, PMEntitySupport.toHtml(sb.toString()));
            return super.visualize("void.jsp?");
        } catch (Exception e1) {
            getPresentationManager().error(e1);
            throw new ConverterException("pm_core.converter.not.collection");
        }
    }
}
