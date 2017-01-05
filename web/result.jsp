<%-- 
    Document   : result.jsp
    Created on : 19-Dec-2016, 13:43:02
    Author     : seromero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>PayPal Pro Direct result</h1>

        <%
            String result = (String) session.getAttribute("result");

            result = java.net.URLDecoder.decode(result, "UTF-8");
            String[] myStr = result.split("&");

            for (String item : myStr) {

                out.print(item + "<br>");
            }

        %>
    </center>
</body>
</html>
