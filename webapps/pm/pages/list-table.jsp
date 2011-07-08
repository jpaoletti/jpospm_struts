<%@include file="../inc/inc-full.jsp" %>
<bean:define id="pmlist" name="ctx" property="entityContainer.list" toScope="request"  type="org.jpos.ee.pm.core.PaginatedList"/>
<bean:define id="c" name="pmlist" property="listTotalDigits" type="java.lang.Integer" />
<bean:define id="has_selected" name="PMLIST" property="hasSelectedScope" type="java.lang.Boolean" />
<bean:define id="messages" name="org.apache.struts.action.MESSAGE" type="org.apache.struts.util.MessageResources" scope="application"/>
<bean:define id="pmsession" name="pmsession" type="org.jpos.ee.pm.core.PMSession" scope="session"/>
<script type="text/javascript">
    function selectItem(i){
        $.ajax({ url: "selectItem.do?pmid="+"${pmid}"+"&idx="+i});
    }
</script>
<table id="list" class="display" >
    <thead>
        <tr>
            <th scope="col" style="width:${pmlist.operationColWidth}">&nbsp;</th>
            <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field"><c:if test="${fn:contains(field.display,operation.id) or fn:contains(field.display,'all')}"><bean:define id="w" value="<%=(field.getWidth().compareTo("")!=0)?"style='width:"+field.getWidth()+"px;'":"" %>"></bean:define>
            <th scope="col" ${w} ><pm:field-name entity="${entity}" field="${field}" />
            </th></c:if></logic:iterate>
        </tr>
    </thead>
    <tbody id="list_body" >
        <bean:define id="contents" name="contents" type="org.jpos.util.DisplacedList" />
        <logic:iterate id="item" name="contents" >
            <% Integer i = contents.indexOf(item); request.setAttribute("i",i); %>
            <tr class="<%= es.getHighlight(ctx.getEntity(), null, item, null) %>">
                <td style="color:gray; white-space: nowrap;">
                    <logic:equal name="has_selected" value="true">
                        <bean:define id="checked" value="<%=(ctx.getEntityContainer().getSelectedIndexes().contains(i))?"checked":"" %>" />
                        <input type="checkbox" id="selected_item" value="${i}" onchange="selectItem(this.value);" ${checked} />
                    </logic:equal>
                    <c:if test="${pmlist.showRowNumber}"><%= String.format("[%0"+c+"d]", i) %></c:if>&nbsp;
                    <%= es.getListItemOperations(ctx, messages, item, i) %>
                </td>
                <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field"><c:if test="${fn:contains(field.display,operation.id) or fn:contains(field.display,'all')}">
                        <td align="${field.align}"><pm:converted-item es="${es}" operation="${operation}" entity="${entity}" item="${item}" field="${field}" />
                        </td></c:if></logic:iterate>
            </tr>
        </logic:iterate>
    </tbody>
    <tfoot>
        <logic:equal name="PMLIST" property="searchable" value="true" >
            <tr>
                <th><input type="hidden" name="search" class="search_init" /></th>
                    <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field">
                        <c:if test="${fn:contains(field.display,operation.id) or fn:contains(field.display,'all')}">
                        <th><input type="text" name="search_<pm:field-name entity="${entity}" field="${field}" />" value="<bean:message key="list.input.search"/><pm:field-name entity="${entity}" field="${field}" />" class="search_init" /></th>
                        </c:if>
                    </logic:iterate>
            </tr>
        </logic:equal>
    </tfoot>
</table>
<script type="text/javascript" >
    $(".confirmable_true").bind('click',function(){
        return confirm("<pm:message key='pm.operation.confirm.question' />");
    });
</script>