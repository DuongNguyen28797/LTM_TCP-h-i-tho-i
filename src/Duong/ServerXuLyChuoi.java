package Duong;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerXuLyChuoi {
	public static final String IP = "localhost";
	public static final int PORT = 7004;
	private ServerSocket serverSocket;

	public ServerXuLyChuoi(String host, int port) {
		try {
			serverSocket = new ServerSocket(port);
			SocketThread socket = new SocketThread(serverSocket.accept());
			new Thread(socket).start();
		} catch (IOException ex) {
			Logger.getLogger(ServerXuLyChuoi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		new ServerXuLyChuoi(IP, PORT);
	}
}

class SocketThread implements Runnable {
	private Socket s;

	public SocketThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			String serverReceive = dis.readUTF();
			String serverResponse = serverReceive.toUpperCase() + "," 
									+ serverReceive.toLowerCase() + ","
									+ UpperFirst(serverReceive) + ","
									+Converse(serverReceive) + ","
									+DeleteVowel(serverReceive)+ ","
									+ConverseString(serverReceive)+ ","
									+ countWords(serverReceive);

			dos.writeUTF(serverResponse);
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private int countWords(String s) {
		int wordCount = 0;
		boolean insideWord = false;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)) && i != s.length() - 1) {
				insideWord = true;
			} else if (!Character.isLetter(s.charAt(i)) && insideWord) {
				wordCount++;
				insideWord = false;
			} else if (Character.isLetter(s.charAt(i)) && i == s.length() - 1) {
				wordCount++;
			}
		}
		return wordCount;
	}
	
	public static String UpperFirst(String s)
    {
       if(s != null && s.isEmpty()){
    	   System.out.println("The function is wrong here");
    	   return s;
       }
        String result = "";
        //lấy danh sách các từ  
        String[] words = s.split(" ");
        for (String word : words) {
			if(word.trim() != ""){
				if(word.length() >1)
					result += word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase() + " ";
				else
					result += word.toUpperCase() + " ";
			}
		}
        return result.trim();
    }
	
	public static String Converse(String str){
		String re = "";
		char c;
		for(int i = 0; i < str.length(); i++){
			c = str.charAt(i);
			if ((c <= 'Z') && (c >= 'A')){
				re+= (char)((int)c - (int)'A' + (int)'a');
			}
			else if((c <= 'z') && (c >= 'a')){
				re+= (char)((int)c - (int)'a' + (int)'A');
			}
			else{
				re+=c;
			}
		}
		return re;
	}
	
	public static String DeleteVowel(String s){
		String[] vowel={"a","e","i","o","u","A","E","I","O","U"};
		  for(String ch:vowel){
	        	if(s.contains(ch))
                s=s.replaceAll(ch, "");
	        }
		  
		  return s;
	}

	public static String ConverseString(String s){
		s = new StringBuffer(s).reverse().toString();
		return s;
	}
}