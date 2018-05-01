package playerTwo;

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

public class PlayerTwoServer {

	private ServerSocket ChessServer;
	private PieceMovement pm;

	public static void main(String[] args) {
		new Playboard("playerTwo");
		new PlayerTwoServer(args);
	}

	public PlayerTwoServer(String args[]) {
		try {
			ChessServer = new ServerSocket(15001);
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

	public ConnectionHandler(Socket isocket) {
		socket = isocket;
	}

	public void run() {
		try {
			InputStream inps = socket.getInputStream();
			OutputStream outps = socket.getOutputStream();

			ObjectOutputStream output = new ObjectOutputStream(outps);
			ObjectInputStream input = new ObjectInputStream(inps);

			String finn = (String) input.readObject();
			if (finn.equals("Hei!")) {
				String s = "Server2 sier: Spiller 1 gjorde et trekk";
				System.out.println(s);
			}
			output.writeObject(null);
			output.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

}
