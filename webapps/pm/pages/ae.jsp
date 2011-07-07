<%@include file="../inc/inc-full.jsp" %>
<bean:define id="e_container" name="ctx" property="entityContainer" />
<bean:define id="entity_instance" name="ctx" property="selected.instance" toScope="request"/>
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:pmtitle entity="${entity}" operation="${ctx.operation}" />
        <form action="${es.context_path}/${ctx.operation.id}.do?pmid=${pmid}"  accept-charset="UTF-8" >
            <html:hidden property="finish" value="yes"/>
            <fieldset>
                <pm:operations labels="true" operations="${ctx.map.operations.operations}"/>
                <div id="navigation_bar">
                    <pm:navigation container="${e_container.owner}"  />
                </div>
                <div class="content">
                    <table id="box-table-a">
                        <tbody id="list_body" >
                            <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field">
                                <c:if test="${fn:contains(field.display,ctx.operation.id) or fn:contains(field.display,'all')}">
                                    <tr>
                                        <th scope="row" width="175px"><div><label for="object.${field.id}"><pm:field-name entity="${entity}" field="${field}" /></label></div></th>
                                        <td><div id="f_${field.id}_div"><pm:converted-item operation="${ctx.operation}" entity="${entity}" item="${entity_instance}" field="${field}" /></div></td>
                                    </tr>
                                </c:if>
                            </logic:iterate>
                        </tbody>
                        <tfoot>
                            <tr><td colspan="2"><pm:errors />&nbsp;</td></tr>
                        </tfoot>
                    </table>
                    <html:submit styleId="${entity.id}_submit"><pm:message key="pm.struts.form.submit"/></html:submit>
                    <html:reset styleId="${entity.id}_submit"><pm:message key="pm.struts.form.reset" /></html:reset>
                </div>
            </fieldset>
        </form>
    </div>
</pm:page>