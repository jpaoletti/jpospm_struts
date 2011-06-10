<%@include file="inc/tag-libs.jsp" %>
<%@ page isErrorPage="true"  %>
<%@page import="java.io.PrintWriter" import="org.jpos.ee.pm.core.*" import="java.io.StringWriter" %>

<div class="leftpane" align="center">
	<p align="left">
	 <i><bean:message key="errors.500" /></i>
	</p>
	<p align="right">
	 <b><bean:message key="webmaster" /></b>
	</p><br/>
	<%-- Exception Handler --%>
	<font color="red">
	${exception}<br>
	</font>
	<style>
		.exception{
			font-size: small;
			text-align: left;
		}
	</style>
	<xmp class="exception">
	<% 
	if(exception!=null){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		out.print(sw);
		sw.close();
		pw.close();
		if(PresentationManager.pm!=null) PresentationManager.pm.error(exception);
	}
	%>
	</xmp>
	</div>