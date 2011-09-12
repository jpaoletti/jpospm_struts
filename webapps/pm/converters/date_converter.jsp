<%@include file="../inc/tag-libs.jsp" %>
<bean:define id="value" value="${param.value}"/>
<input type="text" value="${value}" id="f_${param.f}" name="f_${param.f}" />
<script type="text/javascript" src="${es.context_path}/js/jquery-ui.js"></script>
<script type='text/javascript'>
    $(document).ready(function() {
        $('#f_${param.f}').datepicker({
            buttonImage: '${es.context_path}/templates/${pm.template}/img/calendar.gif',
            buttonImageOnly: true,
            buttonText: '',
            showOn: 'both',
            speed: 'fast',
            dateFormat: '${param.format}'
        });
    });
</script>