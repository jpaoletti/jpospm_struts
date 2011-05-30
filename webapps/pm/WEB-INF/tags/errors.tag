<%@tag description="Custom error tag" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<c:if test="${not empty ctx.errors}">
    <div class='m_error'>
        <logic:iterate id="e" name="ctx" property="errors">
            - <pm:message key="${e.message}" arg0="${e.arg0}" arg1="${e.arg1}" arg2="${e.arg2}" arg3="${e.arg3}" /><br/>
        </logic:iterate>
    </div>
</c:if>