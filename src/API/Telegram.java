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
import java.util.Random;

public class Telegram extends TelegramLongPollingBot {
    private String _adminId = "rivizoft";
    Message message;


    public static void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    GuessTheNumber game = new GuessTheNumber();
    @Override
    public void onUpdateReceived(Update update) {
        //Debug.addAction(message.getContact().getUserID().toString(), message.getChatId().toString(), "Пришло сообщение")
        message = update.getMessage();

        if(message.getText().equals("g") && !game.isPlaying()) {
            game.startGame();
            sendMsg(game.startedText());
            return;
        }
        else if(message.getText().equals("Выход")){
            game = null;
        }
        if (game.isPlaying()) {
            game.gameIteration(message.getText());
            sendMsg(game.getAnswer());
            if(!game.isPlaying())
                game = null;
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
