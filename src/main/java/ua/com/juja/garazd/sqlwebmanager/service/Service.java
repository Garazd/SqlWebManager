package ua.com.juja.garazd.sqlwebmanager.service;

import java.util.List;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;

public interface Service {

    List<String> commandsList();

    DatabaseManager connect(String databaseName, String userName, String password);

    List<List<String>> getTableData(DatabaseManager manager, String tableName);
}