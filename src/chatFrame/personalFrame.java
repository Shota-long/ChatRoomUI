package chatFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		public String name1,name2,name3;
		public JPanel p1,p2,p3,p4,p5;
		public JTextArea area1;
		public static JTextArea area2;
		public JScrollPane js1,js2,js3,js4;
		public JLabel lab;
		public JButton sendBut;
		public DefaultListModel model;
	public void init() {
		this.setTitle("To "+name3);
		this.setSize(520,570);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}
	public personalFrame(String name1, String name2){
		this.name1 = name1;
		this.name2 = name2;
		System.out.println("name2:"+name2);
		 this.name3 = parse(name2);
		System.out.println("name:"+name3);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		area1 = new JTextArea(21,10);
		area1.setLineWrap(true);
		js1 = new JScrollPane(area1);
		js1.setBorder(new TitledBorder("个人聊天"));
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p1.add(js1);

		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		

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

		p4 = new JPanel();
		p4.setLayout(new GridLayout(2, 1));
		p4.add(p2);
		p4.add(p3);

		p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(p1,BorderLayout.NORTH);
		p5.add(p4,BorderLayout.SOUTH);
		this.getContentPane().add(p5);
		init();
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			String message = area2.getText();
			loginFrame.Msg.send(ChatUtil.PRIVATE_CHAT+"#["+name1+"]To["+name3+"]:"+message);
			area1.append("["+name1+"]:"+message);
			area2.setText("");
	}
	public String parse(String name){
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
}
