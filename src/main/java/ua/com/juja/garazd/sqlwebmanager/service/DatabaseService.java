package ua.com.juja.garazd.sqlwebmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.model.PostgresDatabaseManager;

@Component
public abstract class DatabaseService implements Service {

    @Autowired
    private PostgresDatabaseManager manager;
    private String className;
    private String getDatabaseManager;

    protected abstract DatabaseManager getDatabaseManager();

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connectDatabase", "clearTable");
    }

    @Override
    public DatabaseManager connectDatabase(String databaseName, String userName, String password) throws ServiceException {
        try {
            DatabaseManager manager = getDatabaseManager();
            manager.connectDatabase(databaseName, userName, password);
            return manager;
        } catch (Exception e) {
            throw new ServiceException("Connection error", e);
        }
    }

    @Override
    public void clearTable(DatabaseManager manager, String table) {
        manager.clearTable(table);
    }

    @Override
    public void createDatabase(DatabaseManager manager, String database) {
        manager.createDatabase(database);
    }

    @Override
    public void createEntry(DatabaseManager manager, String table, String[] values) {
        Set<String> columns = manager.getTableColumns(table);

        Map<String, Object> result = new LinkedHashMap<>();
        Iterator<String> iterator = columns.iterator();
        for (int index = 0; iterator.hasNext(); index++) {
            result.put(iterator.next(), values[index]);
        }
        manager.createEntry(table, result);
    }

    @Override
    public void createTable(DatabaseManager manager, String query) {
        manager.createTable(query);
    }

    @Override
    public void disconnectDatabase(DatabaseManager manager) {
        manager.disconnectDatabase();
    }

    @Override
    public void dropDatabase(DatabaseManager manager, String database) {
        manager.dropDatabase(database);
    }

    @Override
    public void dropTable(DatabaseManager manager, String table) {
        manager.dropTable(table);
    }

    @Override
    public List<List<String>> getTableData(DatabaseManager manager, String tableName) {
        List<Map<String, Object>> tableData = manager.getTableData(tableName);
        List<String> tableColumns = new ArrayList<>(manager.getTableColumns(tableName));

        List<List<String>> result = new ArrayList<>();
        result.add(tableColumns);
        for (Map<String, Object> entry : tableData) {
            List<String> row = new ArrayList<>();
            for (String column : tableColumns) {
                row.add(entry.get(column).toString());
            }
            result.add(row);
        }

        return result;
    }

    @Override
    public Set<String> tables(DatabaseManager manager) {
        return manager.getTableNames();
    }

    @Override
    public void updateTable(DatabaseManager manager, String table, String[] values) {
        Map<String, String> updateValues = new LinkedHashMap<>();
        int index = 0;
        for (String column: manager.getTableColumns(table)) {
            updateValues.put(column, values[index++]);
        }
        String primaryKeyName = manager.getPrimaryKey(table);
        String primaryKeyValue = updateValues.remove(primaryKeyName);

        manager.updateTable(table, primaryKeyName, primaryKeyValue, updateValues);
            String.format("UPDATE RECORD IN '%s' TABLE", table);
    }

    public void setGetDatabaseManager(String getDatabaseManager) {
        this.getDatabaseManager = getDatabaseManager;
    }
}