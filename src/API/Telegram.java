package API;

import Functions.Game;
import Games.GuessTheNumber;
import Keys.ApiKeys;
import Debug.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Telegram extends TelegramLongPollingBot {
    Message message;
    private static ArrayList<Game> games;
    private int actualGame;
    private boolean gameStarted = false;

    public static void init(ArrayList<Game> gamesList) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        games = gamesList;
    }

    @Override
    public void onUpdateReceived(Update update) {
        message = update.getMessage();

        if (!gameStarted) {
            for (int i = 0; i < games.size(); i++) {
                if (message.getText().equals(games.get(i).getStartCommand())) {
                    sendMsg(games.get(i).getStartedText());
                    actualGame = i;
                    gameStarted = true;
                }
            }
        }

        if (gameStarted && message.getText().equals("/exit")) {
            gameStarted = false;
            sendMsg(games.get(actualGame).exitGame());
        }

        if (gameStarted) {
            sendMsg(games.get(actualGame).gameIteration(message.getText()));
        }
    }

    @Override
    public String getBotUsername() {
        return ApiKeys.TelegramName;
    }

    @Override
    public String getBotToken() {
        return ApiKeys.TelegramAPI;
    }

    public void sendMsg(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
