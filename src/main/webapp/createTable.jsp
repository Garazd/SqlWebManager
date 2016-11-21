<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="createTable" method="get">
            <table>
                <tr>
                    <td>Table name</td>
                    <td><label>
                        <input type="text" name="tableName"/>
                    </label></td>
                </tr>

                <tr>
                    <td>Column count</td>
                    <td><label>
                        <input type="number" name="columnCount"/>
                    </label></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" value="OK"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>