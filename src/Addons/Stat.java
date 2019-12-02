package Addons;

import Functions.Function;
import Users.Users;
import Users.User;

public class Stat implements Function {
    @Override
    public String getCommand() {
        return "/stat";
    }

    @Override
    public String getText(User user) {
        String message = "Твоё количество очков: " + user.getScores();
        message += "\nТвой никнейм: " + user.getName();
        return message;
    }
}
