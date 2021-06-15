package Score_Manager_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	// Controller : �����͸� �������ִ� ��
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	Score_ManagerVO vo;
	private String notice;
	private String id_info;// ����ǥ�ÿ� ���̵�
	// select,insert,update,delete

	// 1. �����ε��� ���� �Լ�
	public void getConn() {
		// Class.forName()
		// DriverManager.getConnection()
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user_id = "hr";
			String user_pw = "hr";

			conn = DriverManager.getConnection(url, user_id, user_pw);
		} catch (Exception e) {
			System.out.println("getconn()����");
			e.printStackTrace();
		}

	}

	// ------------------------------------------------
	// 2. ���Ḧ ���� �Լ�
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
	}

	// ------------------------------------------------
	// 3-1. �л� ȸ������������ �Լ�
	public int insertST(String id, String pw, String name, int tch_no, int van) {
		getConn();

		try {
			String sql = "insert into student_table values (stu_no.nextval,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setInt(4, tch_no);
			psmt.setInt(5, van);
			cnt = psmt.executeUpdate();

			sql = "select stu_no from student_table where USER_ID = ? and USER_PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();

			int stu_no_rs = 0;

			while (rs.next()) {
				stu_no_rs = rs.getInt(1);
			}

			sql = "insert into score_table values (?, '1-m', null, null, null, null, null)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stu_no_rs);
			cnt = psmt.executeUpdate();

			sql = "insert into score_table values (?, '1-e', null, null, null, null, null)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stu_no_rs);
			cnt = psmt.executeUpdate();

			sql = "insert into score_table values (?, '2-m', null, null, null, null, null)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stu_no_rs);
			cnt = psmt.executeUpdate();

			sql = "insert into score_table values (?, '2-e', null, null, null, null, null)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stu_no_rs);
			cnt = psmt.executeUpdate();

			////////////////////////////////////////
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 3-2. ���� ȸ������������ �Լ�
	public int insertTC(String id, String pw, String name, int van) {
		getConn();

		try {
			String sql = "insert into teacher_table values (tch_no.nextval,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setInt(4, van);
			cnt = psmt.executeUpdate();
			////////////////////////////////////////
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 3-3. �л� �α����� ���� �Լ�
	public int loginST(String id, String pw) { // � ������ ���� ������ �α����� �õ��ϴ� �Լ�, ���ڰ����� ID�� Password�� �޾� login�� �Ǵ���.
		getConn();

		try {
			String sql = "SELECT USER_PW FROM student_table WHERE USER_ID = ?"; // ������ DB�� �Էµ� ��ɾ SQL �������� ����.
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ����� �־���
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					return 1; // �α��� ����
				} else {
					return 0; // ��й�ȣ ����ġ
				}
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB ����
	}

	// ------------------------------------------------
	// 3-4. ���� �α����� ���� �Լ�
	public int loginTC(String id, String pw) { // � ������ ���� ������ �α����� �õ��ϴ� �Լ�, ���ڰ����� ID�� Password�� �޾� login�� �Ǵ���.
		getConn();
		try {
			String sql = "SELECT USER_PW FROM teacher_table WHERE USER_ID = ?"; // ������ DB�� �Էµ� ��ɾ SQL �������� ����.

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ����� �־���
			if (rs.next()) {
				if (rs.getString(1).contentEquals(pw)) {
					return 1; // �α��� ����
				} else {
					return 0; // ��й�ȣ ����ġ
				}
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB ����
	}

	// ------------------------------------------------
	// 4-1. �л� ��ü ȸ������ ��ȸ�� ���� �Լ�
	public ArrayList<Score_ManagerVO> selectAll() {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select * from student_table";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int STU_NO = rs.getInt(1);
				String id = rs.getString(2);
				String pw = rs.getString(3);
				String name = rs.getString(4);
				int TCH_NO = rs.getInt(5);
				int van = rs.getInt(6);

				vo = new Score_ManagerVO(STU_NO, id, pw, name, TCH_NO, van);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 4-2. ���� �л� ȸ������ ��ȸ�� ���� �Լ�
	public ArrayList<Score_ManagerVO> selectST(String id) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select * from student_table where USER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				int van = rs.getInt(4);

				vo = new Score_ManagerVO(id, pw, name, van);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 5-1. �л� �ڽ��� ���� ��й�ȣ ������ ���� �Լ�
	public int updateST(String id, String data) {
		getConn();

		try {

			String sql = "update student_table set USER_PW = ? where USER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, data);
			psmt.setString(2, id);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 5-2. ���� �ڽ��� ���� ��й�ȣ ������ ���� �Լ�
	public int updateTC(String id, String data) {
		getConn();

		try {

			String sql = "update teacher_table set USER_PW = ? where USER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, data);
			psmt.setString(2, id);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 6-1. ���簡 �л� ���� ������ ���� �Լ�(����)
	public int updateKOR(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1�б� �߰����")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1�б� �⸻���")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2�б� �߰����")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2�б� �⸻���")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("�߸��� �޼ҵ尪�� �ԷµǾ����ϴ�.");
			}

			psmt = conn.prepareStatement(sql);
			psmt.setInt(2, stu_no);
			psmt.setInt(1, score);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 6-2. ���簡 �л� ���� ������ ���� �Լ�(����)
	public int updateENG(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1�б� �߰����")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1�б� �⸻���")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2�б� �߰����")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2�б� �⸻���")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("�߸��� �޼ҵ尪�� �ԷµǾ����ϴ�.");
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(2, stu_no);
			psmt.setInt(1, score);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 6-3. ���簡 �л� ���� ������ ���� �Լ�(����)
	public int updateMATH(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1�б� �߰����")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1�б� �⸻���")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2�б� �߰����")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2�б� �⸻���")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("�߸��� �޼ҵ尪�� �ԷµǾ����ϴ�.");
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(2, stu_no);
			psmt.setInt(1, score);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 6-4. ���簡 �л� ���� ������ ���� �Լ�(��ȸ)
	public int updateSOCIETY(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1�б� �߰����")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1�б� �⸻���")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2�б� �߰����")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2�б� �⸻���")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("�߸��� �޼ҵ尪�� �ԷµǾ����ϴ�.");
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(2, stu_no);
			psmt.setInt(1, score);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 6-5. ���簡 �л� ���� ������ ���� �Լ�(����)
	public int updateSCIENCE(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1�б� �߰����")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1�б� �⸻���")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2�б� �߰����")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2�б� �⸻���")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("�߸��� �޼ҵ尪�� �ԷµǾ����ϴ�.");
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(2, stu_no);
			psmt.setInt(1, score);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 7-1. ���簡 �л� ���� �Է��� ���� �Լ�
	public int insertScore(int stu_no, int kor, int eng, int math, int society, int science, String test_day) {
		getConn();

		try {

//			String sql = "update score_table set (STU_NO, KOR, ENG, MATH, SOCIETY, SCIENCE) = (select ?,?,?,?,?,?) where test_day = ?";

			String sql = "update score_table set KOR = ?, ENG = ?, MATH = ?, SOCIETY = ?, SCIENCE = ? where STU_NO = ? and TEST_DAY = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, kor);
			psmt.setInt(2, eng);
			psmt.setInt(3, math);
			psmt.setInt(4, society);
			psmt.setInt(5, science);
			psmt.setInt(6, stu_no);
			psmt.setString(7, test_day);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 8-1. �ݺ� �л����� ��ü ��ȸ�� ���� �Լ�
	public ArrayList<Score_ManagerVO> selectScoreTCAll(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and CLASS_NO = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Class);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 8-1-2. �ݺ� �л����� ��ü ��ȸ�� ���� �Լ�(1�б� �߰�)
	public ArrayList<Score_ManagerVO> selectScoreTC_1m(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and test_day = '1-m' and CLASS_NO = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Class);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 8-1-3. �ݺ� �л����� ��ü ��ȸ�� ���� �Լ�(1�б� �⸻)
	public ArrayList<Score_ManagerVO> selectScoreTC_1e(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and test_day = '1-e' and CLASS_NO = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Class);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 8-1-4. �ݺ� �л����� ��ü ��ȸ�� ���� �Լ�(2�б� �߰�)
	public ArrayList<Score_ManagerVO> selectScoreTC_2m(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and test_day = '2-m' and CLASS_NO = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Class);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 8-1-5. �ݺ� �л����� ��ü ��ȸ�� ���� �Լ�(2�б� �⸻)
	public ArrayList<Score_ManagerVO> selectScoreTC_2e(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and test_day = '2-e' and CLASS_NO = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Class);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 8-2. �л��� �л����� ��ȸ�� ���� �Լ�
	public ArrayList<Score_ManagerVO> selectScoreST(String user_id) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// �ε�,Ŀ��,����,����
		try {
			String sql = "select st.USER_ID, st.NAME, st.CLASS_NO, sc.KOR, sc.ENG, sc.MATH, sc.SOCIETY, sc.SCIENCE, sc.test_day from student_table st,score_table sc where sc.STU_NO = st.STU_NO and USER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int van = rs.getInt(3);
				int kor = rs.getInt(4);
				int eng = rs.getInt(5);
				int math = rs.getInt(6);
				int society = rs.getInt(7);
				int science = rs.getInt(8);
				String test_day = rs.getString(9);

				vo = new Score_ManagerVO(id, name, van, kor, eng, math, society, science, test_day);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------------------------------------
	// 9-1-1. �л����� ������ ���� �Լ�(�л����̺�)
	public int deleteST(int STU_NO) {
		getConn();

		try {

			String sql = "delete from student_table where STU_NO = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, STU_NO);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 9-1-2. �л����� ������ ���� �Լ�(�������̺�)
	public int deleteSCR(int STU_NO) {
		getConn();

		try {

			String sql = "delete from score_table where STU_NO = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, STU_NO);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 9-2. �������� ������ ���� �Լ�
	public int deleteTC(String id, String pw) {
		getConn();

		try {

			String sql = "delete from teacher_table where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// --------------------------------------------------
	// 10-1. �������� �Է�
	public int insertNotice(String notice) {
		getConn();

		try {
			String sql = "insert into notice_table values (notice_no.nextval,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, notice);

			cnt = psmt.executeUpdate();
			////////////////////////////////////////
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// --------------------------------------------------
	// 10-2. �������� ���
	public String getNotice() {
		getConn();

		String notice = "";
		// �ε�,Ŀ��,����,����
		try {
			String sql = "SELECT notice FROM notice_table WHERE notice_no = (SELECT max(notice_no) FROM notice_table)";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				notice = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return notice;
	}

	// ------------------------------------------------
	// 11-1. ��Ʈ��ȸ�� ���̺� �ʱ�ȭ
	public int dropChartTable() {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "drop table chart_table";
			psmt = conn.prepareStatement(sql);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 11-2. ��Ʈ��ȸ�� ���̺� ����
	public int createChartTable() {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "create table chart_table (STU_NO number(10), TEST_DAY varchar2(20), KOR number(10), ENG number(10), MATH number(10), SOCIETY number(10), SCIENCE number(10))";
			psmt = conn.prepareStatement(sql);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 11-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ����
	public int insertChartTable(String id) {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "insert into chart_table (select sc.stu_no, sc.test_day, sc.kor, sc.eng, sc.math, sc.society, sc.science from score_table sc, student_table st where sc.stu_no = st.stu_no and user_id = ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 12-1-1. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �߰�)
	public int getChartScore_KOR_1m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select kor from chart_table where test_day = '1-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-1-2. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �⸻)
	public int getChartScore_KOR_1e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select kor from chart_table where test_day = '1-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-1-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �߰�)
	public int getChartScore_KOR_2m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select kor from chart_table where test_day = '2-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-1-4. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �⸻)
	public int getChartScore_KOR_2e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select kor from chart_table where test_day = '2-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-2-1. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �߰�)
	public int getChartScore_ENG_1m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select eng from chart_table where test_day = '1-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-2-2. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �⸻)
	public int getChartScore_ENG_1e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select eng from chart_table where test_day = '1-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-2-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �߰�)
	public int getChartScore_ENG_2m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select eng from chart_table where test_day = '2-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-2-4. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �⸻)
	public int getChartScore_ENG_2e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select eng from chart_table where test_day = '2-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-3-1. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �߰�)
	public int getChartScore_MATH_1m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select math from chart_table where test_day = '1-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-3-2. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �⸻)
	public int getChartScore_MATH_1e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select math from chart_table where test_day = '1-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-3-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �߰�)
	public int getChartScore_MATH_2m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select math from chart_table where test_day = '2-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-3-4. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �⸻)
	public int getChartScore_MATH_2e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select math from chart_table where test_day = '2-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-4-1. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(��ȸ/1�б� �߰�)
	public int getChartScore_SOCIETY_1m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select society from chart_table where test_day = '1-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-4-2. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(��ȸ/1�б� �⸻)
	public int getChartScore_SOCIETY_1e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select society from chart_table where test_day = '1-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-4-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(��ȸ/2�б� �߰�)
	public int getChartScore_SOCIETY_2m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select society from chart_table where test_day = '2-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-4-4. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(��ȸ/2�б� �⸻)
	public int getChartScore_SOCIETY_2e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select society from chart_table where test_day = '2-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-5-1. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �߰�)
	public int getChartScore_SCIENCE_1m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select science from chart_table where test_day = '1-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-5-2. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/1�б� �⸻)
	public int getChartScore_SCIENCE_1e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select science from chart_table where test_day = '1-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-5-3. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �߰�)
	public int getChartScore_SCIENCE_2m() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select science from chart_table where test_day = '2-m'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}

	// ------------------------------------------------
	// 12-5-4. ��Ʈ��ȸ�� ���̺� �α����� �л��� ���� ���� �ҷ�����(����/2�б� �⸻)
	public int getChartScore_SCIENCE_2e() {
		getConn();
		int score = 0;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select science from chart_table where test_day = '2-e'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return score;
	}
	
	// ------------------------------------------------
	// 13-1. ������ �α��� ���� ���̺� �ʱ�ȭ
	public int dropTeacherLoginTable() {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "drop table teacher_login_table";
			psmt = conn.prepareStatement(sql);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 13-2. ������ �α��� ���� ���̺� ����
	public int createTeacherLoginTable() {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "create table teacher_login_table (TCH_NO number(10), USER_ID varchar2(20), USER_PW varchar2(20), NAME varchar2(20), CLASS_NO number(10))";
			psmt = conn.prepareStatement(sql);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 13-3. ������ �α��� ���� ���̺� �α����� �������� ���� ����
	public int insertTeacherLoginTable(String id) {
		getConn();

		// �ε�,Ŀ��,����,����
		try {
			String sql = "insert into teacher_login_table (select tch_no, user_id, user_pw, name, class_no from teacher_table where user_id = ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------------------------
	// 13-4. ������ �α��� ���� ���̺� �α����� �������� �� ���� �ҷ�����
	public int getClassNo() {
		getConn();
		int van = 1;

		// �ε�,Ŀ��,����,����
		try {
			String sql = "select class_no from teacher_login_table";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				van = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return van;
	}


}
