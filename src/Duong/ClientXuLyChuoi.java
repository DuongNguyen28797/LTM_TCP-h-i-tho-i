package Duong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientXuLyChuoi {
	private static Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public ClientXuLyChuoi() {
		JFrame f = new JFrame("Nguyễn Lê Thùy Dương");
		f.setSize(1000, 1000);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		
		JLabel jbEnterString = new JLabel("Client request");
		jbEnterString.setBounds(10, 10, 100, 50);
		f.add(jbEnterString);
		final JTextArea jtaClientSend = new JTextArea(100,100);
		jtaClientSend.setBounds(120, 10, 750, 50);
		f.add(jtaClientSend);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(900, 10, 80, 50);
		f.add(btnSend);

		JLabel jbResult = new JLabel("SERVER RESPONSE");
		jbResult.setBounds(450, 60, 200, 50);
		f.add(jbResult);

		JLabel jbUppercase = new JLabel("Uppercase");
		jbUppercase.setBounds(10, 110, 100, 50);
		f.add(jbUppercase);
		final JTextArea jtaResultUppercase = new JTextArea();
		jtaResultUppercase.setBounds(120, 110, 750, 50);
		f.add(jtaResultUppercase);

		JLabel jbLowercase = new JLabel("Lowercase");
		jbLowercase.setBounds(10, 190, 100, 50);
		f.add(jbLowercase);
		final JTextArea jtaResultLowercase = new JTextArea();
		jtaResultLowercase.setBounds(120, 190, 750, 50);
		f.add(jtaResultLowercase);
		
		JLabel jbUpperFirst = new JLabel("Upper Case First");
		jbUpperFirst.setBounds(10, 270, 100, 50);
		f.add(jbUpperFirst);
		final JTextArea jtaUpperFirst = new JTextArea();
		jtaUpperFirst.setBounds(120, 270, 750, 50);
		f.add(jtaUpperFirst);
		
		JLabel jbConverse = new JLabel("Upper and Lower");
		jbConverse.setBounds(10, 350, 100, 50);
		f.add(jbConverse);
		final JTextArea jtaConverse = new JTextArea();
		jtaConverse.setBounds(120, 350, 750, 50);
		f.add(jtaConverse);
		
		JLabel jbDeleteVowel = new JLabel("Delete Vowel");
		jbDeleteVowel.setBounds(10, 430, 100, 50);
		f.add(jbDeleteVowel);
		final JTextArea jtaDeleteVowel = new JTextArea();
		jtaDeleteVowel.setBounds(120, 430, 750, 50);
		f.add(jtaDeleteVowel);
		
		JLabel jbConverseString = new JLabel("Converse String");
		jbConverseString.setBounds(10, 510, 100, 50);
		f.add(jbConverseString);
		final JTextArea jtaConverseString = new JTextArea();
		jtaConverseString.setBounds(120, 510, 750, 50);
		f.add(jtaConverseString);
		
		JLabel jbNumberString = new JLabel("Number");
		jbNumberString.setBounds(10, 590, 100, 50);
		f.add(jbNumberString);
		final JTextArea jtfResultNumbberString = new JTextArea();
		jtfResultNumbberString.setBounds(120, 590, 750, 50);
		f.add(jtfResultNumbberString);
		
		
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					socket = new Socket("localhost",7004);
					dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream  = new DataOutputStream(socket.getOutputStream());
					String msg = jtaClientSend.getText();
					dataOutputStream.writeUTF(msg);
					dataOutputStream.flush();
					
					String clientReceive = dataInputStream.readUTF();
					String[] arrReceive = clientReceive.split(",");
					jtaResultUppercase.append(arrReceive[0]);
					jtaResultLowercase.append(arrReceive[1]);
					jtaUpperFirst.append(arrReceive[2]);
					jtaConverse.append(arrReceive[3]);
					jtaDeleteVowel.append(arrReceive[4]);
					jtaConverseString.append(arrReceive[5]);
					jtfResultNumbberString.setText(arrReceive[6]);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		f.setVisible(true);
	}


	public static void main(String[] args) {
		new ClientXuLyChuoi();
	}

}