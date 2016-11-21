<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <table border="1">
            <tr><td></td><td>Tables</td></tr>
            <c:forEach items="${tables}" var="table" varStatus="loop">
                <tr><td>${loop.count}</td> <td>${table}</td></tr>
            </c:forEach>

        </table>
        <tr><td><b>To menu <a href="menu">menu</a></b></td></tr>
    </body>
</html>