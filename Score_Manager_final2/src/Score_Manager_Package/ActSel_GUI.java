package Score_Manager_Package;



import java.awt.EventQueue;

import javax.swing.JFrame;

import java.util.ArrayList;

import javax.swing.JLabel;


import java.awt.Font;
import javax.swing.SwingConstants;


import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ActSel_GUI {

	private JFrame frame;
	private JLabel lbl_1;
	private JTable table_1;
	private JButton btn;
	private JLabel lbl_background;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ActSel_GUI window = new ActSel_GUI();
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
	public ActSel_GUI(String id , String pw) {
		initialize(id, pw);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id , String pw) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbl_1 = new JLabel("\uC804\uCCB4 \uD559\uC0DD \uC815\uBCF4 \uC870\uD68C");
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_1.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
		lbl_1.setBounds(12, 10, 760, 74);
		frame.getContentPane().add(lbl_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 739, 289);
		frame.getContentPane().add(scrollPane);
		

		//테이블의 컬럼명 지정
		String[] col = {"학생번호","아이디","이름","선생번호","반"};
		
		//Controller를 통해 전체 데이터를 죄회할수 있는 메소드 불러오기
		Controller controll = new Controller();
		 ArrayList<Score_ManagerVO> list = controll.selectAll();
		 
		 System.out.println(list.size());
		 
		 Object[][] data = new Object[list.size()][6];
		 
		 //이차원 배열의 데이터를 출력하기 위한 반복문
		 for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if(j==0) {
					data[i][j]= list.get(i).getSTU_NO();  // 학생번호
				}if(j==1) {
					data[i][j]= list.get(i).getId();  // 비밀번호
				}if(j==2) {
					data[i][j]=list.get(i).getName();// 이름
				}if(j==3) {
					data[i][j]=list.get(i).getTCH_NO();// 선생번호
				}if(j==4) {
					data[i][j]=list.get(i).getVan();// 반
				}
			}
		 }
		
		table_1 = new JTable(data,col);
		scrollPane.setViewportView(table_1);
		
		JButton btn_1 = new JButton("\uB4A4\uB85C");
		btn_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn_1.addActionListener(new ActionListener() {
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
		btn_1.setBounds(639, 395, 117, 30);
		frame.getContentPane().add(btn_1);
		
		btn = new JButton("\uD559\uC0DD\uC0AD\uC81C");
		btn.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 학생삭제로 넘어가는 버튼
				
				//1. 이동하고 싶은 클래스의 객체 생성
				ActDel_GUI ActDel_GUI = new ActDel_GUI(id,pw);
				//2. 이동하자 하는 클래스의main()/생성자 메소드() 연결
				frame.setVisible(true);
				//3.기존창 닫기
				frame.dispose();
				
				
			}
		});
		btn.setBounds(492, 395, 117, 30);
		frame.getContentPane().add(btn);
		
		lbl_background = new JLabel("");
		lbl_background.setVerticalAlignment(SwingConstants.TOP);
		lbl_background.setOpaque(true);
		lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_background.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_background.setBackground(Color.WHITE);
		lbl_background.setBounds(12, 10, 760, 441);
		frame.getContentPane().add(lbl_background);
	}
}
