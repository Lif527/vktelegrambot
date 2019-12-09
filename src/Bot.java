import API.*;
import Addons.Admin;
import Addons.Help;
import Addons.Stat;
import Addons.Top;
import Core.Core;
import Functions.Function;
import Functions.Game;
import Games.GuessTheNumber;
import Games.Math;
import Games.Score21;
import Users.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public class Bot {
    public static void main(String[] args) {
        //users
        Users users = new Users();

        //games
        HashMap<String, Supplier<Game>> games = new HashMap<>();
        games.put("/start1", GuessTheNumber::new);
        games.put("/start2", Score21::new);
        games.put("/start3", Math::new);
        games.put("/admin", Admin::new);

        //functions
        HashMap<String, Function> functions = new HashMap<>();
        functions.put("/help",  new Help());
        functions.put("/top",   new Top(users));
        functions.put("/stat",  new Stat());

        Core core = new Core(games, functions, users);

        Telegram.init(core);
    }
}