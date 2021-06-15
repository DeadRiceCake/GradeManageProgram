package student;

public class Student {
	//설계도를 작성하는 공간
	
	//이름, 학번, 나이,를 생성하는 순간에 데이터를 채워주는 생성자를 만들기
	//생성자(constructor)
	//1. 메소드
	//2. 틀래스와 생성자(메소드)의 이름이 동일해야 한다.
	//3. 리턴타입을 지정하지 않는다. void 조차 작성하지 않는다.
	//4. 메소드의 중복정의(오버로딩)이 가능하다.
	public Student(String name, int age, String number) {
		this.name = name;
		this.age = age;
		this.number = number;
	}
	public Student(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	
	
	//1. 필드
	private String name;        //이름
	private String number;      //학번
	private int age;            //나이
	private int scoreJava;      //Java 점수
	private int scoreWeb;       //Web 점수
	private int scoreAndroid;   //Android 점수
	//================================================================
	//Alt+Shift+S, Generate Getters and Setters: get, set 자동 생성
	public int getScoreJava() {
		return scoreJava;
	}
	public void setScoreJava(int scoreJava) {
		this.scoreJava = scoreJava;
	}
	public int getScoreWeb() {
		return scoreWeb;
	}
	public void setScoreWeb(int scoreWeb) {
		this.scoreWeb = scoreWeb;
	}
	public int getScoreAndroid() {
		return scoreAndroid;
	}
	public void setScoreAndroid(int scoreAndroid) {
		this.scoreAndroid = scoreAndroid;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public int getAge() {
		return age;
	}
	
	
	
}
