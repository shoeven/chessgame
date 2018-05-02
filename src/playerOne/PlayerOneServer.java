package playerOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chess.PieceMovement;
import chess.Playboard;

public class PlayerOneServer {

	private ServerSocket ChessServer;
	private PieceMovement pm;

	public static void main(String[] args) throws InterruptedException {
		new Playboard("playerOne");
		new PlayerOneServer(args);
	}

	public PlayerOneServer(String args[]) {
		try {
			ChessServer = new ServerSocket(15000);
			Socket socket;
			while ((socket = ChessServer.accept()) != null) {
				new ConnectionHandler(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ConnectionHandler extends Thread {

	private Socket socket;
	private PieceMovement pm;
	
	public ConnectionHandler(Socket isocket) {
		socket = isocket;
	}

	public void run() {
		try {
			InputStream inps = socket.getInputStream();
			ObjectInputStream input = new ObjectInputStream(inps);
			String[] inputArray = (String[]) input.readObject();
			PieceMovement.setFenstring(inputArray[0]);
			PieceMovement.setStartpos(inputArray[1]);
			PieceMovement.setEndpos(inputArray[2]);
			PieceMovement.setPiecename(inputArray[3]);
			PieceMovement.setColor(inputArray[4].charAt(0));
			PieceMovement.updateBoard();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

}

