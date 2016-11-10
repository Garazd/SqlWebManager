package ua.com.juja.garazd.sqlwebmanager;

import ua.com.juja.garazd.sqlwebmanager.controller.MainController;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.model.PostgresDatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.view.Console;
import ua.com.juja.garazd.sqlwebmanager.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new PostgresDatabaseManager();

        MainController controller = new MainController(manager, view);
        controller.run();
    }
}