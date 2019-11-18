import API.*;
import Addons.Help;
import Core.Core;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;

import java.util.ArrayList;
import java.util.HashMap;

public class Bot {
    public static void main(String[] args) {
        //games
        HashMap<String, Game> games = new HashMap<>();
        games.put("/start1", new GuessTheNumber());

        //functions
        HashMap<String, Function> functions = new HashMap<>();
        functions.put("/help", new Help());

        Core core = new Core(games, functions);

        Telegram.init(core);
    }
}