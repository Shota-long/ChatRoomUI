package Login;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chatFrame.chatFrame;

public class registerFrame extends JFrame{
	
	private JPanel panel1,panel2,panel3,panel4,panel5,panel6;
	private JLabel userTab,passTab,show,show1;//��ǩ
	private JTextField userText;
	private JPasswordField passField;
	private JButton regButton;
	public String userName;
	public String userPwd;
	
	private JFrame jf ;
	
	
	
	//窗体初始化
	public void init(){
		this.setTitle("注册页面");
		this.setSize(350,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public registerFrame() {
		
		init();
		//设计布局
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(5, 1));
		panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel6 = new JPanel();
		panel6.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jf = new JFrame();
		jf = this;
		
		userTab = new JLabel("账号");
		userText = new JTextField(11);
	
		passTab = new JLabel("密码:");
		passField = new JPasswordField(11);
		passField.setEchoChar('*');
		
		show = new JLabel("");
		show.setText("");
		
		show1 = new JLabel("");
		show1.setText("");
		
		regButton = new JButton("立即注册");
		regButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
					userName = userText.getText();
					userPwd = String.valueOf(passField.getPassword());
					
					boolean lc = new loginCheck(userName,userPwd).loginCheck();
					if(lc) {
						new loginFrame();
						jf.setVisible(false);
					}
					else show.setText("注册失败");
					
					System.out.println("用户名:"+userName);
					System.out.println("密码:"+userPwd);
			}
		}
				);

		//把组建添加到panel1
		panel1.add(userTab);
		panel1.add(userText);
		panel2.add(passTab);
		panel2.add(passField);
		panel5.add(show);
		panel6.add(show1);
	
		panel3.add(regButton);
		
		panel4.add(panel6);
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
		panel4.add(panel5);
		this.setContentPane(panel4);
		this.setVisible(true);
			
	}
}
