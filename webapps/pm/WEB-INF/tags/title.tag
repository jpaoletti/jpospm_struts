<%@tag description="This tag encapsulates a title" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "key" required="true" type="java.lang.String" %>
<%@attribute name = "key_operation" required="false" type="java.lang.String" %>
<h2 class="title">
    <a href="javascript:history.back();" title="<bean:message key="pm.title.back"/>">
        <img alt="<bean:message key="pm.title.back"/>"
             src="${es.context_path}/templates/${pm.template}/img/arrow_back.gif"
             style="vertical-align:middle;"/></a>
    &nbsp;<pm:message key="${key}" />&nbsp;
    <c:if test="${not empty key_operation}">(<pm:message key="${key_operation}" />)</c:if>
    <a href="javascript:location.reload(true)" title="<bean:message key="pm.title.refresh"/>">
            <img alt="<bean:message key="pm.title.refresh"/>"
             src="${es.context_path}/templates/${pm.template}/img/reload.gif"
             style="vertical-align:middle;"/></a>
</h2>