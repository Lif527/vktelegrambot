package Games;

import Functions.Iterationable;
import Instruments.Useful;
import Users.User;
import org.glassfish.grizzly.nio.transport.UDPNIOServerConnection;

import java.security.MessageDigest;
import java.sql.Time;
import java.util.*;

public class Math implements Iterationable {
    private int _score;
    private int _countTasks;
    private char[] _operations;
    private boolean _firstMessage;
    private int _lastSum;
    private boolean _isOverdue;
    private GameTimer timer;

    public Math() {
        _score = 0;
        _countTasks = 0;
        _operations = new char[] {'+', '-', '*', '/'};
        _firstMessage = true;
        timer = new GameTimer();
    }

    @Override
    public String iteration(String text) {
        Random rnd = new Random();
        char currentOperation = _operations[rnd.nextInt(_operations.length)];
        int max = 20;
        int min = 1;
        int firstNumber = rnd.nextInt((max - min) + 1) + min;
        int secondNumber = rnd.nextInt((max - min) + 1) + min;
        String message = "";

        if (_firstMessage)
        {
            _lastSum = firstNumber + secondNumber;
            _firstMessage = false;
            message +=  firstNumber + " + " + secondNumber + " = ?";
        }
        else
        {
            var userInput = Useful.tryParseInt(text);
            if (userInput == null)
                return "Я жду от тебя число!";
            if (userInput == _lastSum && !_isOverdue)
            {
                _score++;
                _countTasks++;
                message += "Правильно! Ты заработал 1 очко!\n";
            }
            else
            {
                if (_isOverdue)
                {
                    message += "Время вышло :(\n";
                }
                else {
                    _countTasks++;
                    message += "Неправильно!\n";
                }
                _isOverdue = false;
                timer.timerStop();
            }
            switch (currentOperation)
            {
                case '+':
                    _lastSum = firstNumber + secondNumber;
                    message += firstNumber + " + " + secondNumber + " = ?";
                    break;
                case '-':
                    _lastSum = firstNumber - secondNumber;
                    message += firstNumber + " - " + secondNumber + " = ?";
                    break;
                case '*':
                    _lastSum = firstNumber * secondNumber;
                    message += firstNumber + " * " + secondNumber + " = ?";
                    break;
                case '/':
                    _lastSum = firstNumber / secondNumber;
                    message += firstNumber + " / " + secondNumber + " = ?";
                    break;
            }
        }

        timer.timerStart();
        return message;
    }

    @Override
    public String start(User user) {
        return "Это математическая игра, в которой тебе на время нужно решать пример.\n" +
                "На каждый пример у тебя будет 5 секунд!\n" +
                "Если ты ГОТОВ, отправь любое сообщение.\n";
    }

    @Override
    public String getStartCommand() {
        return "/math";
    }

    @Override
    public String exit() {
        return "Всего примеров: " + _countTasks + "\n" +
                "Заработано очков: " + _score;
    }

    @Override
    public int getScores() {
        return _score;
    }

    private class GameTimer {
        Timer timer;
        TimerTask task;

        public GameTimer() {
            timer = new Timer(true);
        }

        public void timerStart() {
            task = new TimerTask() {
                public void run() {
                    _isOverdue = true;
                }
            };
            timer.schedule(task, 5000);
        }

        public void timerStop() {
            boolean cancel = task.cancel();
        }
    }
}
