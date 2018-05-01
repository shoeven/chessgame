package chess;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * Koden brukt for Server/client og bruk av sockets er inspirert av en annen utvikler; "McProgramming"
 * Kilde hvor vi har hentet kodebit: https://www.youtube.com/watch?v=UryaQUjiPSw
 * 
 * Kjør server classen først, deretter Client classen.
 * De kommuniserer sammen, ved å sende String gjennom console vindu
 */

public class Client implements java.io.Serializable {
	
	private Socket clientSocket;
	private BufferedReader input;
	private PrintStream output;
	private Scanner scan = new Scanner(System.in);
	private String fenstring = PieceMovement.getFenstring();
	private String startpos = PieceMovement.getStartpos();
	private String endpos = PieceMovement.getEndpos();
	private String stringcommand = "position fen " + fenstring + " moves " + startpos + endpos;
	
	
	static Socket socket;
	static DataInputStream in;
	
	public static void main(String[]args) {
		new Playboard();
		Client client = new Client();
		client.run();
	}
	
	public void run() {
		try {			
			clientSocket = new Socket ("localhost", 8013); // ip adresse, portnummer
			
			output = new PrintStream (clientSocket.getOutputStream());
		
			output.println("Hello server"); 
			
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//While løkke som tillater kontinuerlig kommunikasjon mellom client og server
			while(clientSocket.isConnected()) {
								
				String message = stringcommand;
				System.out.println("Server: " + message);
				
				String reply = stringcommand;
				output.println(reply);
			}
			

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}