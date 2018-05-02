package playerTwo;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import chess.PieceMovement;

public class PlayerTwoClient {
	
	PieceMovement pm;

	public PlayerTwoClient() {
		String machineName = "127.0.0.1";
		String fenstring = PieceMovement.getFenstring();
		String startpos = PieceMovement.getStartpos();
		String endpos = PieceMovement.getEndpos();
		String piecename = PieceMovement.getPiecename();
		String color = String.valueOf(PieceMovement.getColor());
		String[] outputArray = new String[] {fenstring, startpos, endpos, piecename, color}; // Lager et String[] som skal sendes til PlayerTwoServer
		try {
			Socket socket = new Socket(machineName, 15000);
			OutputStream outps = socket.getOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(outps);
			out.writeObject(outputArray); // Hva som skal sendes til serveren
			out.flush();
		} catch (Exception e) {}
		;
	}
	
}