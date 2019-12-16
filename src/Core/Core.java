package Core;

import API.Telegram;
import Functions.Function;
import Functions.Iterationable;
import Games.GuessTheNumber;
import Users.User;
import Users.Users;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

public class Core {
    private final HashMap<String, Supplier<Iterationable>> _games;
    private final HashMap<String, Function> _functions;
    private final HashMap<Long, User>       _actualSessions;

    public Core(HashMap<String, Supplier<Iterationable>> games) {
        _games = games;
        _functions = null;
        _actualSessions = new HashMap<>();
    }

    public Core(HashMap<String, Supplier<Iterationable>> games, HashMap<String, Function> functions) {
        _games = games;
        _functions = functions;
        _actualSessions = new HashMap<>();
    }

    public String process(Message message) {
        User currentUser;
        if (!_actualSessions.containsKey(message.getChatId()))
        {
            User usr = new User(
                    message.getFrom().getFirstName(),
                    message.getChatId(),
                    message.getFrom().getId(),
                    null,
                    message.getText()
            );
            _actualSessions.put(message.getChatId(), usr);
            Users.addUser(usr);
        }

        //Идентифицируем нашего пользователя
        currentUser = _actualSessions.get(message.getChatId());
        currentUser.setLastMessage(message.getText());

        // Если пользователь просит функцию без ответа и он не в игре
        if (_functions.containsKey(currentUser.getLastMessage()) &&
                currentUser.getActualGame() == null) {
            return _functions.get(currentUser.getLastMessage()).getText(currentUser);
        }

        // Если игрок хочет начать игру
        if (currentUser.getActualGame() == null &&
                _games.containsKey(currentUser.getLastMessage()))
        {
            var game = _games.get(currentUser.getLastMessage()).get();
            currentUser.setActualGame(game);
            return currentUser.getActualGame().start(currentUser);
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
            return currentUser.getActualGame().iteration(currentUser.getLastMessage());
        }

        return "Привет, я бот Бит. Со мной можно поиграть в разные игры: \n" +
                "Набери /start1 и я с тобой сыграю в игру \"Угадай число\"\n" +
                "Набери /start2 и я с тобой сыграю в игру \"21\"\n" +
                "Набери /start3 и я с тобой сыграю в Математическую игру\n" +
                "/top - посмотреть ТОП игроков\n" +
                "/stat - посмотреть свою статистику\n";
    }
}
