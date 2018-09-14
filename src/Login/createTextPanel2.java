package Login;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class createTextPanel2 {
	
	private JPanel panel5,panel6,panel7,panel8;
	private JLabel addrTab,portTab;
	private JTextField addrText,portText;
	private JButton submit,cancel;
	
	public JPanel createTextPanel2() {
		
		//设计布局
		panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel6 = new JPanel();
		panel6.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel7 = new JPanel();
		panel7.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel8 = new JPanel();
		panel8.setLayout(new GridLayout(3, 1));
		
		addrTab = new JLabel("地址ַ:");
		addrText = new JTextField(11);
		portTab = new JLabel("端口:");
		portText = new JTextField(11);
		submit = new JButton("连接");
		cancel = new JButton("断开");

		//把组件添加到panel1
		panel5.add(addrTab);
		panel5.add(addrText);
		panel6.add(portTab);
		panel6.add(portText);
		
		panel7.add(submit);
		panel7.add(cancel);
		
		panel8.add(panel5);
		panel8.add(panel6);
		panel8.add(panel7);
		return panel8;
		
	}

	
}
