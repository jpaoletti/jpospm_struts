<%@tag import="org.jpos.ee.pm.core.PresentationManager"%><%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %><%@attribute name = "key" required="true" type="java.lang.String" %><%@attribute name = "arg0" required="false" type="java.lang.String" %><%@attribute name = "arg1" required="false" type="java.lang.String" %><%@attribute name = "arg2" required="false" type="java.lang.String" %><%@attribute name = "arg3" required="false" type="java.lang.String" %><%try{ %><bean:message key="${key}" arg0="${arg0}" arg1="${arg1}" arg2="${arg2}" arg3="${arg3}" /><%}catch(Exception e){PresentationManager.getPm().warn("Key "+key+" not found"); out.print(key);} %>