<%@include file="../inc/inc-full.jsp" %>
<%
    Entity weak = WeakConverter.getEntity(ctx);
    Collection listv = WeakConverter.getCollection(ctx);
    request.setAttribute("weak", weak);
    request.setAttribute("woperation", weak.getOperations().getOperation("list"));
    request.setAttribute("contents", listv);
%>
<bean:define id="fields" 	  name="weak" property="orderedFields" type="java.util.List" toScope="request"/><br/>
<c:if test="${param.showbutton}">
    <a href="${es.context_path}/list.do?pmid=${param.weakid}" class='button edit' > &nbsp;&nbsp; <pm:message key="${param.buttontext}" /></a>
</c:if>
<c:if test="${param.showlist}">
    <div class="boxed">
        <table id="list" class="display" >
            <thead>
                <tr>
                    <logic:iterate id="field" name="fields" type="org.jpos.ee.pm.core.Field">
                        <c:if test="${fn:contains(field.display,'list') or fn:contains(field.display,'all')}">
                            <th scope="col" style="width:${field.width}px;" ><pm:field-name entity="${weak}" field="${field}" /></th>
                        </c:if>
                    </logic:iterate>
                </tr>
            </thead>
            <tbody id="list_body" >
                <logic:iterate id="item" name="contents" >
                    <tr>
                        <logic:iterate id="field" name="fields" type="org.jpos.ee.pm.core.Field" indexId="j">
                            <c:if test="${fn:contains(field.display,'list') or fn:contains(field.display,'all')}">
                                <td align="text-align:${field.align};">
                                    <pm:converted-item es="${es}" operation="${woperation}" entity="${weak}" item="${item}" field="${field}" />
                                </td>
                            </c:if>
                        </logic:iterate>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </div>
</c:if>