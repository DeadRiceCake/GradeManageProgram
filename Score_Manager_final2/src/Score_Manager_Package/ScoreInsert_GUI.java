package Score_Manager_Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ScoreInsert_GUI {

	private JFrame frame;
	private JTextField txt_01;
	private JTextField txt_02;
	private JTextField txt_03;
	private JTextField txt_04;
	private JTextField txt_05;
	private JTextField txt_06;
	private JButton btn_01;
	private JComboBox comboBox;
	private JButton btn_02;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScoreInsert_GUI window = new ScoreInsert_GUI();
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
	public ScoreInsert_GUI(String id, String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 481, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC131\uC801 \uC785\uB825");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Light", Font.BOLD, 23));
		lblNewLabel.setBounds(138, 10, 164, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD559\uC0DD\uBC88\uD638\uC785\uB825");
		lblNewLabel_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(49, 80, 77, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		txt_01 = new JTextField();
		txt_01.setText("");
		txt_01.setBounds(138, 77, 116, 21);
		frame.getContentPane().add(txt_01);
		txt_01.setColumns(10);
		
		txt_02 = new JTextField();
		txt_02.setBounds(138, 108, 116, 21);
		frame.getContentPane().add(txt_02);
		txt_02.setColumns(10);
		
		txt_03 = new JTextField();
		txt_03.setBounds(138, 139, 116, 21);
		frame.getContentPane().add(txt_03);
		txt_03.setColumns(10);
		
		txt_04 = new JTextField();
		txt_04.setBounds(138, 170, 116, 21);
		frame.getContentPane().add(txt_04);
		txt_04.setColumns(10);
		
		txt_05 = new JTextField();
		txt_05.setBounds(138, 201, 116, 21);
		frame.getContentPane().add(txt_05);
		txt_05.setColumns(10);
		
		txt_06 = new JTextField();
		txt_06.setBounds(138, 233, 116, 21);
		frame.getContentPane().add(txt_06);
		txt_06.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uAD6D\uC5B4");
		lblNewLabel_2.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setBounds(69, 111, 50, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC601\uC5B4");
		lblNewLabel_2_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(69, 142, 50, 15);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uC218\uD559");
		lblNewLabel_2_2.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(69, 173, 50, 15);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("\uC0AC\uD68C");
		lblNewLabel_2_3.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setBounds(69, 204, 50, 15);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("\uACFC\uD559");
		lblNewLabel_2_4.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 12));
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_4.setBounds(69, 236, 50, 15);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "1\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC", "2\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "2\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC"}));
		comboBox.setEditable(true);
		comboBox.setBounds(280, 76, 147, 23);
		frame.getContentPane().add(comboBox);
		
		btn_01 = new JButton("\uC785\uB825");
		btn_01.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btn_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int stu_no = Integer.parseInt(txt_01.getText());
				int kor = Integer.parseInt(txt_02.getText());
				int eng = Integer.parseInt(txt_03.getText());
				int math = Integer.parseInt(txt_04.getText());
				int society = Integer.parseInt(txt_05.getText());
				int science = Integer.parseInt(txt_06.getText());
				String test_day = comboBox.getSelectedItem().toString();
				
				if(test_day.equals("1학기 중간고사")) {
					test_day = "1-m";
				}else if(test_day.equals("1학기 기말고사")) {
					test_day = "1-e";
				}else if(test_day.equals("2학기 중간고사")) {
					test_day = "2-m";
				}else if(test_day.equals("2학기 기말고사")) {
					test_day = "2-e";
				}
				Controller controll = new Controller();
				int cnt = 0;
				cnt = controll.insertScore(stu_no, kor, eng, math, society, science, test_day);
				if (cnt!=0) {
					String order = stu_no+"번 학생의 성적입력에 성공하였습니다,";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
				}else {
					String order = stu_no+"학생의 성적입력에 실패하였습니다,";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
				}
			}
		});
		btn_01.setBounds(120, 286, 100, 30);
		frame.getContentPane().add(btn_01);
		
		
		
		btn_02 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btn_02.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btn_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TCMain_GUI TCMain_GUI = new TCMain_GUI(id,pw);
        	 frame.setVisible(true);
        	 frame.dispose();
				
			}
		});
		btn_02.setBounds(280, 286, 100, 30);
		frame.getContentPane().add(btn_02);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 441, 318);
		frame.getContentPane().add(lbl_background);
	}
}
