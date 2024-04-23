package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("숫자를 입력하세요: ");
		int num = scanner.nextInt();
		int result = 0;
		
		if(num%2 != 0) { // 홀수인 경우
			for(int i=num; i>0; i--) {
				if(i%2 != 0) // 홀수만 더하기
					result += i;
			}
		}
		
		else { // 짝수인 경우
			for(int i=num; i>0; i--) {
				if(i%2 == 0) // 짝수만 더하기
					result += i;
			}
		}
		
		System.out.println("결과 값 : " + result);
		
		scanner.close();
	}
}
