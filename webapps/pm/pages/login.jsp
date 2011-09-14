<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="login">
    <logic:present name="pmsession">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </logic:present>
    <logic:present name="reload" scope="request">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </logic:present>
    <logic:notPresent name="pmsession">
        <div id="login" class="boxed">
            <h2 class="title"><bean:message key="login"/> </h2>
            <div class="content">
                <form action="${es.context_path}/login.do" method="POST">
                    <fieldset>
                        <logic:equal value="true" name="pm" property="loginRequired">
                            <legend><bean:message key="login.sign.in" /></legend>
                            <label for="username"><bean:message key="login.username" /></label>
                            <input name="username" id="username" />
                            <label for="password"><bean:message key="login.password" /></label>
                            <input type="password" name="password" id="password" value="" />
                        </logic:equal>
                        <input type="submit" value="<bean:message key="login.sign.in" />" />
                    </fieldset>
                </form>
            </div>
            <div class="entity_message_container_${entity.id}">&nbsp;</div>
        </div>
    </logic:notPresent>
</pm:page>