package Score_Manager_Package;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class TCInsert_GUI {

	private JFrame frame;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_name;
	private JTextField txt_van;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JLabel lblNewLabel_6;
	private JPasswordField txt_certi;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TCInsert_GUI window = new TCInsert_GUI();
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
	public TCInsert_GUI() {
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
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(27, 22, 50, 50);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(40, 122, 100, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(40, 162, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(40, 202, 100, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uBC18");
		lblNewLabel_3.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(40, 242, 100, 30);
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
		
		txt_van = new JTextField();
		txt_van.setColumns(10);
		txt_van.setBounds(167, 244, 120, 25);
		frame.getContentPane().add(txt_van);
		
		txt_certi = new JPasswordField();
		txt_certi.setBounds(167, 287, 120, 24);
		frame.getContentPane().add(txt_certi);
		
		JLabel lblNewLabel_4 = new JLabel("\uC778\uC99D\uD0A4\uC785\uB825");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(40, 282, 100, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		String certiNum="a11111";
		
		JButton btn_insert = new JButton("\uAC00\uC785\uD558\uAE30");
		btn_insert.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String pw = txt_pw.getText();
				String name = txt_name.getText();
				int van = Integer.parseInt(txt_van.getText());
				String certiInput = txt_certi.getText();
				
				Controller controll = new Controller();
				int cnt = controll.insertTC(id, pw, name, van);
				if(cnt>0 && certiInput.equals(certiNum)) {
					Login_GUI Login_GUI = new Login_GUI();
					Login_GUI.main(null);
					String order = "가입에 성공했습니다.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					frame.setVisible(true);
					frame.dispose();	
				}else {
					
					String order = "가입에 실패했습니다.";
					Error_Code_GUI Error_Code_GUI = new Error_Code_GUI(order);
					
			
				}
				System.out.println(cnt);
					
			
			}
		});
				
		btn_insert.setBounds(173, 354, 100, 30);
		frame.getContentPane().add(btn_insert);
		
		lblNewLabel_5 = new JLabel("\uAD50\uC0AC \uD68C\uC6D0 \uAC00\uC785");
		lblNewLabel_5.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 23));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(153, 33, 300, 50);
		frame.getContentPane().add(lblNewLabel_5);
		
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
		

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 590, 391);
		frame.getContentPane().add(panel);
	}
}
