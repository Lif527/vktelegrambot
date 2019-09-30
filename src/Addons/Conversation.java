package Addons;

import Functions.IFunction;

public class Conversation implements IFunction {
    private String _command = "Спасибо";
    private String[] _answers;

    public Conversation() {
        _answers = new String[3];
        _answers[0] = "Не за что!";
        _answers[1] = "Должен будешь";
        _answers[2] = "Пятница всегда на страже этой группы";
    }

    @Override
    public String getCommand() {
        return _command;
    }

    @Override
    public String getText() {
        return _answers[(int)Math.random() * 3];
    }
}
