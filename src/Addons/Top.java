package Addons;

import Functions.Function;
import Users.Users;
import Users.User;

public class Top implements Function {
    private Users _users;
    private int countOfTop;

    public Top(Users users)
    {
        _users = users;
       countOfTop = 5;
    }

    @Override
    public String getCommand() {
        return "/top";
    }

    @Override
    public String getText(User user) {
        String message = "Кол-во игроков: " + _users.getCountUsers() + "\n";

        for (int i = 0; i < _users.getCountUsers(); i++)
        {
            message += "" + (i+1) + ": " + _users.getAllUsers().get(i).getName() +
                    ", очков: " + _users.getAllUsers().get(i).getScores() + "\n";
        }

        return message;
    }
}
