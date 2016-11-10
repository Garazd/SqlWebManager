package ua.com.juja.garazd.sqlwebmanager.controller.command;

import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.view.View;

public class CreateTable implements Command {
    private DatabaseManager manager;
    private View view;

    public CreateTable(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }
    @Override
    public boolean canProcess(String command) {
        return command.startsWith("createTable|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException("Command format is 'createTable|tableName(columnName1 type, " +
                "columnName2 type,...columnNameN type)', and you input: " + command);
        }
        manager.createTable(data[1]);
        view.write("Table '" + data[1] + "' created.");
    }
}