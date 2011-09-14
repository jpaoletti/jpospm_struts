<%@include file="../inc/inc-full.jsp" %>
<bean:define id="e_container"   name="ctx" property="entityContainer" toScope="request"/>
<bean:define id="operation"     name="ctx" property="operation" toScope="request"/>
<bean:define id="PMLIST"        name="e_container" property="list" toScope="request"/>
<bean:define id="operations"    name="PMLIST" property="operations" type="org.jpos.ee.pm.core.Operations" toScope="request"/>
<bean:define id="contents" 	name="PMLIST" property="contents" type="java.util.List<Object>" toScope="request"/>
<pm:page title="list">
    <div class="boxed">
        <pm:pmtitle entity="${entity}" operation="${operation}" />
        <pm:operations labels="true" operations="${operations.operations}"/>
        <div id="navigation_bar">
            <pm:navigation container="${e_container.owner}"  />
        </div>
        <form action="${es.context_path}/list.do" method="GET" class="listform" id="listform">
            <input type="hidden" name="pmid" value="${entity.id}" />
            <script type="text/javascript" charset="utf-8">
                var pmid = "${entity.id}";
                var searchable = "${PMLIST.searchable}" == "true";
                var sortable = false;
                var paginable = false;
                var texts = new Array(
                "<bean:message key='list.search.all' />" ,
                "<bean:message key='list.first' />" ,
                "<bean:message key='list.last' />" ,
                "<bean:message key='list.next' />" ,
                "<bean:message key='list.previous' />" ,
                "<bean:message key='list.info' />" ,
                "<bean:message key='list.info.empty' />" ,
                "<bean:message key='list.info.filtered' />" ,
                "<bean:message key='pm.struts.list.rpp' />" ,
                "<bean:message key='list.processing' />" ,
                "<bean:message key='list.zero.records' />"
            );
            </script>
            <script type="text/javascript" charset="utf-8" src="${es.context_path}/js/list.js"></script>

            <div id="list-container">
                <div id="example_wrapper" class="dataTables_wrapper">
                    <jsp:include page="list-table.jsp" />
                    <jsp:include page="list-pagination.jsp" />
                    <jsp:include page="list-sort.jsp" />
                </div>
            </div>
            <div class="entity_message_container_${entity.id}"></div>
            <script type="text/javascript">
                PM_register(function(){
                    $("#page").keydown(function(event){
                        if(event.keyCode == 13)
                            this.form.submit();
                    });
                    $('#operationsort').addClass('jqModal');
                    $('#sort_page').jqm({onShow:function(hash){
                            hash.w.css('opacity',0.88).show();
                        }});
                });
            </script>
        </form>
    </div>
</pm:page>