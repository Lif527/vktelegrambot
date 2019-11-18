package Core;

import API.Telegram;
import Functions.Function;
import Functions.Game;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Core {
    private final HashMap<String, Game> _games;
    private final HashMap<String, Function> _functions;
    private final HashMap<Long, String> _actualSessions;

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
        if (!_actualSessions.containsKey(message.getChatId())) {
            _actualSessions.put(message.getChatId(), message.getText());
        }
        else {
            _actualSessions.replace(message.getChatId(), message.getText());
        }

        // Если это вспомогательная функция
        if (_functions != null) {
            if (_functions.containsKey(message.getText()))
                return _functions.get(message.getText()).getText();
        }

        // Если игрок хочет начать игру
        if (!_gameStarted) {
            if (_games.containsKey(message.getText())) {
                _actualGame = message.getText();
                _gameStarted = true;
                return _games.get(message.getText()).getStartedText();
            }
        }

        if (_gameStarted && message.getText().equals("/exit")) {
            _gameStarted = false;
            return _games.get(_actualGame).exitGame();
        }

        if (_gameStarted) {
            return _games.get(_actualGame).gameIteration(message.getText());
        }

        return "404";
    }
}
