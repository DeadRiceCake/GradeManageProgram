package oop;

public class Main {

	public static void main(String[] args) {
		//��ü(object)�� �����ϴ� ����
		//���赵(class)������� ��ü�� �޸𸮿� �Ҵ��ϴ� ���� >> Ŭ������ �ν��Ͻ�ȭ
		People hyung = new People();
		//new : ���۷��� ����(������ ����)�� ������ �� ���� Ű����.
		System.out.println(hyung);
		//���۷��������� �ּҰ��� ������ �ִ�.
		//�����ϴ� ����� .�� ���� �����Ѵ�.
		System.out.println(hyung.age);
		System.out.println(hyung.name);
		//�ʵ尡 ä������ ���� ��� 
		//�⺻�� ������ Ÿ���Ǳ⺻��=0
		//������ ������ Ÿ���� �⺻��=null
		
		//�̸� : ����
		hyung.name="����";
		//���� : 20��
		hyung.age=20;
		//Ű : 185.9
		hyung.height=185.9;
		//��� : ����, ��, ��
		hyung.organs=new String[3];
		hyung.organs[0]="����";
		hyung.organs[1]="��";
		hyung.organs[2]="��";
		
		System.out.println(hyung.name);
		System.out.println(hyung.organs[1]);
		
		hyung.eat();
		hyung.sleep();
		
	}

}
