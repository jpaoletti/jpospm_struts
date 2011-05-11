package org.jpos.ee.pm.struts.converter;

import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.EntityInstanceWrapper;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.PMContext;

/**
 *
 * @author jpaoletti
 */
public class EditBigStringConverter extends EditStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final EntityInstanceWrapper einstance = (EntityInstanceWrapper) ctx.get(PM_ENTITY_INSTANCE_WRAPPER);
        final Field field = (Field) ctx.get(PM_FIELD);
        Object p = ctx.get(PM_FIELD_VALUE);
        if (p == null) {
            p = getValue(einstance, field);
        }
        final String value = (p!=null)?p.toString():"";
        ctx.put(PM_FIELD_VALUE, value);
        return super.visualize("bigstring_converter.jsp?"
                + "isNull=" + (p == null)
                + "&withNull=" + getConfig("with-null", "false"));
    }
}
