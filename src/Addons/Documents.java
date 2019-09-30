package Addons;

import Functions.IFunction;

public class Documents implements IFunction {
    private String _command = "/documents";
    private String _text = "Вы перешли в раздел документов\n" +
            "/addDoc <ссылка на файл> - Добавить новый документ\n" +
            "/viewDocs - Посмотреть все документы";

    @Override
    public String getCommand() {
        return _command;
    }

    @Override
    public String getText() {
        return _text;
    }
}