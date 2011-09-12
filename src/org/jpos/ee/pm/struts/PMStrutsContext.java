package org.jpos.ee.pm.struts;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jpos.ee.pm.core.Field;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMCoreConstants;
import org.jpos.ee.pm.core.PMException;

/**An extension of the org.jpos.ee.pm.core.PMContext class with some helpers
 * for PMStruts.*/
public class PMStrutsContext extends PMContext implements PMCoreConstants, PMStrutsConstants {

    public PMStrutsContext(String sessionId) {
        super(sessionId);
    }

    /**
     * @return the mapping
     */
    public ActionMapping getMapping() {
        return (ActionMapping) get(PM_MAPPINGS);
    }

    /**
     * @param mapping the mapping to set
     */
    public void setMapping(ActionMapping mapping) {
        put(PM_MAPPINGS, mapping);
    }

    /**
     * @return the form
     */
    public ActionForm getForm() {
        return (ActionForm) get(PM_ACTION_FORM);
    }

    /**
     * @param form the form to set
     */
    public void setForm(ActionForm form) {
        put(PM_ACTION_FORM, form);
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) get(PM_HTTP_REQUEST);
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {
        put(PM_HTTP_REQUEST, request);
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return (HttpServletResponse) get(PM_HTTP_RESPONSE);
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        put(PM_HTTP_RESPONSE, response);
    }

    /* ActionForwards Helpers */
    /**
     * Helper for success action forward
     * @return success action forward
     */
    public ActionForward successful() {
        return getMapping().findForward(SUCCESS);
    }

    /**
     * Helper for continue action forward
     * @return continue action forward
     */
    public ActionForward go() {
        return getMapping().findForward(CONTINUE);
    }

    /**
     * Helper for deny action forward
     * @return deny action forward
     */
    public ActionForward deny() {
        return getMapping().findForward(DENIED);
    }

    /**
     * Retrieve the http session
     * @return The session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Getter for the entity support helper object
     * @return The entity support
     */
    public PMEntitySupport getEntitySupport() {
        PMEntitySupport r = (PMEntitySupport) getRequest().getSession().getAttribute(ENTITY_SUPPORT);
        return r;
    }

    private String getPmId() {
        return (String) getRequest().getAttribute(PM_ID);
    }

    public String getTmpName() throws PMException {
        Field field = (Field) get(PM_FIELD);
        return "tmp_" + getEntity().getId() + "_" + field.getId();
    }

    public List<?> getTmpList() {
        try {
            final List<?> r = (List<?>) getSession().getAttribute(getTmpName());
            return r;
        } catch (PMException ex) {
            getPresentationManager().error(ex);
            return null;
        }
    }
}
