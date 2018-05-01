package playerOne;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerOneClient {

	public PlayerOneClient() {
		String machineName = "127.0.0.1";
		try {
			Socket socket = new Socket(machineName, 15001);
			OutputStream outps = socket.getOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(outps);
			String a = "Hei!";

			out.writeObject(a); // Hva som skal sendes til serveren
			out.flush();
		} catch (Exception e) {}
		;
	}
	
}
