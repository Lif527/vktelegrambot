package Addons;

import Functions.IFunctionAttach;

public class DocumentsAdder implements IFunctionAttach {
    private String _command = "/addDoc";
    private String _text = "";

    @Override
    public String getCommand() {
        return _command;
    }

    @Override
    public String getText() {
        return _text;
    }

    @Override
    public String[] getAttachments() {
        return new String[0];
    }
}
