package Score_Manager_Package;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Error_Code_GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//				
//					Cd_Y_GUI window = new Cd_Y_GUI();
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
	public Error_Code_GUI(String order) {
		initialize(order);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String order) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn_1 = new JButton("\uB2EB\uAE30");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_1.setBounds(166, 176, 97, 23);
		frame.getContentPane().add(btn_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.WHITE);
		textPane.setText(order);
		textPane.setBounds(125, 82, 97, 65);
		frame.getContentPane().add(textPane);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(lbl_background);
	}
}
