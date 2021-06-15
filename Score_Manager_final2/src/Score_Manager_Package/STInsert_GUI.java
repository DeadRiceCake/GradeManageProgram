package Score_Manager_Package;






import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class STInsert_GUI {

	private JFrame frame;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_name;
	private JTextField txt_van;
	private JTextField txt_tch_no;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private JPanel panel;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					STInsert_GUI window = new STInsert_GUI();
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
	public STInsert_GUI() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 630, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel_5.setBounds(38, 35, 57, 50);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel.setBounds(40, 122, 100, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(40, 162, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(40, 202, 100, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uBC18");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(40, 282, 100, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		txt_id = new JTextField();
		txt_id.setBounds(167, 124, 120, 25);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		txt_pw = new JTextField();
		txt_pw.setColumns(10);
		txt_pw.setBounds(167, 164, 120, 25);
		frame.getContentPane().add(txt_pw);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(167, 204, 120, 25);
		frame.getContentPane().add(txt_name);
		
		txt_tch_no = new JTextField();
		txt_tch_no.setColumns(10);
		txt_tch_no.setBounds(167, 244, 120, 25);
		frame.getContentPane().add(txt_tch_no);
		
		txt_van = new JTextField();
		txt_van.setColumns(10);
		txt_van.setBounds(167, 284, 120, 25);
		frame.getContentPane().add(txt_van);
		
		JLabel lblNewLabel_3_2 = new JLabel("\uC120\uC0DD\uB2D8\uBC88\uD638");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_2.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(40, 242, 100, 25);
		frame.getContentPane().add(lblNewLabel_3_2);
		
		lblNewLabel_4 = new JLabel("\uD559\uC0DD \uD68C\uC6D0 \uAC00\uC785");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 23));
		lblNewLabel_4.setBounds(148, 34, 300, 50);
		frame.getContentPane().add(lblNewLabel_4);
		
		btnNewButton = new JButton("\uB4A4\uB85C");
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_GUI Login_GUI = new Login_GUI();
				Login_GUI.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(350, 354, 100, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btn_insert = new JButton("\uAC00\uC785\uD558\uAE30");
		btn_insert.setBackground(new Color(230, 230, 250));
		btn_insert.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_insert.setBounds(173, 354, 100, 30);
		frame.getContentPane().add(btn_insert);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 10, 588, 391);
		frame.getContentPane().add(panel);
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String pw = txt_pw.getText();
				String name = txt_name.getText();
				int tch_no = Integer.parseInt(txt_tch_no.getText());
				int van = Integer.parseInt(txt_van.getText());
				
				Controller controll = new Controller();
				int cnt = controll.insertST(id, pw, name, tch_no, van);
				if(cnt>0) {
					System.out.println("가입 성공");
					Login_GUI Login_GUI = new Login_GUI();
					Login_GUI.main(null);
		               String order = "학생 회원가입에 성공했습니다!";
		               Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);
		               frame.setVisible(true);
						frame.dispose();
				}else {
					System.out.println("가입 실패");

		               String order = "학생 회원가입에 실패했습니다!";
		               Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);

						
				}
				
			}
		});
		
		
	}
}
