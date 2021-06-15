package Score_Manager_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	// Controller : 데이터를 제어해주는 곳
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	Score_ManagerVO vo;
	private String notice;
	private String id_info;// 정보표시용 아이디
	// select,insert,update,delete

	// 1. 동적로딩을 위한 함수
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
			System.out.println("getconn()오류");
			e.printStackTrace();
		}

	}

	// ------------------------------------------------
	// 2. 종료를 위한 함수
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
			System.out.println("종료 오류");
			e.printStackTrace();
		}
	}

	// ------------------------------------------------
	// 3-1. 학생 회원가입을위한 함수
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
	// 3-2. 선생 회원가입을위한 함수
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
	// 3-3. 학생 로그인을 위한 함수
	public int loginST(String id, String pw) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.
		getConn();

		try {
			String sql = "SELECT USER_PW FROM student_table WHERE USER_ID = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류
	}

	// ------------------------------------------------
	// 3-4. 선생 로그인을 위한 함수
	public int loginTC(String id, String pw) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.
		getConn();
		try {
			String sql = "SELECT USER_PW FROM teacher_table WHERE USER_ID = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
			if (rs.next()) {
				if (rs.getString(1).contentEquals(pw)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류
	}

	// ------------------------------------------------
	// 4-1. 학생 전체 회원정보 조회를 위한 함수
	public ArrayList<Score_ManagerVO> selectAll() {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 4-2. 개별 학생 회원정보 조회를 위한 함수
	public ArrayList<Score_ManagerVO> selectST(String id) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 5-1. 학생 자신의 계정 비밀번호 수정을 위한 함수
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
	// 5-2. 교사 자신의 계정 비밀번호 수정을 위한 함수
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
	// 6-1. 교사가 학생 성적 수정을 위한 함수(국어)
	public int updateKOR(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1학기 중간고사")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1학기 기말고사")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2학기 중간고사")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2학기 기말고사")) {
				sql = "update score_table set KOR = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("잘못된 메소드값이 입력되었습니다.");
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
	// 6-2. 교사가 학생 성적 수정을 위한 함수(영어)
	public int updateENG(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1학기 중간고사")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1학기 기말고사")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2학기 중간고사")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2학기 기말고사")) {
				sql = "update score_table set ENG = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("잘못된 메소드값이 입력되었습니다.");
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
	// 6-3. 교사가 학생 성적 수정을 위한 함수(수학)
	public int updateMATH(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1학기 중간고사")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1학기 기말고사")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2학기 중간고사")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2학기 기말고사")) {
				sql = "update score_table set MATH = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("잘못된 메소드값이 입력되었습니다.");
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
	// 6-4. 교사가 학생 성적 수정을 위한 함수(사회)
	public int updateSOCIETY(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1학기 중간고사")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1학기 기말고사")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2학기 중간고사")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2학기 기말고사")) {
				sql = "update score_table set SOCIETY = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("잘못된 메소드값이 입력되었습니다.");
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
	// 6-5. 교사가 학생 성적 수정을 위한 함수(과학)
	public int updateSCIENCE(int stu_no, int score, String test_day) {
		getConn();

		try {
			String sql = "";
			if (test_day.equals("1학기 중간고사")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('1-m')";
			} else if (test_day.equals("1학기 기말고사")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('1-e')";
			} else if (test_day.equals("2학기 중간고사")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('2-m')";
			} else if (test_day.equals("2학기 기말고사")) {
				sql = "update score_table set SCIENCE = ? where STU_NO=? and test_day in ('2-e')";
			} else {
				System.out.println("잘못된 메소드값이 입력되었습니다.");
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
	// 7-1. 교사가 학생 성적 입력을 위한 함수
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
	// 8-1. 반별 학생성적 전체 조회를 위한 함수
	public ArrayList<Score_ManagerVO> selectScoreTCAll(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 8-1-2. 반별 학생성적 전체 조회를 위한 함수(1학기 중간)
	public ArrayList<Score_ManagerVO> selectScoreTC_1m(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 8-1-3. 반별 학생성적 전체 조회를 위한 함수(1학기 기말)
	public ArrayList<Score_ManagerVO> selectScoreTC_1e(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 8-1-4. 반별 학생성적 전체 조회를 위한 함수(2학기 중간)
	public ArrayList<Score_ManagerVO> selectScoreTC_2m(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 8-1-5. 반별 학생성적 전체 조회를 위한 함수(2학기 기말)
	public ArrayList<Score_ManagerVO> selectScoreTC_2e(int Class) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 8-2. 학생별 학생성적 조회를 위한 함수
	public ArrayList<Score_ManagerVO> selectScoreST(String user_id) {
		getConn();

		ArrayList<Score_ManagerVO> list = new ArrayList<>();
		// 로딩,커넥,수행,종료
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
	// 9-1-1. 학생정보 삭제를 위한 함수(학생테이블)
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
	// 9-1-2. 학생점수 삭제를 위한 함수(점수테이블)
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
	// 9-2. 선생정보 삭제를 위한 함수
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
	// 10-1. 공지사항 입력
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
	// 10-2. 공지사항 출력
	public String getNotice() {
		getConn();

		String notice = "";
		// 로딩,커넥,수행,종료
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
	// 11-1. 차트조회용 테이블 초기화
	public int dropChartTable() {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 11-2. 차트조회용 테이블 생성
	public int createChartTable() {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 11-3. 차트조회용 테이블에 로그인한 학생의 성적 저장
	public int insertChartTable(String id) {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 12-1-1. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(국어/1학기 중간)
	public int getChartScore_KOR_1m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-1-2. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(국어/1학기 기말)
	public int getChartScore_KOR_1e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-1-3. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(국어/2학기 중간)
	public int getChartScore_KOR_2m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-1-4. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(국어/2학기 기말)
	public int getChartScore_KOR_2e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-2-1. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(영어/1학기 중간)
	public int getChartScore_ENG_1m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-2-2. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(영어/1학기 기말)
	public int getChartScore_ENG_1e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-2-3. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(영어/2학기 중간)
	public int getChartScore_ENG_2m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-2-4. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(영어/2학기 기말)
	public int getChartScore_ENG_2e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-3-1. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(수학/1학기 중간)
	public int getChartScore_MATH_1m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-3-2. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(수학/1학기 기말)
	public int getChartScore_MATH_1e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-3-3. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(수학/2학기 중간)
	public int getChartScore_MATH_2m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-3-4. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(수학/2학기 기말)
	public int getChartScore_MATH_2e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-4-1. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(사회/1학기 중간)
	public int getChartScore_SOCIETY_1m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-4-2. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(사회/1학기 기말)
	public int getChartScore_SOCIETY_1e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-4-3. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(사회/2학기 중간)
	public int getChartScore_SOCIETY_2m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-4-4. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(사회/2학기 기말)
	public int getChartScore_SOCIETY_2e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-5-1. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(과학/1학기 중간)
	public int getChartScore_SCIENCE_1m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-5-2. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(과학/1학기 기말)
	public int getChartScore_SCIENCE_1e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-5-3. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(과학/2학기 중간)
	public int getChartScore_SCIENCE_2m() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 12-5-4. 차트조회용 테이블에 로그인한 학생의 성적 정보 불러오기(과학/2학기 기말)
	public int getChartScore_SCIENCE_2e() {
		getConn();
		int score = 0;

		// 로딩,커넥,수행,종료
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
	// 13-1. 선생님 로그인 정보 테이블 초기화
	public int dropTeacherLoginTable() {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 13-2. 선생님 로그인 정보 테이블 생성
	public int createTeacherLoginTable() {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 13-3. 선생님 로그인 정보 테이블에 로그인한 선생님의 정보 저장
	public int insertTeacherLoginTable(String id) {
		getConn();

		// 로딩,커넥,수행,종료
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
	// 13-4. 선생님 로그인 정보 테이블에 로그인한 선생님의 반 정보 불러오기
	public int getClassNo() {
		getConn();
		int van = 1;

		// 로딩,커넥,수행,종료
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
