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
    private Queue<String> queue = new LinkedList<String>();
    public static int flag = 0;
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
    @Override
    public void handleText(String s) {
        System.out.println("s");
        int n = s.indexOf("#");
        String mes0 = s.substring(0,n);
        System.out.println(mes0);
        String mes1 = s.substring(n+1,s.length()).trim();
        System.out.println(mes1);
        //在线列表处理
        if (mes0.equals(ChatUtil.FRIEND_LIST)) {
            if (!(mes1.equals(""))){
                String[] list = mes1.split(",");
                for (String friendlist : list) {
                    chat.model.addElement(friendlist.trim());
                    user_list.add(friendlist.trim());
                }
            }
        }
        else if (mes0.equals(ChatUtil.PRIVATE_CHAT)){
            personal = personalFrame.getInstance();
            System.out.println("加到队列中");
            MessgeMap messgeMap = MessgeMap.getInstance();;
                System.out.println("输出队列"+mes1);
                String sender_name = mes1.substring(1,mes1.indexOf("]"));
                System.out.println("sender_name"+sender_name);
                int i=0;
                if (flag == 0){
                    i = chat.model.indexOf(sender_name.trim());
                    System.out.println(i);
                }
                 else if (flag == 1 ){
                     i = chat.model.indexOf("<html><font color='red'>"+sender_name+"</font></html>");
                }
                chat.model.setElementAt("<html><font color='red'>"+sender_name+"</font></html>",i);
                flag = 1;
                messgeMap.addMessage(sender_name,mes1);
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
