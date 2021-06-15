package oop;

public class People {
	//설계도면을 작성하는 공간 >> 추상화 작업
	//1. 필드(속성, 데이터)
	//- 이름
	String name;
	//- 나이
	int age;
	//- 키
	double height;
	//- 장기
	String[] organs;
	
	
	
	
	
	
	//2. 메소드(기능, 로직)
	//- 밥먹는다
	public void eat() {
		System.out.println("밥을 먹는다.");
	}
	//- 잔다
	public void sleep() {
		System.out.println(name+"이 잠을 잔다.");
	}
	//- 공부한다
	public void study() {
		System.out.println("공부를 한다.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
