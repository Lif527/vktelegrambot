package Users;

import Functions.Game;

public class User {
    private String _name;
    private int _scores;
    private Long _chatID;
    private Game _actualGame;
    private String _lastMessage;

    public boolean userInGame = false;

    public User(String name, Long chatID, Game actualGame, String message) {
        _name = name;
        _scores = 0;
        _chatID = chatID;
        _actualGame = actualGame;
    }

    public void addScores(int scores) {
        _scores += scores;
    }

    public int getScores() {
        return _scores;
    }

    public Game getActualGame() {
        return _actualGame;
    }

    public void setActualGame(Game game) {
        _actualGame = game;
    }

    public void setLastMessage(String message) {
        _lastMessage = message;
    }

    public String getLastMessage() {
        return _lastMessage;
    }
}
