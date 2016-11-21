<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="create" method="post">
            <table>
                <input type="hidden" name="columnCount" value="${columnCount}"/>
                <input type="hidden" name="tableName" value="${tableName}"/>
                <tr>
                    <c:forEach begin="1" end="${columnCount}" varStatus="loop">
                <tr>
                    <td>Column name ${loop.count}</td>
                    <td><label>
                        <input type="text" name="columnName${loop.count}"/>
                    </label></td>

                    <td>Column value ${loop.count}</td>
                    <td><label>
                        <input type="text" name="columnValue${loop.count}"/>
                    </label></td>
                </tr>
                </c:forEach>

                <tr>
                    <td></td>
                    <td><input type="submit" value="create"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>