package ua.com.juja.garazd.sqlwebmanager.controller.command;

import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.view.View;

public class DisconnectDatabase implements Command {
    private DatabaseManager manager;
    private View view;

    public DisconnectDatabase(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("disconnect");
    }

    @Override
    public void process(String command) {
        manager.disconnectDatabase();
        view.write("Disconnected");
    }
}