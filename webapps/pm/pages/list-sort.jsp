<%@include file="../inc/tag-libs.jsp" %>
<div id="sort_page" class="jqmWindow">
    <pm:message key='list.sort.field' /> <br/>
    <html:select property="order" onchange="this.form.submit();" value="${PMLIST.sort.fieldId}">
        <logic:iterate id="field" name="entity" property="orderedFields" type="org.jpos.ee.pm.core.Field">
            <c:if test="${fn:contains(field.display,'sort') or fn:contains(field.display,'all')}">
                <html:option value="${field.id}"><pm:field-name entity='${entity}' field='${field}' /></html:option>
            </c:if>
        </logic:iterate>
    </html:select>
    <html:select property="desc" onchange="this.form.submit();" value="${PMLIST.sort.desc}">
        <html:option value="true"><pm:message key="list.sort.desc" /></html:option>
        <html:option value="false"><pm:message key="list.sort.asc" /></html:option>
    </html:select><br/>
</div>
