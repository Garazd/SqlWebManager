<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="update" method="post">
            <table>
                <input type="hidden" name="columnCount" value="${columnCount}" />
                <input type="hidden" name="tableName" value="${tableName}" />
                <tr>
                    <td>Primary key name</td>
                    <td><input type="text" name="keyName"/></td>

                    <td>Primary key value</td>
                    <td><input type="text" name="keyValue"/></td>
                </tr>

                <c:forEach begin="1" end="${columnCount - 1}" varStatus="loop">
                    <tr>
                        <td>Column name ${loop.count}</td>
                        <td><input type="text" name="columnName${loop.count}"/></td>

                        <td>Column value ${loop.count}</td>
                        <td><input type="text" name="columnValue${loop.count}"/></td>
                    </tr>
                </c:forEach>

                <tr>
                    <td></td>
                    <td><input type="submit" value="update"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>