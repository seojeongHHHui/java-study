package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread {
	private Socket socket;
	private String nickname;
	private List<Writer> listWriters;
	private PrintWriter printWriter;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}
	
	@Override
	public void run() {
		try {
			//1. Remote Host Information
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			ChatServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
			//2. 스트림 얻기
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			//3. 요청 처리
			while(true) {
				String request = br.readLine(); // blocking
				
				if(request == null) { //클라이언트가 “quit” 보내지 않고 소켓을 닫은 경우
					ChatServer.log( "클라이언트로 부터 연결 끊김" );
					doQuit( printWriter );
					break;
				}
				
				// 4. 프로토콜 분석				
				String[] tokens = request.split(":");
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
					
				} else if("message".equals(tokens[0])) {
					doMessage(tokens[1]);
					
				} else if("quit".equals(tokens[0])) {
					doQuit(printWriter);
					break;
					
				} else {
					ChatServer.log("에러:알수 없는 요청(" + tokens[0] + ")");
				}
			}
		} catch(SocketException e) {
			doQuit( printWriter );
			ChatServer.log("Socket Exception: " +e);
		} catch(IOException e) {
			doQuit( printWriter );
			ChatServer.log("error:" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void doJoin( String nickName, Writer writer ) { // join 프로토콜 구현
		this.nickname = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		broadcast( data );
		
		/* writer pool에 저장 */
		addWriter( writer );
		
		// ack
		printWriter.println( "채팅방에 입장하셨습니다." );
		printWriter.flush();
	}
	
	
	private void addWriter( Writer writer ) {
		synchronized( listWriters ) {
			listWriters.add( writer );
		}
	}
	
	private void broadcast( String data ) {
		synchronized( listWriters ) {
			for( Writer writer : listWriters ) {
				PrintWriter printWriter = (PrintWriter)writer;
				printWriter.println( data );
				printWriter.flush();
			}
		}
	}
	
	
	private void doMessage( String message ) {
		String data = nickname + ":" + message;
		broadcast( data );
	}
	
	
	private void doQuit( Writer writer ) { // 프로토콜 "quit"
		removeWriter( writer );
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast( data );
		

		printWriter.println( "채팅방에서 퇴장하셨습니다.1" );
		printWriter.flush();
		
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void removeWriter( Writer writer ) {
		synchronized( listWriters ) {
			listWriters.remove( writer );
		}

	}
		
		
	
}
