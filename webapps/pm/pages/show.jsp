<%@include file="../inc/inc-full.jsp" %>
<bean:define id="e_container" name="ctx" property="entityContainer" />
<pm:page title="titles.add">
    <pm:errors />
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
                                <td><pm:converted-item operation="${ctx.operation}" entity="${entity}" item="${ctx.selected.instance}" field="${field}" /></td>
                            </tr>
                        </c:if>
                    </logic:iterate>
                </tbody>
                <tfoot>
                    <tr><td colspan="2">&nbsp;</td></tr>
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
    <script type="text/javascript" src="${es.context_path}/js/highlight.js"></script>
</pm:page>