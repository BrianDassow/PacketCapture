import java.io.*; 
import java.net.*; 

class TCPClient{ 
	public static void main(String argv[]) throws Exception {
		//setup variables
		String sentence; 
		String modifiedSentence; 
		
		//keep sending data to server unless the keyword "QUIT" is used.
		do {
			//setup reader to read from system.in
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
			
			//create socket connection to 127.0.0.1 (this computer) on port 6789
			Socket clientSocket= new Socket("127.0.0.1", 6789); 
			
			//setup variable to send to server and variable to read from server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			
			//ask user to enter something and read it.
			System.out.print("Enter Something: ");
			sentence = inFromUser.readLine(); 
			
			//send data to server and read from server
			outToServer.writeBytes(sentence + '\n'); 
			modifiedSentence = inFromServer.readLine(); 
			
			//print items from server and close the socket connection.
			System.out.println("FROM SERVER: " + modifiedSentence); 
			clientSocket.close(); 
		}
		while (!sentence.equals("QUIT"));

	} 
}