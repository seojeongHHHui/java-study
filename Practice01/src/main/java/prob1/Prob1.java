package prob1;

import java.util.Scanner;

public class Prob1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		
		int num = scanner.nextInt();
		
		if(num!=0 && num%3==0) {
			System.out.println("3의 배수입니다.");
		}
		else {
			System.out.println("3의 배수가 아닙니다.");
		}
		
		scanner.close();
	}
}
