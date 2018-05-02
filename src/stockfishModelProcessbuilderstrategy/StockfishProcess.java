package stockfishModelProcessbuilderstrategy;

import java.util.Observable;

import config.IConfiguration;

public class StockfishProcess extends Observable implements IConfiguration  {
	
	private static String processFilename = PROCESS_FILE_LOCATION_ON_THE_DESKTOP;
	private ClientThread client;
		
	public StockfishProcess() throws InterruptedException {
		client = new ClientThread(processFilename, this);
		client.start();
		client.join(); // Venter på at threaden skal avslutte
	}

}
