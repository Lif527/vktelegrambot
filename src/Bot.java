import API.*;
import Addons.Help;
import Core.Core;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;

import java.util.ArrayList;

public class Bot {
    public static void main(String[] args) {
        //games
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(new GuessTheNumber());

        //functions
        ArrayList<Function> functions = new ArrayList<>();
        functions.add(new Help());

        Core core = new Core(games, functions);

        Telegram.init(core);
    }
}