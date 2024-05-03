package chat.gui;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {	

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			
			System.out.println("닉네임을 입력하세요.");
			System.out.print(">> ");
			name = scanner.nextLine();
			
			if (!name.isEmpty()) {
				break;
			}
			
			System.out.println("닉네임은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();

		new ChatWindow(name).show();
	}

}