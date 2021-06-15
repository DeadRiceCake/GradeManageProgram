package Score_Manager_Package;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class ActDel_GUI {

	private JFrame frame;
	private JLabel lbl_1;
	private JTextField txt_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ActDel_GUI window = new ActDel_GUI();
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
	public ActDel_GUI(String id , String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id , String pw) {
		
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(100, 149, 237));
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		
		JButton btn_2 = new JButton("\uB4A4\uB85C");
		btn_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 다시 선생메인으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				TCMain_GUI TCMain_GUI = new TCMain_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn_2.setBounds(615, 381, 120, 40);
		frame.getContentPane().add(btn_2);
		
		txt_1 = new JTextField();
		txt_1.setBounds(333, 187, 117, 30);
		frame.getContentPane().add(txt_1);
		System.out.println(frame.getContentPane().add(txt_1));
		txt_1.setColumns(10);
		
		JLabel lbl_2 = new JLabel("STU_NO");
		lbl_2.setBounds(268, 186, 53, 30);
		frame.getContentPane().add(lbl_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\uC0AD\uC81C\uD558\uACE0\uC790 \uD558\uB294 \uD559\uC0DD\uC758 \uD559\uC0DD\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694");
		textPane.setBounds(248, 156, 273, 21);
		frame.getContentPane().add(textPane);
		
		JButton btn_3 = new JButton("\uC0AD\uC81C");
		btn_3.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Controller Controller = new Controller();
				int STU_NO = Integer.parseInt(txt_1.getText());
				
				Controller.deleteSCR(STU_NO);
				int cnt = Controller.deleteST(STU_NO);
				System.out.println(cnt);
				if (cnt!=0){
					String order= "삭제에 성공했습니다!";
					Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);
//					frame.setVisible(true);
					
				}else {
					String order= "삭제에 실패했습니다!";
					Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);
					
//					frame.setVisible(true);
				}
			
				
				
				
			}
		});
		btn_3.setBounds(400, 248, 120, 40);
		frame.getContentPane().add(btn_3);
		
		JButton btn_1 = new JButton("\uD559\uC0DD\uBC88\uD638\uC870\uD68C");
		btn_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		// 학생일련번호 조회로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				ActSel_GUI ActSel_GUI = new ActSel_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
				
			}
		});
		btn_1.setBounds(268, 248, 120, 40);
		frame.getContentPane().add(btn_1);
		
		lbl_1 = new JLabel("\uD559\uC0DD \uC815\uBCF4 \uC0AD\uC81C");
		lbl_1.setBounds(12, 25, 760, 58);
		frame.getContentPane().add(lbl_1);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_1.setFont(new Font("G마켓 산스 TTF Medium", Font.BOLD, 26));
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 760, 441);
		frame.getContentPane().add(lbl_background);
	}
}
