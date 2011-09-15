<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <div class="content">
            <table id="box-table-a">
                <tbody id="list_body" >
                    <c:forEach var="field" items="${entity.orderedFields}">
                        <c:if test="${fn:contains(field.display,'show') or fn:contains(field.display,'all')}">
                            <tr>
                                <th scope="row" width="175px"><pm:field-name entity="${entity}" field="${field}" /></th>
                                <td>
                                    <pm:converted-item es="${es}" operation="${ctx.operation}" entity="${entity}" item="${ctx.selected.instance}" field="${field}" />
                                    <div class="field_message_container_${entity.id}_${field.id}"></div>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr><td colspan="2"><div class="entity_message_container_${entity.id}">&nbsp;</div></td></tr>
                </tfoot>
            </table>
        </div>
    </div>
    <c:if test="${not empty entity.highlights}">
        <style type="text/css" >
            <c:forEach var="highlight" items="${entity.highlights.highlights}">
                .${highlight.field}_${highlight.value} { background-color: ${highlight.color}; }
            </c:forEach>
        </style>
    </c:if>
</pm:page>