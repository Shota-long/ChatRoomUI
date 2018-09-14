package chatFrame;

import MySocket.MsgSender;
import Server.ChatUtil;
import javax.swing.*;
import java.util.*;

public class AppenToTextArea extends MsgSender<JTextArea> {

    private chatFrame chat;
    List user_list = new ArrayList();
    private personalFrame personal;

    public AppenToTextArea(String NetAddress, int port) { super(NetAddress, port); }
    @Override
    public void send(String s) {
        super.send(s);
    }
    public void Recive(chatFrame chat){
        this.chat = chat;
    }
    public void TranSportPersonal(personalFrame personal){
        this.personal = personal;
    }
    @Override
    public void handleText(String s) {
        String[] mes = s.split("#");
        if (mes[0].equals(ChatUtil.FRIEND_LIST)) {
            if (mes.length>1){
                String[] list = mes[1].split(",");
                for (String friendlist : list) {
                    chat.model.addElement(friendlist.trim());
                    user_list.add(friendlist.trim());
                }
            }
        }
        else if (mes[0].equals(ChatUtil.PRIVATE_CHAT)){
            System.out.println("私聊");
            Queue<String> queue = new LinkedList<>();
            String name = mes[1].substring(1,mes[1].indexOf("]"));
            System.out.println(name);
            int i = chat.model.indexOf(name.trim());
            chat.model.setElementAt("<html><font color='red'>"+name+"</font></html>",i);
            if(personal.name3.equals(name.trim())){
                personal.area1.append(mes[1]);
            }
        }
        else if (mes[0].equals(ChatUtil.OPEN_ROOM)){
            chat.model.addElement(mes[1]);
            user_list.add(mes[1]);
        }
        else if (mes[0].equals(ChatUtil.PUBLLIC_CHAT))
            ui.append(mes[1]+"\n");
        else if (mes[0].equals(ChatUtil.CLOSE_ROOM)){
            chat.model.clear();
            for (Iterator iterators = user_list.iterator();iterators.hasNext();){
                String list =  (String )iterators.next();
                if (list.equals(mes[1]))
                    iterators.remove();
            }
            for (Iterator iterators = user_list.iterator();iterators.hasNext();){
                 String list =  (String )iterators.next();
                 chat.model.addElement(list);
            }
        }

    }
}
