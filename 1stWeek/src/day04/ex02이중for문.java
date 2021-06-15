package day04;

public class ex02이중for문 {

	public static void main(String[] args) {
		
		//별찍기 풀이 1
		for (int i = 0; i < 5; i++) {
			for (int j = 4; j > i; j--) {
				System.out.print(" ");
			}
			for (int k = 0; k <= i; k++) {
				System.out.print("*");
			}

			System.out.println();
		}
		
		//별찍기 풀이 2
		for (int i = 1; i <= 5; i++) {
			for (int j = 5; j > 0; j--) {
				if (j>i) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}

			System.out.println();
		}
	}
}
