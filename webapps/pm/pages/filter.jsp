<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.filter">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <form action="${es.context_path}/${ctx.operation.id}.do?pmid=${pmid}"  accept-charset="UTF-8" >
            <input type="hidden" name="finish" value="yes" />
            <fieldset>
                <div class="content">
                    <table id="box-table-a">
                        <tbody id="list_body" >
                            <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                                <tr>
                                    <th scope="row" width="175px"><div><label for="object.${field.id}"><pm:field-name entity="${entity}" field="${field}" /></label></div></th>
                                    <td><pm:filter-operations field_id="${field.id}" filter="${ctx.entityContainer.filter}" /></td>
                                    <td><pmfn:converted-item ctx="${ctx}" field="${field}" fieldValue="${ctx.entityContainer.filter.filterValues[field.id][0]}"/></td>
                                </tr>
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