import java.net.*;

class UDPServer { 
	public static void main(String args[]) throws Exception { 
		//create the server socket on port 9876
		DatagramSocket serverSocket = new DatagramSocket(9876); 
		
		//setup variables
		byte[] receiveData = new byte[1024]; 
		byte[] sendData  = new byte[1024]; 
		
		//keep the server up with a while loop
		while(true) { 
			//look to receive packets and actually receive them.
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
			serverSocket.receive(receivePacket);
			
			//transform data into a String and get the address that it was sent from.
			String sentence = new String(receivePacket.getData()); 
			InetAddress IPAddress = receivePacket.getAddress(); 
			
			//get the port that it came from.
			int port = receivePacket.getPort(); 
			
			//change data into uppercase and transform it into bytes
			String capitalizedSentence = sentence.toUpperCase(); 
			sendData = capitalizedSentence.getBytes(); 
			
			//send data back to the client that you recieved it from
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
			serverSocket.send(sendPacket);
		}
	}
}