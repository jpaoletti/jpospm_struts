<%@include file="../inc/tag-libs.jsp" %>
<bean:define id="value" value="${ctx.map.PM_FIELD_VALUE}"/>
<bean:define id="checked" value="${param.isNull ? 'checked' : ''}"/>
<bean:define id="disabled" value="${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''}"/>
<c:if test="${param.withNull}">
    <script type="text/javascript" >
        function disable${param.f}(v){
            if (v){
                $('#f_${param.f}').val("");
                $('#f_${param.f}').attr('disabled', 'disabled');
            }else{
                $('#f_${param.f}').removeAttr('disabled');
            }
        }
    </script>
    <input type="checkbox" ${checked} value="true" id="f_${param.f}_null" name="f_${param.f}_null" onclick="disable${param.f}(this.checked);" />
</c:if>
<textarea cols="${param.cols}" rows="${param.rows}"  ${disabled} id="f_${param.f}" name="f_${param.f}">${value}</textarea>