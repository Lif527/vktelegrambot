import API.*;
import Addons.Help;
import Addons.Top;
import Core.Core;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;
import Users.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class Bot {
    public static void main(String[] args) {
        //users
        Users users = new Users();

        //games
        HashMap<String, Game> games = new HashMap<>();
        games.put("/start1", new GuessTheNumber());

        //functions
        HashMap<String, Function> functions = new HashMap<>();
        functions.put("/help", new Help());
        functions.put("/top", new Top(users));

        Core core = new Core(games, functions, users);

        Telegram.init(core);
    }
}