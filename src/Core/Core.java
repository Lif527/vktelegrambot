package Core;

import API.Telegram;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;
import Users.User;
import Users.Users;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Core {
    private final HashMap<String, Game>     _games;
    private final HashMap<String, Function> _functions;
    private final HashMap<Long, User>       _actualSessions;

    private Users _users;
    private boolean _gameStarted;
    private String _actualGame;

    public Core(HashMap<String, Game> games, Users users) {
        _games = games;
        _gameStarted = false;
        _functions = null;
        _actualSessions = new HashMap<>();
        _users = users;
    }

    public Core(HashMap<String, Game> games, HashMap<String, Function> functions, Users users) {
        _games = games;
        _gameStarted = false;
        _functions = functions;
        _actualSessions = new HashMap<>();
        _users = users;
    }

    public String process(Message message) {
        User currentUser;
        if (!_actualSessions.containsKey(message.getChatId()))
        {
            User usr = new User(message.getFrom().getFirstName(),
                    message.getChatId(), null, message.getText());
            _actualSessions.put(message.getChatId(), usr);
            _users.addUser(usr);
        }

        //Идентифицируем нашего пользователя
        currentUser = _actualSessions.get(message.getChatId());
        currentUser.setLastMessage(message.getText());

        // Если пользователь просит функцию без ответа
        if (_functions.containsKey(currentUser.getLastMessage())) {
            return _functions.get(currentUser.getLastMessage()).getText();
        }

        // Если игрок хочет начать игру
        if (currentUser.getActualGame() == null &&
                _games.containsKey(currentUser.getLastMessage()))
        {
            var game = _games.get(currentUser.getLastMessage()).getCopyGame();
            currentUser.setActualGame(game);
            return currentUser.getActualGame().getStartedText();
        }

        // Если игрок хочет выйти из игры
        if (currentUser.getActualGame() != null &&
                currentUser.getLastMessage().equals("/exit"))
        {
            currentUser.addScores(currentUser.getActualGame().getScores());
            return currentUser.exitGame();
        }

        // Если игрок в игре
        if (currentUser.getActualGame() != null) {
            return currentUser.getActualGame().gameIteration(currentUser.getLastMessage());
        }

        return "Привет, я бот Бит. Со мной можно поиграть в разные игры: \n" +
                "Набери /start1 и я с тобой сыграю в игру \"Угадай число\"\n" +
                "Набери /start2 и я с тобой сыграю в игру \"21\"\n" +
                "/top - посмотреть ТОП игроков\n" +
                "/stat - посмотреть свою статистику\n";
    }
}
