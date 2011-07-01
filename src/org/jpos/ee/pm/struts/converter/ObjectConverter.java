package org.jpos.ee.pm.struts.converter;

import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMException;

/**Converter for integer <br>
 * <pre>
 * {@code
 * <converter class="org.jpos.ee.pm.converter.ObjectConverter">
 *     <operationId>edit</operationId>
 *         <properties>
 *             <property name="entity"          value="other_entity" />
 *             <property name="id"              value="other_entity_id" />
 *             <property name="display"         value="other_entity_display" />
 *             <property name="with-null"       value="true" />
 *             <property name="filter"          value="org.jpos.ee.pm.core.ListFilterXX" />
 *             <property name="sort-field"      value="xxx" /> NOT IMPLEMENTED!
 *             <property name="sort-direction"  value="asc | desc" /> NOT IMPLEMENTED!
 *             <property name="min-search-size" value="0" />
 *         </properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ObjectConverter extends StrutsEditConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final String _id = getConfig("id");
            final String _entity = getConfig("entity");
            final Entity entity = ctx.getPresentationManager().getEntity(_entity);
            final String newFieldValue = ctx.getString(PM_FIELD_VALUE);
            if (newFieldValue == null || newFieldValue.trim().compareTo("-1") == 0) {
                return null;
            }
            //I don't like this, it need a review
            ctx.put(PM_ENTITY, entity);
            final Object res = entity.getDataAccess().getItem(ctx, _id, newFieldValue);
            ctx.put(PM_ENTITY, null);
            return res;
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
    }

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final String _id = getConfig("id");
        final String _display = getConfig("display");
        final Object fieldValue = ctx.get(PM_FIELD_VALUE);
        if (fieldValue == null) {
            ctx.put("_selected_value", "");
            ctx.put("_selected_id", "-1");
            ctx.put("_with_null", false); //false because selected is already null
        } else {
            ctx.put("_selected_value", ctx.getPresentationManager().getAsString(fieldValue, _display));
            ctx.put("_selected_id", ctx.getPresentationManager().getAsString(fieldValue, _id));
            ctx.put("_with_null", getConfig("with-null", "false"));
        }
        ctx.put("_min_search_size", getConfig("min-search-size", "0"));
        ctx.put("_entity", getConfig("entity"));
        ctx.put("_id", _id);
        ctx.put("_display", _display);
        ctx.put("_filter", getConfig("filter"));
        return super.visualize("object_converter.jsp?");
    }
}
