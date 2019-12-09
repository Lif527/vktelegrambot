package Tests;

import Games.Score21;
import org.junit.Assert;
import org.junit.Test;

public class Score21Test {
    @Test
    public void OnlyYes() {
        var game = new Score21();
        var actualAnswer = game.iteration("Hello").substring(0,17);
        Assert.assertEquals("Первые две карты:", actualAnswer);

        actualAnswer = game.iteration("").substring(0,15);
        Assert.assertEquals("Введите + или -", actualAnswer);

        var counter = 0;
        var checker = 0;
        while(checker < 7 && counter++ < 22) {
            var words = game.iteration("+").split(" ");
            checker = words.length;
        }
        Assert.assertEquals(true, counter < 21);
    }
}