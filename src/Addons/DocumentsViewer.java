package Addons;

import Functions.IFunctionAttach;

import java.util.ArrayList;

public class DocumentsViewer implements IFunctionAttach {
    private String _command = "/viewDocs";
    private String _text = "Это все документы, которые у меня есть.";
    private ArrayList<String> _attachments = new ArrayList<String>();

    public DocumentsViewer() {
        _attachments.add("photo-160697310_457262189");
        _attachments.add("photo-31976785_457422524");
        _attachments.add("photo137000201_456240853");
    }

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
        return _attachments.toArray(new String[_attachments.size()]);
    }

    public void addAttach(String attach) {
        _attachments.add(attach);
    }
}
