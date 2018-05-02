package playerOne;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import chess.PieceMovement;
import config.ReadXMLfile;

public class PlayerOneClient {
	
	PieceMovement pm;
	ReadXMLfile rxf;

	public PlayerOneClient() {
		rxf = new ReadXMLfile();
		String machineName = ReadXMLfile.getIpPlayer2(); // IP til PlayerTwoServer
		String fenstring = PieceMovement.getFenstring();
		String startpos = PieceMovement.getStartpos();
		String endpos = PieceMovement.getEndpos();
		String piecename = PieceMovement.getPiecename();
		String color = String.valueOf(PieceMovement.getColor());
		String[] outputArray = new String[] {fenstring, startpos, endpos, piecename, color}; // Lager et String[] som skal sendes til PlayerTwoServer
		try {
			Socket socket = new Socket(machineName, 15001);
			OutputStream outps = socket.getOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(outps);
			out.writeObject(outputArray); // Hva som skal sendes til serveren
			out.flush();
		} catch (Exception e) {}
	}
	
}
