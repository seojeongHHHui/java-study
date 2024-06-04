package behavioral.strategy;

import java.util.Scanner;

public class CalculateContext {
	public void execute(CalculateStrategy calculateStrategy) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("(val1, val2) > ");
		int val1 = scanner.nextInt();
		int val2 = scanner.nextInt();
		
		int result = calculateStrategy.calculate(val1, val2);
		
		System.out.println(result);
	}
	
}
