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

    void createEntry(String tableName, Map<String, Object> input);

    void clearTable(String tableName);

    void updateTable(String tableName, int id, Map<String, Object> newValue);


    List<Map<String, Object>> getTableData(String tableName);

    Set<String> getTableNames();

    Set<String> getTableColumns(String tableName);

    Set<String> getDatabasesName();

    int getTableSize(String tableName);
}