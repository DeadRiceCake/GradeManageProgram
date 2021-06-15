package Score_Manager_Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ScoreUpdate_GUI {

	private JFrame frame;
	private JTextField txt_1;
	private JTextField txt_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScoreUpdate_GUI window = new ScoreUpdate_GUI();
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
	public ScoreUpdate_GUI(String id, String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC131\uC801\uC218\uC815");
		lblNewLabel.setFont(new Font("돋움체", Font.BOLD, 17));
		lblNewLabel.setBounds(168, 21, 90, 27);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC131\uC801 \uC218\uC815\uD560 \uD559\uC0DD\uBC88\uD638");
		lblNewLabel_1.setBounds(22, 58, 127, 27);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uC218\uC815\uD560 \uACFC\uBAA9");
		lblNewLabel_1_1.setBounds(22, 101, 127, 27);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("\uC810\uC218\uC785\uB825");
		lblNewLabel_1_2.setBounds(22, 152, 127, 27);
		frame.getContentPane().add(lblNewLabel_1_2);

		txt_1 = new JTextField();
		txt_1.setBounds(151, 61, 116, 21);
		frame.getContentPane().add(txt_1);
		txt_1.setColumns(10);

		

		txt_2 = new JTextField();
		txt_2.setColumns(10);
		txt_2.setBounds(151, 155, 116, 21);
		frame.getContentPane().add(txt_2);
		

		JComboBox cbx_2 = new JComboBox();// 과목
		cbx_2.setEditable(true);
		cbx_2.setModel(new DefaultComboBoxModel(new String[] {"\uAD6D\uC5B4", "\uC601\uC5B4", "\uC218\uD559", "\uC0AC\uD68C", "\uACFC\uD559"}));
		cbx_2.setBounds(151, 103, 116, 23);
		frame.getContentPane().add(cbx_2);
		

		JComboBox cbx_1 = new JComboBox();// 날짜
		cbx_1.setModel(new DefaultComboBoxModel(
				new String[] { "1\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "1\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC",
						"2\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "2\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC" }));
		cbx_1.setBounds(304, 58, 97, 27);
		frame.getContentPane().add(cbx_1);
		
		JButton btn_01 = new JButton("\uC131\uC801 \uC218\uC815");
		btn_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Controller controll = new Controller();
				
				String test_daySelect = cbx_1.getSelectedItem().toString();
				String subSelect = cbx_2.getSelectedItem().toString(); // 선택된 콤보박스 값 지정
				
				int stu_no = Integer.parseInt(txt_1.getText());
				int score = Integer.parseInt(txt_2.getText());
				int cnt = 0;
				if (subSelect.equals("국어")) {
					cnt = controll.updateKOR(stu_no, score, test_daySelect);
				}else if (subSelect.equals("영어")) {
					cnt = controll.updateENG(stu_no, score, test_daySelect);
				}else if (subSelect.equals("수학")) {
					cnt = controll.updateMATH(stu_no, score, test_daySelect);
				}else if (subSelect.equals("사회")) {
					cnt = controll.updateSOCIETY(stu_no, score, test_daySelect);
				}else if (subSelect.equals("과학")) {
					cnt = controll.updateSCIENCE(stu_no, score, test_daySelect);
				}
				if(cnt!=0) {
					String order = "성적수정에 성공하였습니다.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					
				}else {
					String order = "성적수정에 실패하였습니다.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					
				}
				
			}
		});
		btn_01.setBounds(168, 211, 97, 23);
		frame.getContentPane().add(btn_01);
		
		JButton btn_01_1 = new JButton("\uB4A4\uB85C");
		btn_01_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TCMain_GUI TCmain_GUI = new TCMain_GUI(id,pw);
				frame.dispose();
				
			}
		});
		btn_01_1.setBounds(304, 211, 97, 23);
		frame.getContentPane().add(btn_01_1);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(lbl_background);
		String test_daySelect = cbx_1.getSelectedItem().toString();
		System.out.println(test_daySelect);

		

	}
}
