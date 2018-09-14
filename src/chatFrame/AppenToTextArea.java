package chatFrame;

import Login.createTextPanel1;
import Login.loginFrame;
import Login.registerFrame;
import MySocket.MsgSender;
import Server.ChatUtil;
import javax.swing.*;
import java.util.*;

public class AppenToTextArea extends MsgSender<JTextArea> {

    private chatFrame chat;
    private registerFrame register;
    List user_list = new ArrayList();
    private personalFrame personal;
    private loginFrame textPanel1;
    private String login_name;

    public AppenToTextArea(String NetAddress, int port) { super(NetAddress, port); }
    @Override
    public void send(String s) {
        super.send(s);
    }
    public void RecivechatFrame(chatFrame chat){
        this.chat = chat;
    }
    public void ReciveregisterFrame(registerFrame register){this.register = register;}
    public void ReciveLoginName(loginFrame textPanel1,String login_name){
        this.textPanel1 = textPanel1;
        this.login_name = login_name;
    }
    public void TranSportPersonal(personalFrame personal){
        this.personal = personal;
    }
    @Override
    public void handleText(String s) {
        int n = s.indexOf("#");
        System.out.println(n);
        String mes0 = s.substring(0,n);
        String mes1 = s.substring(n+1,s.length()).trim();
        System.out.println("mes0"+mes0);
        System.out.println("mes1"+mes1);
        //在线列表处理
        if (mes0.equals(ChatUtil.FRIEND_LIST)) {
            System.out.println(mes1.equals(""));
            if (!(mes1.equals(""))){
                String[] list = mes1.split(",");
                for (String friendlist : list) {
                    chat.model.addElement(friendlist.trim());
                    user_list.add(friendlist.trim());
                }
            }
        }
        else if (mes0.equals(ChatUtil.PRIVATE_CHAT)){
            System.out.println("私聊");
            Queue<String> queue = new LinkedList<String>();
            queue.offer(mes1);
            for (String q : queue)
            {
                System.out.println("msg:"+q);
                String sender_name = q.substring(1,mes1.indexOf("]"));
                System.out.println("sender_name:"+sender_name);
                int i = chat.model.indexOf(sender_name.trim());
                chat.model.setElementAt("<html><font color='red'>"+sender_name+"</font></html>",i);
                if(personal.name3.equals(sender_name.trim())){
                    personal.area1.append(q);
                }
            }
        }

        else if (mes0.equals(ChatUtil.OPEN_ROOM)){
            chat.model.addElement(mes1);
            user_list.add(mes1);
        }
        //把消息添加到聊天框
        else if (mes0.equals(ChatUtil.PUBLLIC_CHAT))
            ui.append(mes1+"\n");
        //退出修改在线列表
        else if (mes0.equals(ChatUtil.CLOSE_ROOM)){
            chat.model.clear();
            for (Iterator iterators = user_list.iterator();iterators.hasNext();){
                String list =  (String )iterators.next();
                if (list.equals(mes1))
                    iterators.remove();
            }
            for (Iterator iterators = user_list.iterator();iterators.hasNext();){
                 String list =  (String )iterators.next();
                 chat.model.addElement(list);
            }
        }
        //注册
        else if(mes0.equals(ChatUtil.SIGN_IN)){
            System.out.println("验证!");
            if (mes1.trim().equals("true")) {
                new loginFrame();
                register.dispose();
            }
            else if (mes1.trim().equals("false"))
                register.show.setText("注册失败");
            else
                System.out.println("异常");
        }
        //登录验证
        else if (mes0.equals(ChatUtil.LOGIN_CHECK)){
            if (mes1.trim().equals("true")) {
                chatFrame chat= new chatFrame(login_name);
                chat.getSocket();
                textPanel1.dispose();
            }
            if (mes1.trim().equals("false"))
                createTextPanel1.show.setText("账号密码错误");
        }

    }
}
