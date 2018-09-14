package chatFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;//
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import Login.loginFrame;
import Server.ChatUtil;
public class chatFrame extends JFrame implements ActionListener{
	//定义组件
	//jpanel容器
	public JPanel p1,p2,p3,p4,p5,p6,p7;
	public JTextArea area1;
	public JTextArea area3;
	//滚动容器
	public JScrollPane js1,js3,js4;
	//按钮
	public JButton sendBut,refrashBut;
	//模型
	public DefaultListModel model;
	//好友列表
	public JList list;
	//ID
	public String name;

	public void init() {
		//定义窗体的特征
		//窗体标题
		//Msg.read();
		this.setTitle("["+name+"]的聊天室");
		this.setSize(520,570);
		this.setResizable(false);//禁用放大按钮
		this.setLocationRelativeTo(null);//窗体位置(居中显示)
		this.pack();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//窗体关闭
	
	}

	public chatFrame(String name) {

		this.name = name.trim();
		p1 = new JPanel();
		//设置p1的布局为网格布局
		p1.setLayout(new GridLayout(1, 1));
		//往p1中添加组件
		area1 = new JTextArea(21,10);
		//换行
		area1.setLineWrap(true);
		//实例化js1滚动容器
		js1 = new JScrollPane(area1);
		js1.setBorder(new TitledBorder("主聊天频道"));
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p1.add(js1);
		//实例化p2对象
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		//实例化p3
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		area3 = new JTextArea(2,35);
		area3.setLineWrap(true);
		js4 = new JScrollPane(area3);
		js4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );

		sendBut = new JButton("发送>>");
		//绑定事件
		sendBut.addActionListener(this);
		//把组件添加到p3
		p3.add(js4);
		p3.add(sendBut);
		//实例化p4
		p4 = new JPanel();
		p4.setLayout(new GridLayout(2, 1));
		p4.add(p2);
		p4.add(p3);
		//实例化p5
		p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(p1,BorderLayout.NORTH);
		p5.add(p4,BorderLayout.SOUTH);
		//实例化p6
		p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		model = new DefaultListModel();
		//model.addElement("龙胜");
		//model.addElement("<html><font color='red'>李四</font></html>");
		list = new JList(model);
		//好友列表点击事件
		MouseListener mouseClicked = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { 
					List values=list.getSelectedValuesList();
					String selectedName = values.get(0).toString().trim();
					System.out.println("被选择对象:"+selectedName);
					System.out.println(chatFrame.this.name);
					System.out.println(selectedName.equals(chatFrame.this.name));
					if( !(selectedName.equals(chatFrame.this.name)) ) {
						 personalFrame personal =  new personalFrame(chatFrame.this.name,selectedName);
						loginFrame.Msg.TranSportPersonal(personal);
					}
				} 
				}
		};
		list.addMouseListener(mouseClicked);
	
		list.setVisibleRowCount(18);
		list.setFixedCellHeight(24);
		list.setFixedCellWidth(70);

		
		js3 = new JScrollPane(list);
		js3.setBorder(new TitledBorder("好友列表"));
		js3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		p6.add(js3,BorderLayout.NORTH);

		p7 = new JPanel();
		p7.setLayout(new FlowLayout(FlowLayout.LEFT));
		p7.add(p5);
		p7.add(p6);

		this.getContentPane().add(p7);
		
		init();
		this.setVisible(true);
		//绑定关闭窗口事件
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					loginFrame.Msg.send(ChatUtil.CLOSE_ROOM+"#"+name);
					loginFrame.Msg.destroy();
					System.exit(0);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	////buttom监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
				if(e.getActionCommand().equals("发送>>")) {
					String message = area3.getText();
					loginFrame.Msg.send(ChatUtil.PUBLLIC_CHAT+"#["+name+"]:"+message);
					loginFrame.Msg.setTextArea(area1);
					loginFrame.Msg.Recive(this);
					area3.setText("");
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
		public void getSocket(){
			try {
				loginFrame.Msg.send(ChatUtil.OPEN_ROOM+"#"+name);
				loginFrame.Msg.Recive(this);
				loginFrame.Msg.setTextArea(area1);
				loginFrame.Msg.read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//	public static void main(String[] args){
//		chatFrame chat= new chatFrame("龙胜");
//		chat.model.addElement(chat.name);
//	}

}
