package Addons;

import Functions.Function;

public class Help implements Function {

    @Override
    public String getCommand() {
        return "/help";
    }

    @Override
    public String getText() {
        return "Привет, меня зовут Пятница и со мной можно поиграть в разные игры:\n" +
                "Чтобы сыграть со мной в \"Угадай число\" введи /start1";
    }
}
