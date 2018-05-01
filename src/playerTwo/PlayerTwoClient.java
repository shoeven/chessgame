package playerTwo;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerTwoClient {

	public PlayerTwoClient() {
		String machineName = "127.0.0.1";
		try {
			Socket socket = new Socket(machineName, 15000);
			OutputStream outps = socket.getOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(outps);
			String a = "Hei!";

			out.writeObject(a); // Hva som skal sendes til serveren
			out.flush();
		} catch (Exception e) {}
		;
	}
}
	
