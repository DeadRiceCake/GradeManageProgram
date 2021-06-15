package Score_Manager_Package;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TCSelect_GUI {

	private JFrame frame;
	private JTable table;
	private JTextField txt_01;
	private Controller controll;
	private ArrayList<Score_ManagerVO> list;

	private Object[][] data;
	private DefaultTableModel t_model;

	// 테이블의 컬럼 명 지정
	private String[] col = { "아이디", "이름", "반", "국어", "영어", "수학", "사회", "과학", "시험 종류" };

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TCSelect_GUI window = new TCSelect_GUI();
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
	public TCSelect_GUI(String id, String pw) {
		initialize(id, pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 800, 611);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC804\uCCB4 \uC131\uC801 \uC870\uD68C");
		lblNewLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(199, 36, 311, 61);
		frame.getContentPane().add(lblNewLabel);

//콤보박스
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "전체 선택", "1학기 중간고사", "1학기 기말고사", "2학기 중간고사", "2학기 기말고사" }));
		comboBox.setBounds(23, 110, 195, 30);
		frame.getContentPane().add(comboBox);

		String testDaySelect = comboBox.getSelectedItem().toString(); // 선택된 콤보박스 값 지정
		System.out.println(testDaySelect);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 172, 731, 255);
		frame.getContentPane().add(scrollPane);

// Controller를 통해 테이블 전체 조회
		controll = new Controller();

		t_model = new DefaultTableModel(data, col);

		tableReset(testDaySelect);

		JButton btn_01 = new JButton("\uAC80\uC0C9");
		btn_01.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 검색버튼 클릭 시 선택한 시험기간 정보를 가져온 후
				String testDaySelect = comboBox.getSelectedItem().toString(); // 선택된 콤보박스 값 지정

				// 테이블조회메소드에 전달
				tableReset(testDaySelect);
			}
		});
		btn_01.setBounds(237, 110, 73, 30);
		frame.getContentPane().add(btn_01);

		table = new JTable(t_model);
		table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);

		JButton btn_04 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btn_04.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btn_04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCMain_GUI TCMain_GUI = new TCMain_GUI(id,pw);
				frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btn_04.setBounds(637, 472, 100, 30);
		frame.getContentPane().add(btn_04);

		txt_01 = new JTextField();
		txt_01.setBounds(495, 112, 111, 30);
		frame.getContentPane().add(txt_01);
		txt_01.setColumns(10);

//학생 개인별 조회
		JButton btn_02 = new JButton("\uD559\uC0DD\uBCC4 \uC870\uD68C");
		btn_02.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				t_model.setNumRows(0); // JTable 초기화

				String stuNo = txt_01.getText();

				list = controll.selectScoreST(stuNo);

				data = new Object[list.size()][col.length];

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
					} // end for

					// JTable의 행 데이터 추가
					t_model.addRow(data[i]);
				} // end for

			}
		});
		btn_02.setBounds(618, 111, 119, 30);
		frame.getContentPane().add(btn_02);

		JLabel lblNewLabel_1 = new JLabel("\uD559\uC0DD \uC544\uC774\uB514 \uC785\uB825 :");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(342, 110, 141, 30);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btn_03 = new JButton("\uBCC0\uD654 \uCD94\uC774");
		btn_03.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
// 학생별 성적변동추이를 차트로 보는 창으로 넘어가는 버튼
				String id = txt_01.getText();
				
				controll.insertChartTable(id);
				
				//1. 이동하고 싶은 클래스의 객체 생성
				TCChartSelect TCChartSelect = new TCChartSelect();
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				TCChartSelect.main(null);
				//3.기존창 닫기X
				
			}
		});
		btn_03.setBounds(509, 473, 97, 30);
		frame.getContentPane().add(btn_03);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(30, 30, 50, 50);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 760, 548);
		frame.getContentPane().add(panel);

	}

	// 검색결과조회 메소드
	private void tableReset(String testDaySelect) {

		t_model.setNumRows(0); // JTable 초기화
		

//		System.out.println(testDaySelect);

		if (testDaySelect.equals("전체 선택")) {
			list = controll.selectScoreTCAll(controll.getClassNo()); // 나중에 선생님 반으로 변경 학생, 반 전체 성적 조회기능
		} else if (testDaySelect.equals("1학기 중간고사")) {
			list = controll.selectScoreTC_1m(controll.getClassNo()); // 나중에 선생님 반으로 변경 학생, 반 전체 성적 조회기능
		} else if (testDaySelect.equals("1학기 기말고사")) {
			list = controll.selectScoreTC_1e(controll.getClassNo()); // 나중에 선생님 반으로 변경 학생, 반 전체 성적 조회기능
		} else if (testDaySelect.equals("2학기 중간고사")) {
			list = controll.selectScoreTC_2m(controll.getClassNo()); // 나중에 선생님 반으로 변경 학생, 반 전체 성적 조회기능
		} else if (testDaySelect.equals("2학기 기말고사")) {
			list = controll.selectScoreTC_2e(controll.getClassNo()); // 나중에 선생님 반으로 변경 학생, 반 전체 성적 조회기능
		}

		data = new Object[list.size()][col.length];

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
			} // end for

			// JTable의 행 데이터 추가
			t_model.addRow(data[i]);
		} // end for

	}
}
