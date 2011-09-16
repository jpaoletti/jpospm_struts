<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <form action="${es.context_path}/${ctx.operation.id}.do?pmid=${pmid}"  accept-charset="UTF-8" >
            <input type="hidden" name="finish" value="yes" />
            <fieldset>
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
                            <tr><td colspan="2"><div class="entity_message_container_${entity.id}">&nbsp;</div></td></tr>
                        </tfoot>
                    </table>
                    <input type="submit" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.submit"/>" />
                    <input type="reset" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.reset" />" />
                </div>
            </fieldset>
        </form>
    </div>
</pm:page>