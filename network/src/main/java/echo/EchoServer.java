package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 6000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("starts....[port:" + PORT + "]");
			
			while(true) {
				Socket socket = serverSocket.accept();  // blocking
				new EchoRequestHandler(socket).start();
			}
			
			
/// thread로	(다중처리 echo 서버 만들기)		
//			try {
//				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
//				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
//				int remotePort = inetRemoteSocketAddress.getPort();
//				log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
//				
//				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
//				
//				while(true) {
//					String data = br.readLine(); // blocking
//					if(data == null) {
//						log("closed by client");
//						break;
//					}
//					log("received:" + data);
//					pw.println(data);
//				}
//			} catch(SocketException e) {
//				log("Socket Exception: " +e);
//			} catch(IOException e) {
//				log("error:" + e);
//			} finally {
//				try {
//					if(socket != null && !socket.isClosed()) {
//						socket.close();
//					}
//				} catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
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
		System.out.println("[EchoServer] " + message);
	}
}