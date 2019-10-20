import API.*;
import Core.Core;
import Functions.Game;
import Games.GuessTheNumber;

import java.util.ArrayList;

public class Bot {
    public static void main(String[] args) {
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(new GuessTheNumber());

        Core core = new Core(games);

        Telegram.init(core);
    }
}