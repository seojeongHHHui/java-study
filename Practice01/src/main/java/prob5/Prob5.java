package prob5;

public class Prob5 {

	public static void main(String[] args) {
		int num=1, result;
		
		while(num <= 99) {
			result = 0;
			
			if(num>=10 && (num/10)%3==0)
				result++;
				
			if(num%10!=0 && (num%10)%3 == 0)
				result++;
			
			if(result>0) {
				System.out.print(num + " ");
				for(int i=0; i<result; i++)
					System.out.print("짝");
				System.out.println();
			}
			
			num++;
		}
		
	}
}
