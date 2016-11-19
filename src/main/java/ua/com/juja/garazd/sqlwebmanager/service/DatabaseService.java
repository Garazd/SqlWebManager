package ua.com.juja.garazd.sqlwebmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.model.PostgresDatabaseManager;

@Component
public class DatabaseService implements Service {

    @Autowired
    private PostgresDatabaseManager manager;
    private String className;

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connectDatabase", "clearTable");
    }

    @Override
    public DatabaseManager connectDatabase(String databaseName, String userName, String password) {
        manager.connectDatabase(databaseName, userName, password);
        return manager;
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

    public void setClassName(String className) {
        this.className = className;
    }
}