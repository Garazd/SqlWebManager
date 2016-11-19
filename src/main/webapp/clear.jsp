<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="clear" method="post">
            <table>
                <tr>
                    <td>Table name</td>
                    <td><input type="text" name="tableName"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="clearTable"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>