<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="deleteDatabase" method="post">
            <table>
                <tr>
                    <td>Database name</td>
                    <td><input type="text" name="databaseName"/></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" value="deleteDatabase"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>