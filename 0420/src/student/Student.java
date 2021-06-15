package student;

public class Student {
	//���赵�� �ۼ��ϴ� ����
	
	//�̸�, �й�, ����,�� �����ϴ� ������ �����͸� ä���ִ� �����ڸ� �����
	//������(constructor)
	//1. �޼ҵ�
	//2. Ʋ������ ������(�޼ҵ�)�� �̸��� �����ؾ� �Ѵ�.
	//3. ����Ÿ���� �������� �ʴ´�. void ���� �ۼ����� �ʴ´�.
	//4. �޼ҵ��� �ߺ�����(�����ε�)�� �����ϴ�.
	public Student(String name, int age, String number) {
		this.name = name;
		this.age = age;
		this.number = number;
	}
	public Student(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	
	
	//1. �ʵ�
	private String name;        //�̸�
	private String number;      //�й�
	private int age;            //����
	private int scoreJava;      //Java ����
	private int scoreWeb;       //Web ����
	private int scoreAndroid;   //Android ����
	//================================================================
	//Alt+Shift+S, Generate Getters and Setters: get, set �ڵ� ����
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
