<%-- Created on : 02/04/2009, 22:22:00 --%>
<%@ tag description="This tag encapsulates a standard html page" pageEncoding="UTF-8" import="org.jpos.ee.pm.struts.PMStrutsService" %>
<%@ tag import="org.jpos.ee.pm.struts.PMEntitySupport"%>
<%@ tag import="org.jpos.ee.pm.core.*"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ attribute name="title" required="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title><pm:message key="${pm.title}"/> - <pm:message key="${title}"/></title>
        <link href="${es.context_path}/templates/${pm.template}/all.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${es.context_path}/js/jquery.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jqueryslidemenu.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/misc.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.modal.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.center.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.hotkeys.js"></script>
        <link rel="shortcut icon" href="${es.context_path}/templates/${pm.template}/img/favicon.ico">
    </head>
    <body>
        <% try{ %>
        <jsp:doBody />
        <% }catch(Exception e){PresentationManager.getPm().error(e); %>
        <pm:message key="pm.page.error"/>
        <%} %>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                jQuery.each(PM_onLoadFunctions, function(){
                    this();
                });
            });
        </script>
    </body>
</html>