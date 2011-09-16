<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <div class="content">
            <table id="box-table-a">
                <tbody id="list_body" >
                    <c:forEach var="field" items="${entity.orderedFields}">
                        <pmfn:displays operation="${ctx.operation}" field="${field}">
                            <tr>
                                <th scope="row" width="175px">
                                    <pm:field-name entity="${entity}" field="${field}" />
                                </th>
                                <td>
                                    <pmfn:converted-item ctx="${ctx}" field="${field}" />
                                </td>
                            </tr>
                        </pmfn:displays>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="entity_message_container_${entity.id}"/>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</pm:page>