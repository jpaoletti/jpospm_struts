<%@include file="../inc/tag-libs.jsp" %>
<bean:define id="listv"      name="ctx"    property="map.PM_FIELD_VALUE" type="java.util.Collection" />
<bean:define id="collection" name="ctx"    property="tmpList" type="java.util.List" />
<logic:iterate id="o" name="collection" type="java.lang.Object" indexId="i">
    <bean:define id="checked" value="<%= (listv!=null && listv.contains(o))?"checked":"" %>" />
    <input type="checkbox" ${checked} value="${i}" id="f_${param.f}" name="f_${param.f}" />&nbsp;${o}<br/>
</logic:iterate>