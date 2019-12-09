package Users;

import Functions.Game;

public class User {
    private String  _name;
    private int     _scores;
    private Long    _chatID;
    private Game    _actualGame;
    private String  _lastMessage;
    private Integer _id;

    public boolean userInGame = false;

    public User(String name, Long chatID, Integer id, Game actualGame, String message) {
        _name = name;
        _scores = 0;
        _chatID = chatID;
        _actualGame = actualGame;
        _id = id;
    }

    public void addScores(int scores) {
        _scores += scores;
    }

    public Integer getId()
    {
        return _id;
    }

    public Long getChatId()
    {
        return _chatID;
    }

    public String getName() {
        return _name;
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

    public String exitGame() {
        var game = _actualGame;
        _actualGame = null;
        return game.exit();
    }
}
