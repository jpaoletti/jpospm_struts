<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="login">
    <logic:present name="user">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </logic:present>
    <logic:present name="reload" scope="request">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </logic:present>
    <logic:notPresent name="user">
        <pm:login />
    </logic:notPresent>
</pm:page>