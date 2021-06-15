package Score_Manager_Package;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TCMain_GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TCMain_GUI window = new TCMain_GUI();
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
	public TCMain_GUI(String id,String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id,String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn_4 = new JButton("\uD559\uC0DD \uC815\uBCF4 \uC0AD\uC81C");
		btn_4.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 학생의 정보를 삭제하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				ActDel_GUI AccountInfoDelete_GUI = new ActDel_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
			}
		});
		btn_4.setBounds(542, 109, 150, 30);
		frame.getContentPane().add(btn_4);
		
		JButton btn_3 = new JButton("\uD559\uC0DD \uC131\uC801 \uC785\uB825");
		btn_3.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 학생의 성적을 입력하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				ScoreInsert_GUI ScoreInsert_GUI = new ScoreInsert_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
			}
		});
		btn_3.setBounds(273, 161, 150, 30);
		frame.getContentPane().add(btn_3);
		
		JButton btn_1 = new JButton("\uD559\uC0DD \uC131\uC801\uC870\uD68C");
		btn_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 담당 반 전체 학생의 성적을 조회하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				TCSelect_GUI TCSelect_GUI = new TCSelect_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn_1.setBounds(273, 109, 150, 30);
		frame.getContentPane().add(btn_1);
		
		JButton btn_2 = new JButton("\uD559\uC0DD \uC815\uBCF4 \uC870\uD68C");
		btn_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 담당반 학생들의 정보를 조회하는 창

				//1. 이동하고 싶은 클래스의 객체 생성
				String order = "";
				ActSel_GUI ActSel_GUI = new ActSel_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn_2.setBounds(273, 268, 150, 30);
		frame.getContentPane().add(btn_2);
		
		JButton btn_5 = new JButton("\uD559\uC0DD \uC131\uC801 \uC218\uC815");
		btn_5.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 학생의 성적을 수정하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				ScoreUpdate_GUI ScoreUpdate_GUI = new ScoreUpdate_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn_5.setBounds(273, 216, 150, 30);
		frame.getContentPane().add(btn_5);
		
		JLabel lbl_2 = new JLabel("\uC131\uC801 \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		lbl_2.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 38));
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_2.setBounds(153, 20, 453, 74);
		frame.getContentPane().add(lbl_2);
		
		JTextPane txt_1 = new JTextPane();
		txt_1.setBackground(new Color(255, 255, 240));
		txt_1.setEditable(false);
		
		txt_1.setText(id+"님 환영합니다.");
		txt_1.setBounds(42, 113, 196, 156);
		frame.getContentPane().add(txt_1);
		
		JButton btn_9 = new JButton("\uACF5\uC9C0\uC0AC\uD56D");
		btn_9.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 공지사항 입력,수정 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				TCNotice_GUI TeacherNotice_GUI = new TCNotice_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
			}
		});
		btn_9.setBounds(542, 160, 150, 30);
		frame.getContentPane().add(btn_9);
		
		JButton btn_7 = new JButton("\uB0B4 \uC815\uBCF4 \uC218\uC815");
		btn_7.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 선생 자신의 정보를 수정하는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				TCUpdate_GUI TeacherUpdate_GUI = new TCUpdate_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn_7.setBounds(542, 217, 150, 30);
		frame.getContentPane().add(btn_7);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login_GUI Login_GUI = new Login_GUI();
				Login_GUI.main(null);
				
			}
		});
		btnNewButton.setBounds(542, 268, 150, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 30, 50, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 760, 441);
		frame.getContentPane().add(panel);
	}
}
