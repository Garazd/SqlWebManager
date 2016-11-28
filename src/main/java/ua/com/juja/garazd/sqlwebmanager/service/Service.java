package ua.com.juja.garazd.sqlwebmanager.service;

import java.util.List;
import java.util.Set;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;

public interface Service {

    DatabaseManager connectDatabase(String databaseName, String userName, String password) throws ServiceException;

    Set<String> tables(DatabaseManager manager);

    void clearTable(DatabaseManager manager, String table);

    void createDatabase(DatabaseManager manager, String database);

    void createEntry(DatabaseManager manager, String table, String[] values);

    void createTable(DatabaseManager manager, String query);

    void disconnectDatabase(DatabaseManager manager);

    void dropDatabase(DatabaseManager manager, String database);

    void dropTable(DatabaseManager manager, String table);

    List<String> commandsList();

    List<List<String>> getTableData(DatabaseManager manager, String tableName);

    void updateTable(DatabaseManager manager, String table, String[] values);
}