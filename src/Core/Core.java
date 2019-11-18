package Core;

import API.Telegram;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;
import Users.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Core {
    private final HashMap<String, Game> _games;
    private final HashMap<String, Function> _functions;
    private final HashMap<Long, User> _actualSessions;

    private boolean _gameStarted;
    private String _actualGame;

    public Core(HashMap<String, Game> games) {
        _games = games;
        _gameStarted = false;
        _functions = null;
        _actualSessions = new HashMap<>();
    }

    public Core(HashMap<String, Game> games, HashMap<String, Function> functions) {
        _games = games;
        _gameStarted = false;
        _functions = functions;
        _actualSessions = new HashMap<>();
    }

    public String process(Message message) {
        // not implement
        User currentUser;
        if (!_actualSessions.containsKey(message.getChatId())) {
            _actualSessions.put(message.getChatId(),
                    new User("User", message.getChatId(), null, message.getText()));
            currentUser = _actualSessions.get(message.getChatId());
        }
        else {
            currentUser = _actualSessions.get(message.getChatId());
            currentUser.setLastMessage(message.getText());
        }

        // Если это вспомогательная функция
       // if (_functions != null) {
        //    if (_functions.containsKey(message.getText()))
        //        return _functions.get(message.getText()).getText();
       // }

        // Если игрок хочет начать игру
        if (currentUser.getActualGame() == null) {
            currentUser.setActualGame(new GuessTheNumber());
            return currentUser.getActualGame().getStartedText();
        }


       // if (_gameStarted && message.getText().equals("/exit")) {
       //     _gameStarted = false;
       //     return _games.get(_actualGame).exitGame();
       // }

        if (currentUser.getActualGame() != null) {
            return currentUser.getActualGame().gameIteration(currentUser.getLastMessage());
        }

        return "404";
    }
}
