<%@include file="../inc/tag-libs.jsp" %>
<logic:equal value="true" parameter="checked">
<input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" checked /><pm:message key="pm.converter.boolean_converter.yes" />
<input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /> <pm:message key="pm.converter.boolean_converter.no" />
<input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" /> <pm:message key="pm.converter.boolean_converter.null" />
</logic:equal>
<logic:equal value="false" parameter="checked">
<input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><pm:message key="pm.converter.boolean_converter.yes" />
<input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" checked /><pm:message key="pm.converter.boolean_converter.no" />
<input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" /> <pm:message key="pm.converter.boolean_converter.null" />
</logic:equal>
<logic:equal value="null" parameter="checked">
<input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><pm:message key="pm.converter.boolean_converter.yes" />
<input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /><pm:message key="pm.converter.boolean_converter.no" />
<input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" checked /> <pm:message key="pm.converter.boolean_converter.null" />
</logic:equal>
