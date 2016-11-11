package ua.com.juja.garazd.sqlwebmanager.service;

import java.util.Arrays;
import java.util.List;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.model.PostgresDatabaseManager;

public class ServiceImpl implements Service {

    private DatabaseManager manager;

    public ServiceImpl() {
        manager = new PostgresDatabaseManager();
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect");
    }

    @Override
    public void connect(String databaseName, String userName, String password) {
        manager.connectDatabase(databaseName, userName, password);
    }
}