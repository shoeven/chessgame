package stockfishModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;



public abstract class SuperStrategy extends Thread {

	protected BufferedReader commandReader;
	protected BufferedReader processReader;
	protected OutputStreamWriter processWriter;
	
	int count = 0;
	
	public void run() {
		talkToStockfish();			
	}

	abstract public void talkToStockfish(); 
	
}
