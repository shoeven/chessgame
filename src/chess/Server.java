package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Koden brukt for server/client og bruk av sockets er inspirert av en annen utvikler; "McProgramming"
 * Kilde hvor vi har hentet kodebit: https://www.youtube.com/watch?v=UryaQUjiPSw
 * 
 * Kjør server classen først, deretter Client classen.
 * De kommuniserer sammen, ved å sende String gjennom console vindu
 */

public class Server implements java.io.Serializable {

	private ServerSocket serverSocket;
	private Socket acceptSocket;
	private PrintStream output;
	private BufferedReader input;
	private Scanner scan = new Scanner(System.in);
	private String fenstring = PieceMovement.getFenstring();
	private String startpos = PieceMovement.getStartpos();
	private String endpos = PieceMovement.getEndpos();
	private String stringcommand = "position fen " + fenstring + " moves " + startpos + endpos;

	
	public static void main(String[] args) {
		new Playboard();
		Server server = new Server();
		// server.run();
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(8013);
			acceptSocket = serverSocket.accept();
			
			output = new PrintStream(acceptSocket.getOutputStream());
			input = new BufferedReader (new InputStreamReader(acceptSocket.getInputStream()));
			
			//While løkke som tillater kontinuerlig kommunikasjon mellom client og server
			while(acceptSocket.isConnected()) {
				
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