import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class CookieClient {
	public static void main(String args[]){
		//read in the host and port address
		String theHost = args[0];
		String portNo = args[1];
		int portNum = Integer.parseInt(portNo);
		
		Socket theSocket = null;
		try {
			//create the socket, get inputstream and read message from it
			theSocket = new Socket(theHost, portNum);
			System.out.println("Connecting to " + theHost + ": " + portNo);
			InputStream theInput = theSocket.getInputStream();
			System.out.println("Connection established");

			byte [] toGetSize = new byte[1000];
			int size = theInput.read(toGetSize);
			byte [] msgBytes = new byte[size+1];
			for(int x = 0; x < size; x++){
			    msgBytes[x] = toGetSize[x];
			}
			
			String msgStr = new String(msgBytes);
			System.out.println("Your Fortune:  " + msgStr);
			theSocket.close();
			System.out.println("Exiting");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
