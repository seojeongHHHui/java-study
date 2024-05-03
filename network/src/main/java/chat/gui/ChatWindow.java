package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import chat.ChatServer;

public class ChatWindow {
	
	private static final String SERVER_IP = "127.0.0.1";
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	
	private String name;
	private Socket socket = null;
	private PrintWriter printWriter;
	private List<Writer> listWriters;
	private BufferedReader br;

	public ChatWindow(String name) {
		this.name = name;
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			
			// IOSTream 받아오기
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// join
		printWriter.println( "join:" + name );
		printWriter.flush();
		
		// ChatClientThread 생성
		new ChatClientThread().start();
	}
	
	private void sendMessage() {
		String message = textField.getText();
		//System.out.println("메세지 보내는 프로토콜을 구현!:" + message);
		
		textField.setText("");
		textField.requestFocus();
		
		if("quit".equals(message)) {
			finish();
			
		} else {
			printWriter.println("message:" + message);
			printWriter.flush();
		}

		// ChatClientThread에서 서버로 부터 받은 메세지가 있다고 치고!!!!!~~~~
		//updateTextArea("마이콜:" + message);
		
		
		
		
		

	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		// quit protocol 구현
		printWriter.println("quit");
		printWriter.flush();
		
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// exit java application
		System.exit(0);
	}
	
	private class ChatClientThread extends Thread {
		public void run() {
			// String message = br.readline();
			//updateTextArea("마이콜:밥먹으러 가자");
			
			try {
				//br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				
				while(true) {
					String data = br.readLine();
					
					if(socket.isClosed()) {
						break;
					}
					
					updateTextArea(data);
				}	
					
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}