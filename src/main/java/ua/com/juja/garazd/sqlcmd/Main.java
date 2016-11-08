package ua.com.juja.garazd.sqlcmd;

import ua.com.juja.garazd.sqlcmd.controller.MainController;
import ua.com.juja.garazd.sqlcmd.model.DatabaseManager;
import ua.com.juja.garazd.sqlcmd.model.PostgresDatabaseManager;
import ua.com.juja.garazd.sqlcmd.view.Console;
import ua.com.juja.garazd.sqlcmd.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new PostgresDatabaseManager();

        MainController controller = new MainController(manager, view);
        controller.run();
    }
}