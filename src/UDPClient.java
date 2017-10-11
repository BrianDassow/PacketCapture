import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String args[]) throws Exception {
		String sentence;
		
		//keep sending data to server unless the keyword "QUIT" is used.
		do {

			//create new buffered reader
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			
			//create client socket
			DatagramSocket clientSocket = new DatagramSocket();
			
			//setup address where we want to connect to
			InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
			
			//set up variables
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			//ask user to enter something and read it.
			System.out.print("Enter Something: ");
			sentence = inFromUser.readLine();
			
			//transform entered data into bytes
			sendData = sentence.getBytes();
			
			//create a packet with the data and send it over the socket.
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 
			clientSocket.send(sendPacket); 
			
			//receive data back in the form of a packet.
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
			clientSocket.receive(receivePacket); 
			
			//create a string out of the data, and print it.
			String modifiedSentence = new String(receivePacket.getData()); 
			System.out.println("FROM SERVER:" + modifiedSentence);
			
			//close the connection
			clientSocket.close();
		} while(!sentence.equals("QUIT"));
	}
}
