package Score_Manager_Package;


public class Score_ManagerVO {
	
	//Model : 접근하고자하는 데이터베이스 테이블의 모든 자료를 정리하는곳
	
	//members 테이블 : id, pw, name, van
	
	//필드 지정
	private String id;
	private String pw;
	private String name;
	private int STU_NO;
	private int TCH_NO;
	private int van;
	private int kor;
	private int eng;
	private int math;
	private int society;
	private int science;
	private String test_day;
	String login_id;
	
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}


	int cnt1 = 1;
	int cnt0 = 0;
	

	//회원가입시 사용하기 위한 생성자 메소드 생성
	public Score_ManagerVO(String id,String pw, String name, int van) {
		//필드의 변수와 매개변수 연결하기
		this.id = id;//1
		this.pw = pw;//2
		this.name = name;//3
		this.van = van;	//4

	}
	public int getCnt1() {
		return cnt1;
	}
	public void setCnt1(int cnt) {
		this.cnt1 = cnt;
	}
	// 각 필드에 따른 get/set 메소드 생성
	public Score_ManagerVO(int STU_NO,String id,String pw, String name,int TCH_NO , int van) {
		//필드의 변수와 매개변수 연결하기
		this.STU_NO = STU_NO;//1
		this.id = id;//2
		this.pw = pw;//3
		this.name = name;//4
		this.TCH_NO =TCH_NO;//5
		this.van = van;	//6

	}


	public int getSTU_NO() {
		return STU_NO;
	}
	public void setSTU_NO(int sTU_NO) {
		STU_NO = sTU_NO;
	}
	public int getTCH_NO() {
		return TCH_NO;
	}
	public void setTCH_NO(int tCH_NO) {
		TCH_NO = tCH_NO;
	}
	public Score_ManagerVO(String id, String name, int van,int kor,int eng,int math,int society,int science, String test_day) {
		//필드의 변수와 매개변수 연결하기
		this.id = id;//1
		this.name = name;//2
		this.van = van;	//3
		this.kor = kor;//4
		this.eng = eng;//5
		this.math = math;//6
		this.society = society;//7
		this.science = science;//8
		this.test_day = test_day;//9
	}


	public String getTest_day() {
		return test_day;
	}


	public void setTest_day(String test_day) {
		this.test_day = test_day;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}


	public int getSociety() {
		return society;
	}


	public void setSociety(int society) {
		this.society = society;
	}


	public int getScience() {
		return science;
	}


	public void setScience(int science) {
		this.science = science;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getVan() {
		return van;
	}


	public void setVan(int van) {
		this.van = van;
	}

	
	
}
