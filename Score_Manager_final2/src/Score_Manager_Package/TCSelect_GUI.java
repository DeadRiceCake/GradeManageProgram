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

	// ���̺��� �÷� �� ����
	private String[] col = { "���̵�", "�̸�", "��", "����", "����", "����", "��ȸ", "����", "���� ����" };

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
		lblNewLabel.setFont(new Font("G���� �꽺 TTF Medium", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(199, 36, 311, 61);
		frame.getContentPane().add(lblNewLabel);

//�޺��ڽ�
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("�������", Font.PLAIN, 15));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "��ü ����", "1�б� �߰����", "1�б� �⸻���", "2�б� �߰����", "2�б� �⸻���" }));
		comboBox.setBounds(23, 110, 195, 30);
		frame.getContentPane().add(comboBox);

		String testDaySelect = comboBox.getSelectedItem().toString(); // ���õ� �޺��ڽ� �� ����
		System.out.println(testDaySelect);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 172, 731, 255);
		frame.getContentPane().add(scrollPane);

// Controller�� ���� ���̺� ��ü ��ȸ
		controll = new Controller();

		t_model = new DefaultTableModel(data, col);

		tableReset(testDaySelect);

		JButton btn_01 = new JButton("\uAC80\uC0C9");
		btn_01.setFont(new Font("�������", Font.PLAIN, 15));
		btn_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// �˻���ư Ŭ�� �� ������ ����Ⱓ ������ ������ ��
				String testDaySelect = comboBox.getSelectedItem().toString(); // ���õ� �޺��ڽ� �� ����

				// ���̺���ȸ�޼ҵ忡 ����
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
		btn_04.setFont(new Font("�������", Font.PLAIN, 15));
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

//�л� ���κ� ��ȸ
		JButton btn_02 = new JButton("\uD559\uC0DD\uBCC4 \uC870\uD68C");
		btn_02.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				t_model.setNumRows(0); // JTable �ʱ�ȭ

				String stuNo = txt_01.getText();

				list = controll.selectScoreST(stuNo);

				data = new Object[list.size()][col.length];

				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data[i].length; j++) {
						if (j == 0) {
							data[i][j] = list.get(i).getId(); // ���̵�
						}
						if (j == 1) {
							data[i][j] = list.get(i).getName(); // �̸�
						}
						if (j == 2) {
							data[i][j] = list.get(i).getVan(); // ��
						}
						if (j == 3) {
							data[i][j] = list.get(i).getKor(); // ����
						}
						if (j == 4) {
							data[i][j] = list.get(i).getEng(); // ��
						}
						if (j == 5) {
							data[i][j] = list.get(i).getMath(); // ��
						}
						if (j == 6) {
							data[i][j] = list.get(i).getSociety(); // ��
						}
						if (j == 7) {
							data[i][j] = list.get(i).getScience(); // ��
						}
						if (j == 8) {
							data[i][j] = list.get(i).getTest_day(); // �߰��⸻
						}
					} // end for

					// JTable�� �� ������ �߰�
					t_model.addRow(data[i]);
				} // end for

			}
		});
		btn_02.setBounds(618, 111, 119, 30);
		frame.getContentPane().add(btn_02);

		JLabel lblNewLabel_1 = new JLabel("\uD559\uC0DD \uC544\uC774\uB514 \uC785\uB825 :");
		lblNewLabel_1.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(342, 110, 141, 30);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btn_03 = new JButton("\uBCC0\uD654 \uCD94\uC774");
		btn_03.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
// �л��� �����������̸� ��Ʈ�� ���� â���� �Ѿ�� ��ư
				String id = txt_01.getText();
				
				controll.insertChartTable(id);
				
				//1. �̵��ϰ� ���� Ŭ������ ��ü ����
				TCChartSelect TCChartSelect = new TCChartSelect();
				//2. �̵����� �ϴ� Ŭ������main()/������ �޼ҵ�() ����
				TCChartSelect.main(null);
				//3.����â �ݱ�X
				
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

	// �˻������ȸ �޼ҵ�
	private void tableReset(String testDaySelect) {

		t_model.setNumRows(0); // JTable �ʱ�ȭ
		

//		System.out.println(testDaySelect);

		if (testDaySelect.equals("��ü ����")) {
			list = controll.selectScoreTCAll(controll.getClassNo()); // ���߿� ������ ������ ���� �л�, �� ��ü ���� ��ȸ���
		} else if (testDaySelect.equals("1�б� �߰����")) {
			list = controll.selectScoreTC_1m(controll.getClassNo()); // ���߿� ������ ������ ���� �л�, �� ��ü ���� ��ȸ���
		} else if (testDaySelect.equals("1�б� �⸻���")) {
			list = controll.selectScoreTC_1e(controll.getClassNo()); // ���߿� ������ ������ ���� �л�, �� ��ü ���� ��ȸ���
		} else if (testDaySelect.equals("2�б� �߰����")) {
			list = controll.selectScoreTC_2m(controll.getClassNo()); // ���߿� ������ ������ ���� �л�, �� ��ü ���� ��ȸ���
		} else if (testDaySelect.equals("2�б� �⸻���")) {
			list = controll.selectScoreTC_2e(controll.getClassNo()); // ���߿� ������ ������ ���� �л�, �� ��ü ���� ��ȸ���
		}

		data = new Object[list.size()][col.length];

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (j == 0) {
					data[i][j] = list.get(i).getId(); // ���̵�
				}
				if (j == 1) {
					data[i][j] = list.get(i).getName(); // �̸�
				}
				if (j == 2) {
					data[i][j] = list.get(i).getVan(); // ��
				}
				if (j == 3) {
					data[i][j] = list.get(i).getKor(); // ����
				}
				if (j == 4) {
					data[i][j] = list.get(i).getEng(); // ��
				}
				if (j == 5) {
					data[i][j] = list.get(i).getMath(); // ��
				}
				if (j == 6) {
					data[i][j] = list.get(i).getSociety(); // ��
				}
				if (j == 7) {
					data[i][j] = list.get(i).getScience(); // ��
				}
				if (j == 8) {
					data[i][j] = list.get(i).getTest_day(); // �߰��⸻
				}
			} // end for

			// JTable�� �� ������ �߰�
			t_model.addRow(data[i]);
		} // end for

	}
}
