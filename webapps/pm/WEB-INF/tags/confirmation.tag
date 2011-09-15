<%@tag description="This tag encapsulates a PM title" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "entity" required="true" type="org.jpos.ee.pm.core.Entity"%>
<%@attribute name = "operation" required="true" type="org.jpos.ee.pm.core.Operation"%>
<c:if test="${operation.confirm}">
    <p id="q_${operation.id}" style="display: none;" >
        <pmfn:message key="pm.operation.confirm.question" arg0="operation.${operation.id}" arg1="pm.entity.${entity.id}" />
    </p>
    <bean:define id="onclick" value="onclick=\"return confirm($('#q_${operation.id}').html().trim());\" " toScope="request"/>
</c:if>