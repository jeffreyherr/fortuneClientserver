import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class CookieServer {
	public static void main(String args[]) throws IOException{
		//read in port address from arguments
        String portNo = args[0];
        int portNum = Integer.parseInt(portNo);
		ServerSocket theserverSocket = new ServerSocket(portNum);
		try {
			//Listen on port, recieve connection, write message into output stream
			System.out.println("Listening on port " + portNo + "...");
			Socket actualSocket = theserverSocket.accept();
			System.out.println("Connection established");
			OutputStream theOutput = actualSocket.getOutputStream();
			String theMsg = randomMessage();
			byte [] msgInByte = theMsg.getBytes();
			theOutput.write(msgInByte);
			System.out.println("Fortune sent: " + theMsg);
			actualSocket.close();
			theserverSocket.close();	
			System.out.println("Exiting");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**********************
	 * Picks a random line in a text file
	 * @return the randomly selected message from text file
	 */
	private static String randomMessage() {
		File fortunes = new File("fortunes.txt");
		
		//count the number of lines in text file
		int lineCnt = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fortunes))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		       lineCnt++;
		    }
		    System.out.println(lineCnt);
		    
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//pick random line in the text filel
		Random randomize = new Random();
		int randInt = randomize.nextInt(lineCnt) + 1;
		try (BufferedReader reader = new BufferedReader(new FileReader(fortunes))) {
		    String realLine;
		    int count = 1;
		    while ((realLine = reader.readLine()) != null) {
			   if(count == randInt) {
				   return realLine;
			   }
			   count++;
			}
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return " ";
	}
}
