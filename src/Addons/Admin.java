package Addons;

import Functions.Game;
import Users.User;

public class Admin implements Game {
    private Integer _adminId;

    public Admin()
    {
        _adminId = 271705139;
    }

    @Override
    public String iteration(String text) {
        return "Панель работает: " + text;
    }

    @Override
    public String start(User user) {
        if (_adminId.compareTo(user.getId()) == 0)
            return "Вы вошли в режим администрирования.";
        else {
            user.setActualGame(null);
            return "Эта функция не доступна для вас.";
        }
    }

    @Override
    public String getStartCommand() {
        return "/admin";
    }

    @Override
    public String exit() {
        return "Вы вышли из режима администрирования.";
    }

    @Override
    public int getScores() {
        return 0;
    }
}
