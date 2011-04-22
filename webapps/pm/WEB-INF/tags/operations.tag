<%@ tag description="This tag shows the operations" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name="labels" required="false" type="java.lang.Boolean" %>
<%@attribute name="operations" required="false" type="java.util.List" %>
<% if(operations!=null && operations.size() > 0){ %>
<div id="operation_bar">
<logic:iterate id="operation" name="operations" type="org.jpos.ee.pm.core.Operation" >
    <%
    final org.jpos.ee.pm.core.PMSession pmsession = (org.jpos.ee.pm.core.PMSession) session.getAttribute("pmsession");
    if(pmsession.getUser().hasPermission(operation.getPerm())) {
    %>
	<pm:operation operation="${operation}" labels="${labels}" />
<% } %>
</logic:iterate>
</div>
<% } %>