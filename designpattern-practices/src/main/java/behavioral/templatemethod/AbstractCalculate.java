package behavioral.templatemethod;

import java.util.Scanner;

@SuppressWarnings("resource")
public abstract class AbstractCalculate {
	public void templateMethod() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("(val1, val2) > ");
		int val1 = scanner.nextInt();
		int val2 = scanner.nextInt();
		
		int result = calculate(val1, val2);
		
		System.out.println(result);
	}

	public abstract int calculate(int val1, int val2);
}
