<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>
    <body>
        <form action="${actionURL}" method="post">
            <table>
                <tr>
                    <td>Table name</td>
                    <td><label>
                        <input type="text" name="tableName"/>
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