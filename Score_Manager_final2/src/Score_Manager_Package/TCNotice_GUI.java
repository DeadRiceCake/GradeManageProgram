package Score_Manager_Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class TCNotice_GUI {

	Controller controll = new Controller();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TCNotice_GUI window = new TCNotice_GUI();
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
	public TCNotice_GUI(String id,String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id,String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 586, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uACF5\uC9C0\uC0AC\uD56D \uB4F1\uB85D");
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 10, 282, 44);
		frame.getContentPane().add(lblNewLabel);

		JTextPane txtpane_insertNotice = new JTextPane();
		txtpane_insertNotice.setBackground(SystemColor.info);
		txtpane_insertNotice.setBounds(37, 50, 490, 283);
		frame.getContentPane().add(txtpane_insertNotice);

		JButton btn_01 = new JButton("\uACF5\uC9C0 \uB4F1\uB85D");
		btn_01.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String notice = txtpane_insertNotice.getText();
				int cnt = controll.insertNotice(notice);
				if (cnt > 0 ) {
					String order = "공지등록에 성공하였습니다.";
					System.out.println("공지등록 성공");
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					
				} else {
					String order = "공지등록에 실패하였습니다.";
					System.out.println("공지등록 실패");
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					
				}

			}
		});

		btn_01.setBounds(210, 354, 100, 30);
		frame.getContentPane().add(btn_01);

		JButton btnNewButton_1 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnNewButton_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TCMain_GUI TCMain_GUI = new TCMain_GUI(id,pw);
				frame.setVisible(true);
				frame.dispose();

			}
		});
		btnNewButton_1.setBounds(373, 354, 100, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 546, 401);
		frame.getContentPane().add(lbl_background);
	}
}