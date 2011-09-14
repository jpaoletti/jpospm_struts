<%@include file="../inc/inc-full.jsp" %>
<bean:define id="e_container" name="ctx" property="entityContainer" />
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:pmtitle entity="${entity}" operation="${ctx.operation}"/>
        <pm:operations labels="true" operations="${ctx.map.operations.operations}"/>
        <div id="navigation_bar">
            <pm:navigation container="${e_container.owner}"  />
        </div>
        <div class="content">
            <table id="box-table-a">
                <tbody id="list_body" >
                    <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field">
                        <c:if test="${fn:contains(field.display,'show') or fn:contains(field.display,'all')}">
                            <tr>
                                <th scope="row" width="175px"><pm:field-name entity="${entity}" field="${field}" /></th>
                                <td>
                                    <pm:converted-item es="${es}" operation="${ctx.operation}" entity="${entity}" item="${ctx.selected.instance}" field="${field}" />
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
        </div>
    </div>
    <logic:present name="entity" property="highlights">
        <style type="text/css" >
            <logic:iterate id="highlight" name="entity" property="highlights.highlights">
                .${highlight.field}_${highlight.value} { background-color: ${highlight.color}; }
            </logic:iterate>
        </style>
    </logic:present>
</pm:page>