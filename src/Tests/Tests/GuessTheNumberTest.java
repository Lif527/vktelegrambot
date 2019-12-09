package Tests;

import Games.GuessTheNumber;
import org.junit.Assert;
import org.junit.Test;

public class GuessTheNumberTest {
    @Test
    public void OutOfSection() {
        var game = new GuessTheNumber();
        var actual = game.iteration("110");
        var expected1 = ("Неправильно! Слишком много");
        Assert.assertEquals(expected1, actual);

        actual = game.iteration("-1");
        var expected2 = ("Неправильно! Слишком мало");
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void OnlyIntegers() {
        var game = new GuessTheNumber();
        var actual = game.iteration("Hello");
        var expected = ("Внимание! Пиши только числа :)");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void FindAnswer(){
        var game = new GuessTheNumber();
        var guessed = -1;
        var expected1 = "Неправильно! Слишком много";
        var expected2 = "Неправильно! Слишком мало";
        var answer = "";

        for(int i = 0; i < 101; i++){
            answer = game.iteration(Integer.toString(i));
            if(!answer.equals(expected1) && !answer.equals(expected2)){
                guessed = i;
                break;
            }
        }
        var score = (guessed + 1) > 4 ? 5 : (guessed + 1) > 1 ? 10 : 30;
        var expected = "Правильно, это " + guessed + "\n" + "Кол-во попыток: " + (guessed + 1) +
                "\nТы набрал " + score + " очков\n" + "Я загадал новое число!";

        Assert.assertEquals(expected,answer);
    }
}