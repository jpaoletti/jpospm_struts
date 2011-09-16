<%@tag description="This tag creates an standard form" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "contextPath" required="true" type="java.lang.String" %>
<%@attribute name = "entity" required="true" type="org.jpos.ee.pm.core.Entity" %>
<%@attribute name = "operation" required="true" type="org.jpos.ee.pm.core.Operation" %>
<form action="${contextPath}/${operation.id}.do?pmid=${entity.id}"  accept-charset="UTF-8" >
    <input type="hidden" name="finish" value="yes" />
    <fieldset>
        <div class="content">
            <jsp:doBody />
            <input type="submit" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.submit"/>" />
            <input type="reset" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.reset" />" />
        </div>
    </fieldset>
</form>
