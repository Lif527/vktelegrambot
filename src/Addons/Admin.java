package Addons;

import Functions.Game;
import Instruments.Useful;
import Users.User;
import Users.Users;

public class Admin implements Game {
    private Integer _adminId;

    public Admin()
    {
        _adminId = 271705139;
    }

    @Override
    public String iteration(String text)
    {
        var _msg = text.split(" ");

        if (text.equals("/users")) {
            String message = "Кол-во пользователей: " + Users.getCountUsers() + "\n\n";
            for (int i = 0; i < Users.getCountUsers(); i++)
            {
                message += (i + 1) + ": " + Users.getUser(i).getName() + "\n";
            }
            return message;
        }

        if (text.contains("/addScore"))
        {
            if (_msg.length < 3)
                return "Введите аргументы!";
            Integer numberUser = Useful.tryParseInt(_msg[1]);
            Integer needScores = Useful.tryParseInt(_msg[2]);
            if (numberUser == null || needScores == null)
                return "Пиши только числа";
            numberUser--;
            if (Users.getUser(numberUser) == null)
                return "Такого пользователя не существует";
            Users.getUser(numberUser).addScores(needScores);
            return "Выполнено.";
        }

        if (text.contains("/deleteUser"))
        {
            if (_msg.length < 2)
                return "Введите аргументы!";
            Integer numberUser = Useful.tryParseInt(_msg[1]);
            if (numberUser == null)
                return "Пиши только числа";
            numberUser--;
            if (Users.getUser(numberUser) == null)
                return "Такого пользователя не существует";
            Users.deleteUser(numberUser);
            return "Выполнено.";
        }

        if (text.contains("/info"))
        {
            if (_msg.length < 2)
                return "Введите аргументы!";
            Integer numberUser = Useful.tryParseInt(_msg[1]);
            if (numberUser == null)
                return "Пиши только числа";
            numberUser--;
            if (Users.getUser(numberUser) == null)
                return "Такого пользователя не существует";
            var usr = Users.getUser(numberUser);
            return "Имя: " + usr.getName() + "\n" +
                    "ID: " + usr.getId() + "\n" +
                    "Кол-во очков: " + usr.getScores() + "\n" +
                    "Последнее сообщение: " + usr.getLastMessage() + "\n" +
                    "Chat ID: " + usr.getChatId();
        }

        return "error: неизвестная команда";
    }

    @Override
    public String start(User user)
    {
        if (_adminId.equals(user.getId()))
            return "Вы вошли в режим администрирования.\n" +
                    "/users - вывод всех пользователей\n" +
                    "/addScore !номер пользователя! !кол-во очков! - добавить очки пользователю\n" +
                    "/deleteUser !номер пользователя! - удалить пользователя\n" +
                    "/info !номер пользователя!\n";
        else {
            //Если id не совпадает выкидываем пользователя из игры
            user.setActualGame(null);
            return "Эта функция не доступна для вас.";
        }
    }

    @Override
    public String getStartCommand()
    {
        return "/admin";
    }

    @Override
    public String exit()
    {
        return "Вы вышли из режима администрирования.";
    }

    @Override
    public int getScores() {
        return 0;
    }
}
