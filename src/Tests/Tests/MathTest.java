package Tests;

import Games.Math;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {
    @Test
    public void OnlyIntegers() {
        var game = new Math();
        var actual = game.iteration("Hello");
        actual = game.iteration("Hello");
        var expected = "Я жду от тебя число!";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void TooSlow(){
        var game = new Math();
        var move = game.iteration("Hello");
        try {Thread.sleep(5001);} catch(InterruptedException e) {}
        var answer = game.iteration("2");
        var actual = answer.substring(0,11);
        Assert.assertEquals("Время вышло", actual);
    }

    @Test
    public void Calculations(){
        var game = new Math();
        var move = game.iteration("Hello").split(" ");
        var first = Integer.parseInt(move[0]);
        var second = Integer.parseInt(move[2]);
        var expected = Solve(move[1], first, second);
        var actual = game.iteration(Integer.toString(expected)).substring(0,10);
        Assert.assertEquals("Правильно!", actual);

    }

    public Integer Solve(String operator, Integer firstNumber, Integer secondNumber){
        var answer = 0;
        switch (operator)
            {
                case "+":
                    answer = firstNumber + secondNumber;
                    break;
                case "-":
                    answer = firstNumber - secondNumber;
                    break;
                case "*":
                    answer = firstNumber * secondNumber;
                    break;
                case "/":
                    answer = firstNumber / secondNumber;
                    break;
            }
        return answer;
    }
}