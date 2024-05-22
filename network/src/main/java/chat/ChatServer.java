package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 7000;
	private static List<Writer> listWriters = new ArrayList<Writer>(); // writer pool
	
	public static void main(String[] args) {		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			//String hostAddress = InetAddress.getLocalHost().getHostAddress();
			String hostAddress = "0.0.0.0";
			serverSocket.bind( new InetSocketAddress( hostAddress, PORT ));
			log( "연결 기다림 " + hostAddress + ":" + PORT );
			
			while(true) {
				Socket socket = serverSocket.accept();  // blocking
				new ChatServerThread( socket, listWriters ).start();
			}
			
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[ChatServer] " + message);
	}

}
