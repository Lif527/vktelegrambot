package Addons;

import Functions.Function;
import Users.Users;
import Users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Top implements Function {
    private int countOfTop;
    private ArrayList<User> _sortedUsers;

    public Top(Users users)
    {
       countOfTop = 10;
        _sortedUsers = Users.getAllUsers();
    }

    @Override
    public String getCommand() {
        return "/top";
    }

    @Override
    public String getText(User user) {
        sortUsers();
        String message = "Кол-во игроков: " + Users.getCountUsers() + "\n";

        for (int i = 0; i < _sortedUsers.size(); i++)
        {
            message += "" + (i+1) + ": " + _sortedUsers.get(i).getName() +
                    ", очков: " + _sortedUsers.get(i).getScores() + "\n";
        }

        return message;
    }

    private void sortUsers()
    {
        _sortedUsers.sort(Comparator.comparing(user -> user.getScores(), Comparator.reverseOrder()));
    }
}
