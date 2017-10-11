import java.io.*; 
import java.net.*;

class TCPServer { 
	public static void main(String argv[]) throws Exception { 
		//setup variables
		String clientSentence; 
		String capitalizedSentence;
		
		//setup the server socket on port 6789
		ServerSocket welcomeSocket = new ServerSocket(6789); 
		
		//setup while to keep the server going
		while(true) { 
			//look for packets and accept them.
			Socket connectionSocket = welcomeSocket.accept(); 
			//read input data and setup variable to send back to client.
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			//read data from client
			clientSentence = inFromClient.readLine(); 
			
			//capitalize the sentence and send it back to the client.
			capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
			outToClient.writeBytes(capitalizedSentence); 
		}
	}
}