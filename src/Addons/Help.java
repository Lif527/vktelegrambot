package Addons;

import Functions.IFunction;

public class Help implements IFunction {
    private String _command = "/help";
    private String _text = "Привет, меня зовут Пятница, я ваш Бот Староста\n" +
            "Я могу выполнять различные функции: \n" +
            "/timetable <дата> (вывод расписания на день)\n" +
            "/num (четность недели)\n" +
            "/homework <дата> (вывод ДЗ на дату)\n" +
            "/deadlines (задачи)\n" +
            "/attend (отметить посещаемость, доступно старосте)\n" +
            "/documents (присылаю доступные учебники/документы)\n";

    @Override
    public String getCommand() {
        return _command;
    }

    @Override
    public String getText() {
        return _text;
    }
}
