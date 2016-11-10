package ua.com.juja.garazd.sqlwebmanager.controller.command;

import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.model.TableConstructor;
import ua.com.juja.garazd.sqlwebmanager.view.View;

public class GetTableData implements Command {
    private DatabaseManager manager;
    private View view;

    public GetTableData(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("contents|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");

        TableConstructor constructor = new TableConstructor(
            manager.getTableColumns(data[1]), manager.getTableData(data[1]));
        view.write(constructor.getTableString());
    }
}