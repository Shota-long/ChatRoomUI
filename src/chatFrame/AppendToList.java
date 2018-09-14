package chatFrame;

import MySocket.MsgSender;

import javax.swing.*;
import java.net.Socket;

public class AppendToList {
    private MsgSender msg;
    private chatFrame chat;
    public AppendToList(MsgSender msg){
        this.msg = msg;
        String list = msg.getMsgFromQueue();
        chat.model.addElement(list);
    }
}
