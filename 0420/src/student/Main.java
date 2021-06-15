package student;

public class Main {

	public static void main(String[] args) {
		
		//
		Student s2 = new Student("정형", 20, "20210421");
		Student s3 = new Student("정형","20200421");
		
		System.out.println(s2.getName());
		System.out.println(s2.getAge());
		System.out.println(s2.getNumber());
		
		s2.setScoreJava(50);
		System.out.println(s2.getScoreJava());
		
	}

}
