package Score_Manager_Package;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class STSelect_GUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					STSelect_GUI window = new STSelect_GUI();
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
	public STSelect_GUI(String id, String pw) {
		initialize(id,pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 799, 611);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 121, 728, 342);
		frame.getContentPane().add(scrollPane);
		


		// 테이블의 컬럼 명 지정
		String[] col = { "아이디", "이름", "반", "국어", "영어", "수학", "사회", "과학", "시험 종류" };

		// Controller를 통해 테이블 전체 조회
		Controller controll = new Controller();
		
		ArrayList<Score_ManagerVO> list = controll.selectScoreST(id); // 학생 성적 조회기능

		Object[][] data = new Object[list.size()][col.length];

		// list의 내용을 JTable에 저장하기 -> 실제 data에 삽입
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (j == 0) {
					data[i][j] = list.get(i).getId(); // 아이디
				}
				if (j == 1) {
					data[i][j] = list.get(i).getName(); // 이름
				}
				if (j == 2) {
					data[i][j] = list.get(i).getVan(); // 반
				}
				if (j == 3) {
					data[i][j] = list.get(i).getKor(); // 국어
				}
				if (j == 4) {
					data[i][j] = list.get(i).getEng(); // 영
				}
				if (j == 5) {
					data[i][j] = list.get(i).getMath(); // 수
				}
				if (j == 6) {
					data[i][j] = list.get(i).getSociety(); // 사
				}
				if (j == 7) {
					data[i][j] = list.get(i).getScience(); // 과
				}
				if (j == 8) {
					data[i][j] = list.get(i).getTest_day(); // 중간기말
				}
			}
		}

		table = new JTable(data, col);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("\uC131\uC801    \uC870\uD68C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel.setBounds(0, 36, 783, 61);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(id+"님의 성적");
		lblNewLabel_1.setBounds(100, 73, 124, 38);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STMain_GUI STMain_GUI = new STMain_GUI(id,pw);
				frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton.setBounds(371, 493, 100, 30);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\uC131\uC801\uBCC0\uD654 \uADF8\uB798\uD504 \uD655\uC778");
		btnNewButton_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// 학생별 성적변동추이를 차트로 보는 창으로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				STChartSelect STChartSelect = new STChartSelect();
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				STChartSelect.main(null);
				//3.기존창 닫기X
			}
		});
		btnNewButton_1.setBounds(499, 493, 195, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(30, 30, 50, 50);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 12, 759, 548);
		frame.getContentPane().add(panel);
	}
}
