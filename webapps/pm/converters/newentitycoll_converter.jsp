<%@include file="../inc/tag-libs.jsp" %>
<%@page import="org.jpos.ee.pm.core.*" import="org.jpos.ee.Constants" import="java.util.Collection"%>
<%@page import="java.util.List" import="org.jpos.ee.pm.struts.PMEntitySupport" import="org.jpos.ee.pmee.PMList" %>
<bean:define id="listv"      name="ctx"    property="map.PM_FIELD_VALUE" type="java.util.Collection" />
<bean:define id="collection" name="ctx"    property="tmpList" type="java.util.List" />
<div id="">
</div>
<logic:iterate id="o" name="list" property="contents" type="java.lang.Object" indexId="i">
	<bean:define id="checked" value="<%= (listv.contains(o))?"checked":"" %>" />
	<input type="checkbox" ${checked} value="${param.entity}@${i}" id="f_${param.f}" name="f_${param.f}" />&nbsp;${o}<br/>
</logic:iterate>