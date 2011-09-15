<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.add">
    <div id="add" class="boxed">
        <pm:pmtitle entity="${entity}" operation="${ctx.operation}" />
        <form action="${es.context_path}/${ctx.operation.id}.do?pmid=${pmid}"  accept-charset="UTF-8" >
            <input type="hidden" name="finish" value="yes" />
            <fieldset>
                <pmfn:operations entity="${entity}" operations="${ctx.map.operations}" pmsession="${pmsession}" labels="true" />
                <div id="navigation_bar">
                    <pm:navigation container="${ctx.entityContainer.owner}"  />
                </div>
                <div class="content">
                    <table id="box-table-a">
                        <tbody id="list_body" >
                            <c:forEach var="field" items="${entity.orderedFields}">
                                <c:if test="${fn:contains(field.display,ctx.operation.id) or fn:contains(field.display,'all')}">
                                    <tr>
                                        <th scope="row" width="175px"><div><label for="object.${field.id}"><pm:field-name entity="${entity}" field="${field}" /></label></div></th>
                                        <td>
                                            <div id="f_${field.id}_div"><pm:converted-item es="${es}" operation="${ctx.operation}" entity="${entity}" item="${ctx.selected.instance}" field="${field}" /></div>
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
                    <input type="submit" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.submit"/>" />
                    <input type="reset" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.reset" />" />
                </div>
            </fieldset>
        </form>
    </div>
</pm:page>