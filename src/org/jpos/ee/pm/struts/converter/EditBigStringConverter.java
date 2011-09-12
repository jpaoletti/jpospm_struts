package org.jpos.ee.pm.struts.converter;

import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.PMContext;

/**
 *
 * @author jpaoletti
 */
public class EditBigStringConverter extends EditStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final Field field = (Field) ctx.get(PM_FIELD);
        Object p = ctx.get(PM_FIELD_VALUE);
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), field);
        }
        final String value = (p!=null)?p.toString():"";
        ctx.put(PM_FIELD_VALUE, value);
        return super.visualize("bigstring_converter.jsp?"
                + "isNull=" + (p == null)
                + "&cols=" + getConfig("cols", "40")
                + "&rows=" + getConfig("rows", "10")
                + "&withNull=" + getConfig("with-null", "false"));
    }
}
