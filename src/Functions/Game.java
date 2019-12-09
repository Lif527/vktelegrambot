package Functions;

import Users.User;

public interface Game {
    public String iteration(String text);
    public String start(User user);
    public String getStartCommand();
    public String exit();
    public int    getScores();
}
