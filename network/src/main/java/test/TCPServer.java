package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding)
			//    Socket에 InetSocketAddress[InetAddress(IPAddress) + Port]를 바인딩 한다.
			//    IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩 하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000), 10);
			
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking
			
			System.out.println("연결!!!");			
			
		} catch (IOException e) {
			System.out.println("[server] error: " + e);
			
		} finally {
			try {
				if((serverSocket != null) && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
