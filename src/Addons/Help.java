package Addons;

import Functions.Function;
import Users.User;

public class Help implements Function {

    @Override
    public String getCommand() {
        return "/help";
    }

    @Override
    public String getText(User user) {
        return "Привет, я бот Бит. Со мной можно поиграть в разные игры: \n" +
                "Набери /start1 и я с тобой сыграю в игру \"Угадай число\"\n" +
                "Набери /start2 и я с тобой сыграю в игру \"21\"\n" +
                "/top - посмотреть ТОП игроков\n" +
                "/stat - посмотреть свою статистику\n";
    }
}
