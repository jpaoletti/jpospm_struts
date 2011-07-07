<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    function paginate(i){
        $("#page").val(i);
        $("#listform").submit();
    }
</script>
<logic:equal value="true" name="PMLIST" property="paginable">
    <pm:message key='pm.struts.list.rpp' />
    <html:select property="rowsPerPage" value="${PMLIST.rowsPerPage}" onchange="this.form.submit();">
        <html:option value="5" />
        <html:option value="10"/>
        <html:option value="20"/>
        <html:option value="50"/>
        <html:option value="100"/>
    </html:select> <pm:message key="pm.struts.list.of" />
    <c:if test="${PMLIST.total != null}">${PMLIST.total}</c:if>
    <c:if test="${PMLIST.total == null}">? &nbsp;</c:if>| &nbsp;&nbsp;&nbsp;&nbsp;
    <c:if test="${PMLIST.page > 1}">
        <a href="javascript:paginate('${PMLIST.page-1}')">&laquo; <pm:message key="pm.struts.list.prev"/></a> |
    </c:if>
    <c:if test="${PMLIST.total != null}">
        <logic:greaterThan value="20" name="PMLIST" property="pages">
            <pm:list-pagination-link i="${1}" />
            <html:text property="page" value="${PMLIST.page}" styleId="page" size="5" style="width:25px;" /> |
            <pm:list-pagination-link i="${PMLIST.pages}" />
        </logic:greaterThan>

        <logic:lessEqual value="20" name="PMLIST" property="pages">
            <input type="hidden" value="${PMLIST.page}" id="page" name="page"/>
            <logic:iterate id="i" name="PMLIST" property="pageRange" >
                <pm:list-pagination-link i="${i}" />
            </logic:iterate>
        </logic:lessEqual>
    </c:if>

    <c:if test="${PMLIST.total == null}">
        <pm:list-pagination-link i="${1}" />
        <html:text property="page" value="${PMLIST.page}" styleId="page" size="5" style="width:25px;" /> |
        <input type="hidden" value="${PMLIST.page}" id="page" name="page"/>
    </c:if>
    <c:if test="${PMLIST.total == null || PMLIST.page < PMLIST.pages}">
        <a href="javascript:paginate('${PMLIST.page+1}')"><pm:message key="pm.struts.list.next"/> &raquo;</a>
    </c:if>
</logic:equal>