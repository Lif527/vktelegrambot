package Core;

import API.Telegram;
import Functions.Function;
import Functions.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Core {
    private ArrayList<Game> gamesList;
    private Telegram telegram;

    public Core() {
        gamesList = new ArrayList<Game>();
        telegram = new Telegram();
    }

    public void addGames(ArrayList<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public ArrayList<Game> getGames() {
        return gamesList;
    }
}
