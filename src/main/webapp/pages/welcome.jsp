<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>

<%-- Potentially could be welcome page but for now just redirect to login page --%>
<%
    response.sendRedirect(request.getContextPath() + "/login");
%>
