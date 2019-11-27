package Users;

import java.util.ArrayList;
import java.util.Comparator;

public class Users {
    private final ArrayList<User> _users;

    public Users()
    {
        _users = new ArrayList<User>();
    }
    public Users(ArrayList<User> users)
    {
        _users = users;
    }

    public void addUser(User user)
    {
        _users.add(user);
    }

    public int getCountUsers()
    {
        return _users.size();
    }

    public User getUser(User user)
    {
        return _users.get(_users.indexOf(user));
    }

    public ArrayList<User> getAllUsers() {
        return _users;
    }
}
