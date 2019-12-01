package API;

import Core.Core;
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
    private Message message;
    private static Core _core;

    public static void init(Core core) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        _core = core;

        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        message = update.getMessage();
        sendMsg(_core.process(message));
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
        sendMessage.setText(text.replaceAll("([_*`])", "\\\\$1"));

        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
