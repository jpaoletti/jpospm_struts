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

import org.jpos.ee.pm.converter.Converter;
import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.struts.PMEntitySupport;

/**
 *
 * @author jpaoletti
 */
public class DefaultStrutsConverter extends Converter {

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        Object s = ctx.getFieldValue();
        if (s != null && s instanceof String && (s.toString().contains(".jsp?") || s.toString().contains(".do?"))) {
            return s;
        } else {
            if (s == null) {
                s = "";
            }
            ctx.put(PM_VOID_TEXT, PMEntitySupport.toHtml(s.toString()));
            return "void.jsp?";
        }
    }
}
