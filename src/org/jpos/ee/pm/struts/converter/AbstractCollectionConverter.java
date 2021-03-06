package org.jpos.ee.pm.struts.converter;

import java.util.List;
import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.ListFilter;
import org.jpos.ee.pm.core.ListSort;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public abstract class AbstractCollectionConverter extends StrutsEditConverter {

    public void saveList(PMStrutsContext ctx, String eid) {
        try {
            ctx.getSession().setAttribute(ctx.getTmpName(), getList(ctx, null));
        } catch (PMException ex) {
            ctx.getPresentationManager().error(ex);
        }
    }

    public static List<?> recoverList(PMStrutsContext ctx, String eid, boolean remove) {
        try {
            final List<?> r = (List<?>) ctx.getSession().getAttribute(ctx.getTmpName());
            if (remove) {
                ctx.getSession().removeAttribute(ctx.getTmpName());
            }
            return r;
        } catch (PMException ex) {
            ctx.getPresentationManager().error(ex);
            return null;
        }
    }

    public List<?> getList(PMStrutsContext ctx, String entity_id) throws PMException {
        final String filter = getConfig("filter");
        final String eid = (entity_id == null) ? getConfig("entity") : entity_id;
        final String sortField = getConfig("sord-field");
        final String sortd = getConfig("sord-direction");
        final ListSort sort = new ListSort(sortField, (sortd != null) ? ListSort.SortDirection.ASC : ListSort.SortDirection.DESC);

        ListFilter lfilter = null;
        if (filter != null && filter.compareTo("null") != 0 && filter.compareTo("") != 0) {
            lfilter = (ListFilter) ctx.getPresentationManager().newInstance(filter);
        }
        Entity e = ctx.getPresentationManager().getEntity(eid);
        List<?> list = null;
        if (e == null) {
            throw new ConverterException("Cannot find entity " + eid);
        }
        synchronized (e) {
            ListFilter tmp = e.getListfilter();
            e.setListfilter(lfilter);
            list = e.getList(ctx, null, sort, null, null);
            e.setListfilter(tmp);
        }
        return list;
    }
}
