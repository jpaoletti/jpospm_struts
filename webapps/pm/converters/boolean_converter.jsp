<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.checked == 'checked'}">
    <input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" checked /><pm:message key="pm.converter.boolean_converter.yes" />
    <input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /> <pm:message key="pm.converter.boolean_converter.no" />
</c:if>
<c:if test="${param.checked != 'checked'}">
    <input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><pm:message key="pm.converter.boolean_converter.yes" />
    <input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" checked /><pm:message key="pm.converter.boolean_converter.no" />
</c:if>
