package stockfish.model.processbuilderstrategy;

import java.io.BufferedReader;
import java.util.Observable;

import stockfish.model.IConfiguration;
import stockfish.model.Messagetype;

public class StockfishProcess extends Observable implements IConfiguration  {
	
	private static String processFilename = PROCESS_FILE_LOCATION_ON_THE_DESKTOP;
	private ClientThread client;
		
	public StockfishProcess() {
		client = new ClientThread(processFilename, this);
		client.start();		
	}
	
	public void update(Messagetype message) {
		setChanged();
		notifyObservers(message);
		clearChanged();		
	}

}
