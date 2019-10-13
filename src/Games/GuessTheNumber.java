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
    private boolean playing;
    private String answer;

    public GuessTheNumber(){
        rnd = new Random();
        number = rnd.nextInt(100);
        playing = false;
        count = 0;
    }

    public String getNumber(){
        return Integer.toString(number);
    }

    public String getAnswer(){
        return answer;
    }

    public void countIncrease(){
        count++;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void gameIteration(String text) {
        var numberGot = Useful.tryParseInt(text);
        if(numberGot != null) {
            countIncrease();
            if (Integer.parseInt(text) > number)
                answer = "Неправильно! Слишком много";
            else if (Integer.parseInt(text) < number)
                answer = "Неправильно! Слишком мало";
            else if (Integer.parseInt(text) == number) {
                playing = false;
                answer = "Правильно, это " + number + "\n" + "Кол-во попыток: " + count;
                number = rnd.nextInt(100);
            }
        }
        else answer = "Внимание! Пиши только числа, спасибо :)";
    }

    @Override
    public String startedText() {
        return "Я загадал число от 1 до 100! Угадай его,\n" +
                "Если тебе вдруг надоест играть, ты сможешь всегда выйти,\n" +
                "командой Выход, но я сильно огорчусь.\n" +
                "Внимание! Пиши только числа, спасибо :)";
    }

    @Override
    public void startGame() {
        playing = true;
    }

}
