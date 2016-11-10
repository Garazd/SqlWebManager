package ua.com.juja.garazd.sqlwebmanager.controller.command;

import ua.com.juja.garazd.sqlwebmanager.view.View;

public class FakeView implements View {

    private String massages = "";
    private String input;

    @Override
    public void write(String massage) {
        massages += massage + "\n";
    }

    @Override
    public String read() {
        if (this.input == null) {
            throw new IllegalArgumentException("To work initialize method read");
        }
        String result = this.input;
        this.input = null;
        return result;
    }

    public void addRead(String input) {
        this.input = input;
    }

    public String getContent() {
        return massages;
    }
}