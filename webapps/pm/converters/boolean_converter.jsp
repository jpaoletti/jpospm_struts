<%@include file="../inc/tag-libs.jsp" %>
<logic:equal value="checked" parameter="checked">
<input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" checked /><bean:message key="pm.converter.boolean_converter.yes" />
<input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /> <bean:message key="pm.converter.boolean_converter.no" />
</logic:equal>
<logic:notEqual value="checked" parameter="checked">
<input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><bean:message key="pm.converter.boolean_converter.yes" />
<input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" checked /><bean:message key="pm.converter.boolean_converter.no" />
</logic:notEqual>
