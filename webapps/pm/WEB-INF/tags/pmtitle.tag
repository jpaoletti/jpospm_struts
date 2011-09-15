<%@tag description="This tag encapsulates a PM title" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "entity" required="true" type="org.jpos.ee.pm.core.Entity" %>
<%@attribute name = "operation" required="true" type="org.jpos.ee.pm.core.Operation"%>
<c:if test="${operation.showTitle}"><pm:title key="pm.entity.${entity.id}" key_operation="operation.${operation.id}" /></c:if>