package Score_Manager_Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.SwingConstants;

import Score_Manager_Package.STInsert_GUI;
import Score_Manager_Package.TCInsert_GUI;
import Score_Manager_Package.STMain_GUI;
import Score_Manager_Package.TCMain_GUI;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login_GUI {

	private JFrame frame;
	private JTextField txt_id;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Controller controll = new Controller();
	private int cnt;
	private JPasswordField txt_pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI window = new Login_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_GUI() {
		initialize();
//      frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.getContentPane().setForeground(new Color(70, 130, 180));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		controll.dropChartTable();
		controll.createChartTable();
		
		controll.dropTeacherLoginTable();
		controll.createTeacherLoginTable();

		JButton btn_login = new JButton("\uB85C\uADF8\uC778");
		btn_login.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\SM2116\\Desktop\\KakaoTalk_20210523_140305797.png"));
		lblNewLabel_1.setBounds(382, 87, 371, 332);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("\uD559\uAD50 \uC131\uC801 \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		lblNewLabel_3.setBackground(new Color(100, 149, 237));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 38));
		lblNewLabel_3.setBounds(12, 38, 760, 86);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblNewLabel.setBounds(74, 188, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		btn_login.setBounds(68, 312, 100, 30);
		frame.getContentPane().add(btn_login);

		JRadioButton rd_tch = new JRadioButton("\uAD50\uC0AC");
		rd_tch.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rd_tch);
		rd_tch.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		rd_tch.setBackground(Color.WHITE);
		rd_tch.setBounds(225, 269, 63, 30);
		frame.getContentPane().add(rd_tch);

		txt_id = new JTextField();
		txt_id.setBounds(143, 183, 145, 26);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		txt_pw = new JPasswordField();
		txt_pw.setBounds(143, 223, 145, 26);
		frame.getContentPane().add(txt_pw);

		JRadioButton rd_stu = new JRadioButton("\uD559\uC0DD");
		rd_stu.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rd_stu);
		rd_stu.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		rd_stu.setBackground(Color.WHITE);
		rd_stu.setBounds(143, 269, 63, 30);
		frame.getContentPane().add(rd_stu);

		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 회원가입 버튼 화면 전환

				if (rd_stu.isSelected()) {
					String id = txt_id.getText();
					String pw = txt_pw.getText();
					cnt = controll.loginST(id, pw);

					controll.insertChartTable(id);

					if (cnt > 0) {
						STMain_GUI STMain_GUI = new STMain_GUI(id, pw);
						frame.setVisible(true);
						frame.dispose(); // 기존 창을 종료
						System.out.println("로그인 성공");
					} else {
						System.out.println("로그인 실패");
						String order = "로그인 실패\n아이디와 패스워드를 확인해주세요.";
						Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					}
				} else if (rd_tch.isSelected()) {
					String id = txt_id.getText();
					String pw = txt_pw.getText();

					cnt = controll.loginTC(id, pw);
					
					controll.insertTeacherLoginTable(id);
					
					if (cnt > 0) {
						System.out.println("로그인 성공");
						TCMain_GUI TCMain_GUI = new TCMain_GUI(id, pw);
						frame.setVisible(true);
						frame.dispose(); // 기존 창 종료
					} else {
						System.out.println("로그인 실패");
						String order = "로그인 실패\n아이디와 패스워드를 확인해주세요.";
						Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);

					}
				} else {
					String order = "학생인지 선생님인지 선택해주세요.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);

				}

			}
		});

		JLabel lblPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblPw.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblPw.setBackground(Color.WHITE);
		lblPw.setBounds(74, 231, 57, 15);
		frame.getContentPane().add(lblPw);

		JButton btn_insert = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btn_insert.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 회원가입 버튼 화면 전환
				if (rd_stu.isSelected()) {
					STInsert_GUI insertStGui = new STInsert_GUI();

					frame.setVisible(true);

					// 기존 창을 종료
					frame.dispose();
				} else if (rd_tch.isSelected()) {
					TCInsert_GUI insertTcGui = new TCInsert_GUI();

					frame.setVisible(true);

					// 기존 창을 종료
					frame.dispose();
				} else {
					String order = "학생인지 선생님인지 선택해주세요.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);

				}

			}
		});
		btn_insert.setBounds(188, 312, 100, 30);
		frame.getContentPane().add(btn_insert);

		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 12));
		lbl_background.setOpaque(true);
		lbl_background.setBackground(new Color(255, 255, 255));
		lbl_background.setBounds(12, 10, 760, 441);
		frame.getContentPane().add(lbl_background);

	
	}
}