<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>

    <body>
        <c:forEach items="${items}" var="item">
            <a href="${item}">${item}</a><br>
        </c:forEach>
    </body>
</html>