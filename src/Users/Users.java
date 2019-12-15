package Users;

import java.util.ArrayList;
import java.util.Comparator;

public class Users {
    private final static ArrayList<User> _users = new ArrayList<User>();

    public static void addUser(User user)
    {
        _users.add(user);
    }

    public static int getCountUsers()
    {
        return _users.size();
    }

    public static User getUser(int number)
    {
        if (number < 0 || number >= _users.size())
            return null;
        return _users.get(number);
    }

    public static ArrayList<User> getAllUsers()
    {
        return _users;
    }

    public static void deleteUser(int number)
    {
        if (number < 0 || number >= _users.size())
            return;
        _users.remove(number);
    }
}
