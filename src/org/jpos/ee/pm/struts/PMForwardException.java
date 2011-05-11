package org.jpos.ee.pm.struts;

import org.apache.struts.action.ActionForward;
import org.jpos.ee.pm.core.PMException;

/**
 * Exception that indicates the engine to forward a diferent mapping than
 * success without failing
 * 
 * @author jpaoletti
 */
public class PMForwardException extends PMException {

    private static final long serialVersionUID = 8043873501146882128L;
    private ActionForward actionForward = null;

    /**
     * Constructor
     * 
     * @param key The mapping key to forward
     */
    public PMForwardException(String key) {
        super(key);
    }

    public PMForwardException(ActionForward actionForward) {
        this.actionForward = actionForward;
    }

    public ActionForward getActionForward() {
        return actionForward;
    }

    public void setActionForward(ActionForward actionForward) {
        this.actionForward = actionForward;
    }
}
