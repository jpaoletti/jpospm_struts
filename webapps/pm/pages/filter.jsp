<%@include file="../inc/inc-full.jsp" %>
<bean:define id="e_container" name="es" property="container" />
<bean:define id="entity_filter" name="es" property="filter" toScope="request" type="org.jpos.ee.pm.core.EntityFilter"/>
<pm:page title="titles.filter">
    <div id="add" class="boxed">
        <pm:pmtitle entity="${entity}" operation="${ctx.operation}"/>
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
                                        <td><pm:filter-operations field_id="${field.id}" filter="${entity_filter}" /></td>
                                        <td>
                                            <pm:converted-item es="${es}" operation="${ctx.operation}" entity="${entity}" field="${field}" field_value="${entity_filter.filterValues[field.id][0]}" />
                                            <div class="field_message_container_${entity.id}_${field.id}"></div>
                                        </td>
                                    </tr>
                                </c:if>
                            </logic:iterate>
                        </tbody>
                        <tfoot>
                            <tr><td colspan="2"><div class="entity_message_container_${entity.id}">&nbsp;</div></td></tr>
                        </tfoot>
                    </table>
                    <html:submit styleId="${entity.id}_submit"><pmfn:message key="pm.struts.form.submit"/></html:submit>
                    <html:reset styleId="${entity.id}_submit"><pmfn:message key="pm.struts.form.reset" /></html:reset>		</div>
            </fieldset>
        </form>
    </div>
</pm:page>