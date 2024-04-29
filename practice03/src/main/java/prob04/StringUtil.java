package prob04;

public class StringUtil {
	public static String concatenate(String[] str) {
		//문자열을 결합하여 리턴 하는 메소드 구현
		String result = str[0];
		
		for(int i=1; i<str.length; i++) {
			result = result+str[i];
		}
		
		return result;
	}
}
