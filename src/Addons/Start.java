package Addons;

import Functions.IFunction;

public class Start implements IFunction {
    private String _command = "Начать";
    private String _text = "Привет, я Пятница, чтобы узнать, что я могу делать, введи команду /help";

    @Override
    public String getCommand() {
        return _command;
    }

    @Override
    public String getText() {
        return _text;
    }
}
