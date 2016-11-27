package ua.com.juja.garazd.sqlwebmanager.model;

import java.util.*;

public class InMemoryDatabaseManager implements DatabaseManager {

    private Map<String, Object> tables = new LinkedHashMap<>();

    @Override
    public List<Map<String, Object>> getTableData(String tableName) {
        List<Map<String, Object>> result = new LinkedList<>();
        return result;
    }

    @Override
    public int getTableSize(String tableName) {
        return get(tableName).size();
    }

    @Override
    public Set<String> getTableNames() {
        return tables.keySet();
    }

    @Override
    public String getPrimaryKey(String table) {
        return null;
    }

    @Override
    public void connectDatabase(String database, String userName, String password) {
        // do nothing
    }

    @Override
    public void clearTable(String tableName) {
        get(tableName).clear();
    }

    private List<Object> get(String tableName) {
        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new LinkedList<>());
        }
        return (List<Object>) tables.get(tableName);
    }

    @Override
    public void createEntry(String tableName, Map<String, Object> input) {
        get(tableName).add(input);
    }

    @Override
    public void updateTable(String tableName, String primaryKeyName, String primaryKeyValue, Map<String, String> newValue) {
        // do nothing
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return new LinkedHashSet<>(Arrays.asList("name", "password", "id"));
    }

    @Override
    public Set<String> getDatabasesName() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void disconnectDatabase() {
        // do nothing
    }

    @Override
    public void createDatabase(String databaseName) {
        // do nothing
    }

    @Override
    public void dropDatabase(String databaseName) {
        // do nothing
    }

    @Override
    public void createTable(String query) {
        // do nothing
    }

    @Override
    public void dropTable(String tableName) {
        // do nothing
    }
}