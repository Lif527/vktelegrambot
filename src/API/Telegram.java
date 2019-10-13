package API;

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
import java.util.Random;

public class Telegram extends TelegramLongPollingBot {
    private String _adminId = "rivizoft";
    private Random rnd = new Random();
    private int number = rnd.nextInt(100);
    private int count = 0;
    private boolean isGame = false;

    public static void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        //Debug.addAction(message.getContact().getUserID().toString(), message.getChatId().toString(), "Пришло сообщение")

        if (!isGame) {
            sendMsg(message, "Я загадал число от 1 до 100! Угадай его");
            isGame = true;
            return;
        }

        count++;

        if (isGame && Integer.parseInt(message.getText()) > number)
            sendMsg(message, "Неправильно! Слишком много");

        if (isGame && Integer.parseInt(message.getText()) < number)
            sendMsg(message, "Неправильно! Слишком мало");

        if (isGame && Integer.parseInt(message.getText()) == number) {
            isGame = false;
            sendMsg(message, "Правильно, это " + number);
            sendMsg(message, "Кол-во попыток: " + count);
            number = rnd.nextInt(100);
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

    private void sendMsg(Message message, String text) {
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
