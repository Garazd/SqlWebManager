package ua.com.juja.garazd.sqlwebmanager.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatabaseManager {

    void connectDatabase(String database, String userName, String password);

    boolean isConnected();

    void disconnectDatabase();


    void createDatabase (String databaseName);

    void dropDatabase (String databaseName);

    void createTable(String query);

    void dropTable(String tableName);

    void createEntry(String tableName, Map<String, Object> columnData);

    void clearTable(String tableName);

    void updateTable(String tableName, String primaryKeyName, String primaryKeyValue, Map<String, String> newValue);


    List<Map<String, Object>> getTableData(String tableName);

    Set<String> getTableNames();

    String getPrimaryKey(String table);

    Set<String> getTableColumns(String tableName);

    Set<String> getDatabasesName();

    int getTableSize(String tableName);
}