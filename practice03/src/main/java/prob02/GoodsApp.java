package prob02;

import java.util.Scanner;
import java.util.StringTokenizer;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		String name;
		int price, count;
		
		for(int i=0; i<goods.length; i++) {
			name = scanner.next();
			price = scanner.nextInt();
			count = scanner.nextInt();
			/*
			StringTokenizer st = new StringTokenizer(information);
			name = st.nextToken();
			price = Integer.valueOf(st.nextToken());
			count = Integer.valueOf(st.nextToken());
			*/
			goods[i] = new Goods(name, price, count);
		}

		// 상품 출력
		for(int i=0; i<goods.length; i++) {
			System.out.println(goods[i].getName()+"(가격:"+goods[i].getPrice()+"원)이 "+goods[i].getCount()+"개 입고 되었습니다.");
		}
		
		// 자원정리
		scanner.close();
	}
}
