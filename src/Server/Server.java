package Server;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import MySocket.*;
public class Server {
	public MsgSender Msg;
	public Server() {
		int num = 0;
		try {
			ServerSocket ss = new ServerSocket(ChatUtil.PORT);
			System.out.println("服务器开启");
			while(true) {
				System.out.println("等待客户端连接");
				Socket socket = ss.accept();
				num++;
				System.out.println("第"+num+"个用户已连接成功！");
				Msg = new ReciveMsg(socket);
				Msg.read();
				//Msg.send("hello");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Server();
	}
	public class ReciveMsg extends MsgSender<TextArea>{

        public ReciveMsg(Socket socket) {
            super(socket);
            System.out.println("接收成功");
        }
        public void handleText(String s) {
        	System.out.println("fliter: " + s);
        	/*
            String[] mes = s.split("#");
            if (mes[0].equals(ChatUtil.OPEN_ROOM))
                Msg.send(s);
            else if (mes[0].equals(ChatUtil.CLOSE_ROOM))
                Msg.send(s);
            else if (mes[0].equals(ChatUtil.PUBLLIC_CHAT))
                Msg.send(s);*/
        }
    }
}
