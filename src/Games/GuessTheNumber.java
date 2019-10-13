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
    private int count;
    private String answer;
    private int games;
    private int scores;

    public GuessTheNumber(){
        rnd = new Random();
        number = rnd.nextInt(100);
        count = 0;
        games = 0;
        scores = 0;
    }

    @Override
    public String gameIteration(String text) {
        var numberGot = Useful.tryParseInt(text);

        if (numberGot != null) {
            countIncrease();
            if (Integer.parseInt(text) > number)
                return "Неправильно! Слишком много";
            else if (Integer.parseInt(text) < number)
                return "Неправильно! Слишком мало";
            else if (Integer.parseInt(text) == number) {
                int prevNum = number;
                number = rnd.nextInt(100);
                games++;
                scores += count / 2;
                return "Правильно, это " + prevNum + "\n" + "Кол-во попыток: " + count +
                        "\nТы набрал " + count / 2 + " очков\n" + "Я загадал новое число!";
            }
        }

        return "Внимание! Пиши только числа :)";
    }

    @Override
    public String getStartedText() {
        return "Я загадал число от 1 до 100! Угадай его,\n" +
                "чем меньше попыток у тебя будет, тем больше очков ты наберешь." +
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
                "Набрано очков: " + scores;
    }

    public void countIncrease(){
        count++;
    }
}
