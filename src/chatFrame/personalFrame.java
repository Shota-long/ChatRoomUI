package chatFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import Login.loginFrame;
import Server.ChatUtil;

public class personalFrame extends JFrame implements ActionListener{

	private JPanel p1,p2,p3,p4,p5;
	public  JTextArea area1;
	private JScrollPane js1,js2,js3,js4;
	private JLabel[] smile ;
	private String[] smile_lab;
	private JButton sendBut;
	private DefaultListModel model;
	private static JTextArea area2;

	public String user_send;
	private String user_recv;
	private static personalFrame instance;

	private personalFrame()
	{
		//set p1
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		area1 = new JTextArea(13,10);
		area1.setFont(new Font("dialog",0,20));
		area1.setLineWrap(true);
		js1 = new JScrollPane(area1);
		js1.setBorder(new TitledBorder("个人聊天"));
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		p1.add(js1);


		//set p2
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		smile_lab = new String[]{"😂","😘","😍","😊","😁",
				"😭","😜","😝","☺","😄",
				"😡","😀","😥","🙃","😋",
				"👍","👌","❤","😱","🐷"};
		smile = new JLabel[smile_lab.length];
		for (int i = 0 ; i<smile_lab.length ; i++) {
			smile[i] = new JLabel(smile_lab[i]);
			smile[i].setFont(new Font("dialog",0,20));
			smile[i].addMouseListener(new MyMouseListener());
		}
		for (int i = 0; i< smile_lab.length; i++)
		{
			p2.add(smile[i]);
		}


		//set p3
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		area2 = new JTextArea(2,35);
		area2.setLineWrap(true);
		js4 = new JScrollPane(area2);
		js4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		sendBut = new JButton("发送>>");
		sendBut.addActionListener(this);
		p3.add(js4);
		p3.add(sendBut);


		//set p4
		p4 = new JPanel();
		p4.setLayout(new GridLayout(2, 1));
		p4.add(p2);
		p4.add(p3);


		//set p5
		p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(p1,BorderLayout.NORTH);
		p5.add(p4,BorderLayout.SOUTH);
		this.getContentPane().add(p5);
	}


	private void setWindow()
	{
		this.setTitle("TO " + this.user_recv);
		this.setSize(520,570);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	public void setName(String user_send, String html_source)
	{
		this.user_send = user_send;
		this.user_recv = parse(html_source);
		setWindow();
		showWindow();
		instance.area1.setText("");
		int size = instance.area1.getText().length();
		instance.area1.replaceRange("",0,size);
		addMessage();
	}

	private void showWindow()
	{
		this.setVisible(true);
		this.setExtendedState(NORMAL);
		this.requestFocus();
	}


	public static personalFrame getInstance()
	{
		if (instance == null)
			instance = new personalFrame();
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
			String message = area2.getText();
			loginFrame.Msg.send(ChatUtil.PRIVATE_CHAT+"#["+user_send+"]To["+user_recv+"]:"+message);
			area1.append("["+user_send+"]:"+message+"\n");
			area2.setText("");
	}

	public String parse(String name)
	{
		String expr = "'>(.+?)</";
		Pattern r = Pattern.compile(expr);
		Matcher m = r.matcher(name);
		if (m.find())
		{
			System.out.println(m.group(1));
			return m.group(1);
		}
		else return name;
	}
	private void addMessage(){
		MessgeMap messgeMap;
		messgeMap = MessgeMap.getInstance();
		Queue<String> messageQueue= messgeMap.getMessageQueue(this.user_recv);
		if (messageQueue == null)
			return;
		for (String q:messageQueue) {
			System.out.println("queue:"+q);
		}
		System.out.println("\n");
		for (String q:messageQueue) {
			area1.append(q+"\n");
			//messageQueue.
		}
	}
	class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==1){
				JLabel Recive_simle = (JLabel) e.getComponent();
				System.out.println(Recive_simle.getText());
				area2.append(Recive_simle.getText());
			}
		}
	}
}
