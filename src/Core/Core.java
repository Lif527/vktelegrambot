package Core;

import API.Telegram;
import Functions.Function;
import Functions.Game;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Core {
    private final ArrayList<Game> _games;
    private final ArrayList<Function> _functions;
    private boolean _gameStarted;
    private int _actualGame;

    public Core(ArrayList<Game> games) {
        _games = games;
        _gameStarted = false;
        _functions = null;
    }

    public Core(ArrayList<Game> games, ArrayList<Function> functions) {
        _games = games;
        _gameStarted = false;
        _functions = functions;
    }

    public String process(Message message) {
        if (_functions != null )
            for (int i = 0; i < _functions.size(); i++)
                if (message.getText().equals(_functions.get(i).getCommand()))
                    return _functions.get(i).getText();

        if (!_gameStarted) {
            for (int i = 0; i < _games.size(); i++) {
                if (message.getText().equals(_games.get(i).getStartCommand())) {
                    _actualGame = i;
                    _gameStarted = true;
                    return _games.get(i).getStartedText();
                }
            }
        }

        if (_gameStarted && message.getText().equals("/exit")) {
            _gameStarted = false;
            return _games.get(_actualGame).exitGame();
        }

        if (_gameStarted) {
            return _games.get(_actualGame).gameIteration(message.getText());
        }

        return "Not Found!";
    }
}
