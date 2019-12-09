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
        return "Привет, " + user.getName() + ". Я бот Бит. Со мной можно поиграть в разные игры: \n" +
                "Набери /start1 и я с тобой сыграю в игру \"Угадай число\"\n" +
                "Набери /start2 и я с тобой сыграю в игру \"21\"\n" +
                "Набери /start3 и я с тобой сыграю в математическую игру\n" +
                "/top - посмотреть ТОП игроков\n" +
                "/stat - посмотреть свою статистику\n";
    }
}
