package Games;

import Functions.Iterationable;
import API.Telegram;
import java.io.IOException;
import java.nio.file.*;

import Instruments.Useful;
import Users.User;
import com.google.inject.internal.cglib.core.$CollectionUtils;

import java.util.Random;

public class GuessTheNumber implements Iterationable {
    private final Random rnd;
    private int number;
    private int countAttempts;
    private int games;
    private int scores;

    public GuessTheNumber(){
        rnd = new Random();
        number = rnd.nextInt(100);
        countAttempts = 0;
        games = 0;
        scores = 0;
    }

    @Override
    public String iteration(String text) {
        var numberGot = Useful.tryParseInt(text);

        if (numberGot != null) {
            countAttempts++;
            if (numberGot > number)
                return "Неправильно! Слишком много";
            else if (numberGot < number)
                return "Неправильно! Слишком мало";
            else if (numberGot == number) {
                int prevNum = number;
                int prevCountAttempts = countAttempts;
                number = rnd.nextInt(100);
                games++;
                scores += getScores(prevCountAttempts);
                countAttempts = 0;
                return "Правильно, это " + prevNum + "\n" + "Кол-во попыток: " + prevCountAttempts +
                        "\nТы набрал " + getScores(prevCountAttempts) + " очков\n" + "Я загадал новое число!";
            }
        }

        return "Внимание! Пиши только числа :)";
    }

    @Override
    public String start(User user) {
        return "Я загадал число от 1 до 100! Угадай его,\n" +
                "чем меньше попыток у тебя будет, тем больше очков ты наберешь. " +
                "Чтобы выйти из игры введи: /exit";
    }


    @Override
    public String getStartCommand() {
        return "/start1";
    }

    @Override
    public String exit() {
        return "Ты завершил игру\n" +
                "-- Твоя статистика --\n" +
                "Игр сыграно: " + games + "\n" +
                "Набрано очков: " + scores;
    }

    @Override
    public int getScores() {
        return scores;
    }

    private int getScores(int count) {
        if (count > 4)
            return 5;
        else if (count >= 2 && count <= 4)
            return 10;
        else if (count == 1)
            return 30;

        return 0;
    }
}
