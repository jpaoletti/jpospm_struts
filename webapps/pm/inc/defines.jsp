<%-- General definitions. Requires tag-libs.jsp @author jpaoletti --%>
<bean:define id="ctx" name="PM_CONTEXT" type="org.jpos.ee.pm.struts.PMStrutsContext" toScope="request" />
<bean:define id="es" name="es" type="org.jpos.ee.pm.struts.PMEntitySupport" toScope="request" />
<jsp:setProperty name="es" property="request" value="<%= request %>" />
<logic:present name="ctx" property="entity">
    <bean:define id="entity" name="ctx" property="entity" toScope="request" type="org.jpos.ee.pm.core.Entity" />
</logic:present>
<bean:define id="messages" name="org.apache.struts.action.MESSAGE" type="org.apache.struts.util.MessageResources" toScope="application"/>
