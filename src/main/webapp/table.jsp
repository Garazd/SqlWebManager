<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="table" method="post">
            <table>
                <input type="hidden" name="tableName" value="${tableName}" />
                <input type="hidden" name="columnCount" value="${columnCount}" />

                <tr>
                    <td>Primary key name</td>
                    <td><input type="text" name="keyName"/></td>
                </tr>

                <c:forEach begin="1" end="${columnCount - 1}" varStatus="loop">
                    <tr>
                        <td>Column name${loop.count}</td>
                        <td><input type="text" name="columnName${loop.count}"/></td>

                        <td>Column type${loop.count}</td>
                        <td><input type="text" name="columnType${loop.count}"/></td>
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