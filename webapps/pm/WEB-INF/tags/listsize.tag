<%@tag description="This tag returns a list size" pageEncoding="UTF-8"%>
<%@tag import="java.util.List" %>
<%@attribute name="list_name" required="true"%>
<% out.print((List<?>)request.getAttribute(list_name)); %>