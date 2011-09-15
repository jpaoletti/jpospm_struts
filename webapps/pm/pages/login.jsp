<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="login">
    <c:if test="${not empty pmsession}">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </c:if>
    <c:if test="${not empty reload}">
        <script type="text/javascript" charset="utf-8">
            parent.location = "/";
        </script>
    </c:if>
    <c:if test="${empty pmsession}">
        <div id="login" class="boxed">
            <h2 class="title"><pm:message key="login"/> </h2>
            <div class="content">
                <form action="${es.context_path}/login.do" method="POST">
                    <fieldset>
                        <c:if test="${pm.loginRequired}">
                            <legend><pm:message key="login.sign.in" /></legend>
                            <label for="username"><pm:message key="login.username" /></label>
                            <input name="username" id="username" />
                            <label for="password"><pm:message key="login.password" /></label>
                            <input type="password" name="password" id="password" value="" />
                        </c:if>
                        <input type="submit" value="<pm:message key="login.sign.in" />" />
                    </fieldset>
                </form>
            </div>
            <div class="entity_message_container_${entity.id}">&nbsp;</div>
        </div>
    </c:if>
</pm:page>