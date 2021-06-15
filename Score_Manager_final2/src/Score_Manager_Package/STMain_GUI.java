package Score_Manager_Package;



import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class STMain_GUI {

	private JFrame frame;
	Controller controll = new Controller();
//	만드실VO 변수이름;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					STMain_GUI window = new STMain_GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public STMain_GUI(String id,String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}
	
//	public STMain_GUI(만드실VO user) {
//		this.변수이름 = user;
//		initialize();
//		frame.setVisible(true);
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id,String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_1 = new JLabel("\uC131\uC801 \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_1.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 38));
		lbl_1.setBounds(153, 20, 453, 74);
		frame.getContentPane().add(lbl_1);
		
		JTextPane txt_1 = new JTextPane();
		txt_1.setBackground(new Color(255, 255, 240));
		txt_1.setEditable(false);
		txt_1.setText("\r\n "+id+" \uB2D8 \uD658\uC601\uD569\uB2C8\uB2E4.\r\n\r\n");
		txt_1.setBounds(54, 104, 196, 156);
		frame.getContentPane().add(txt_1);
		
		JButton btn_1 = new JButton("\uC131\uC801\uC870\uD68C");
		btn_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 자신의 성적을 조회 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				STSelect_GUI StudentSelect_GUI = new STSelect_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
			}
		});
		btn_1.setBounds(273, 104, 145, 30);
		frame.getContentPane().add(btn_1);
		
		JButton btn_2 = new JButton("\uB0B4  \uC815\uBCF4\uC218\uC815");
		btn_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 자신의 정보를 수정하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				STUpdate_GUI StudentUpdate_GUI = new STUpdate_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
			}
		});
		btn_2.setBounds(461, 104, 145, 30);
		frame.getContentPane().add(btn_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u203B \uACF5 \uC9C0 \uC0AC \uD56D");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(40, 322, 196, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 240));
		textPane.setText(controll.getNotice());
		textPane.setEditable(false);
		textPane.setBounds(40, 358, 705, 74);
		frame.getContentPane().add(textPane);
		
		JButton btn_3 = new JButton("\uC131\uC801 \uBCC0\uB3D9 \uCD94\uC774");
		btn_3.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 학생별 성적변동추이를 차트로 보는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				STChartSelect STChartSelect = new STChartSelect();
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				STChartSelect.main(null);
				//3.기존창 닫기X
				
			}
		});
		btn_3.setBounds(273, 152, 145, 30);
		frame.getContentPane().add(btn_3);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login_GUI Login_GUI = new Login_GUI();
				Login_GUI.main(null);
				
			}
		});
		btnNewButton.setBounds(461, 153, 145, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel.setBounds(31, 30, 58, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 760, 441);
		frame.getContentPane().add(panel);
	}
}
