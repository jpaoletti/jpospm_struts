<%@include file="inc/tag-libs.jsp" %>
<script type="text/javascript" charset="utf-8">
    function loadPage(url){
        var mf = window.frames["mainframe"];
        mf.location = url;
        $(mf).focus();
    }
</script>
<logic:present name="pm">
    <pm:page title="titles.index" >
        <script type="text/javascript">
            $(document).ready(function(){
                $(window.frames["mainframe"]).focus();
            });
        </script>
        <div id="page-container">
            <pm:header />
            <jsp:include page="pages/menu.jsp" />
            <div id="content">
                <logic:notPresent scope="session" name="user">
                    <iframe id="mainframe" name="mainframe" frameborder="0"  width="100%" height="75%" src="${es.context_path}/pages/login.jsp" >
                    </iframe>
                </logic:notPresent>

                <logic:present scope="session" name="user">
                    <iframe id="mainframe" name="mainframe" frameborder="0"  width="100%" height="75%" src="${es.context_path}/${es.welcomePage}">
                    </iframe>
                </logic:present>
            </div>
            <pm:footer />
        </div>
    </pm:page>
</logic:present>

<logic:notPresent name="pm">
    <style type="text/css" >
        #pm_error_div{
            margin: 70px;
            padding: 40px;
            border-color: black;
            border-style: solid;
            border-width: 1px;
            background-color: red;
            font-size: large;
            font-weight: bold;
            width:400px;
        }
        #error_img{
            vertical-align:middle;
            float:left;
            width:100px;
            margin: 0px 20px 0px 0px;
        }
    </style>
    <div id="pm_error_div">
        <img alt="error" src="error.png" id="error_img">
        <bean:message key="pm.not.present"/>
    </div>
</logic:notPresent>