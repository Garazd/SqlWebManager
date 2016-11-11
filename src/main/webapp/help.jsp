<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SQLWebManager</title>
    </head>

    <body>
        Existing command:<br>

        help<br>
            to show all commands<br>

        databases<br>
            to to show all databases<br>

        createDatabase|databaseName<br>
            to create the database<br>

        dropDatabase|databaseName<br>
            to drop the database<br>

        createTable|tableName(columnName1 type, columnName2 type,...columnNameN type)<br>
            to create the table with the name. Type can be text or integer<br>

        dropTable|tableName<br>
            to drop the table<br>

        createEntry|tableName|column1|value1|column2|value2|...|columnN|valueN<br>
            to create entries the table<br>

        clearTable|tableName<br>
            to clear the table<br>

        show<br>
            to show all tables in the database<br>

        contents|tableName<br>
            to get the contents of the table<br>

        exit<br>
            to exit the program<br>
    </body>
</html>