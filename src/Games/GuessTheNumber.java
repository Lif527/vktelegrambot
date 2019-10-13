package Games;

import java.util.Random;

public class GuessTheNumber {
    private final Random rnd;
    private int number;
    private int count;
    public boolean isGame;

    public GuessTheNumber(){
        rnd = new Random();
        number = rnd.nextInt(100);
        isGame = true;
        count = 0;
    }

    public String GetNumber(){
        return Integer.toString(number);
    }

    public void CountIncrease(){
        count++;
    }
}
