package Games;

import Functions.Game;
import API.Telegram;
import java.io.IOException;
import java.nio.file.*;

import Instruments.Useful;
import java.util.Random;

public class GuessTheNumber implements Game {
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
    public String gameIteration(String text) {
        var numberGot = Useful.tryParseInt(text);

        if (numberGot != null) {
            countAttempts++;
            if (numberGot > number)
                return "Неправильно! Слишком много";
            else if (numberGot < number)
                return "Неправильно! Слишком мало";
            else if (numberGot == number) {
                int prevNum = number;
                number = rnd.nextInt(100);
                games++;
                scores += countAttempts / 2;
                return "Правильно, это " + prevNum + "\n" + "Кол-во попыток: " + countAttempts +
                        "\nТы набрал " + countAttempts / 2 + " очков\n" + "Я загадал новое число!";
            }
        }

        return "Внимание! Пиши только числа :)";
    }

    @Override
    public String getStartedText() {
        return "Я загадал число от 1 до 100! Угадай его,\n" +
                "чем меньше попыток у тебя будет, тем больше очков ты наберешь. " +
                "Чтобы выйти из игры введи: /exit";
    }

    @Override
    public String getStartCommand() {
        return "/start1";
    }

    @Override
    public String exitGame() {
        return "Ты завершил игру\n" +
                "--Твоя статистика--\n" +
                "Игр сыграно: " + games + "\n" +
                "Набрано очков: " + countAttempts;
    }
}
