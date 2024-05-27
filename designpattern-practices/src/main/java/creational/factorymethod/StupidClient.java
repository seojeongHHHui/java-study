package creational.factorymethod;

import java.util.Scanner;

public class StupidClient {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("화면선택(2d, 3d): ");
		String dimension = scanner.nextLine();
		
		System.out.print("도형번호: ");
		int shapeNo = scanner.nextInt();

		if("3d".equals(dimension)) {
			new Screen3D().display(shapeNo);
		} else {
			new Screen2D().display(shapeNo);
		}
	}
	
	private static class Screen2D {
		public void display(int shapeNo) {			
			if(shapeNo == 1) {
				System.out.println("삼각형을 그렸습니다.");
			} else if(shapeNo == 2) {
				System.out.println("사각형을 그렸습니다.");
			} else if(shapeNo == 3) {
				System.out.println("원을 그렸습니다.");
			} else {
				System.out.println("도형이 없습니다.");
			}
		}
	}
	
	private static class Screen3D {
		public void display(int shapeNo) {
			if(shapeNo == 1) {
				System.out.println("구를 그렸습니다.");
			} else if(shapeNo == 2) {
				System.out.println("사각기둥을 그렸습니다.");
			} else if(shapeNo == 3) {
				System.out.println("원뿔을 그렸습니다.");
			} else {
				System.out.println("도형이 없습니다.");
			}
		}
	}
}