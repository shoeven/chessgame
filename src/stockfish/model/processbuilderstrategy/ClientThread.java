package stockfish.model.processbuilderstrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.StringUtils;

import chess.PieceMovement;
import stockfish.model.Messagetype;
import stockfish.model.SuperStrategy;

public class ClientThread  extends SuperStrategy  {

	private StockfishProcess stockfishProcess;
	private PieceMovement pm;

	public ClientThread(String processFilename, StockfishProcess stockfishProcess) {
		this.stockfishProcess = stockfishProcess;
		ProcessBuilder pb = new ProcessBuilder(processFilename);
		try {
			Process  process = pb.start();
			processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			processWriter = new OutputStreamWriter(process.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void talkToStockfish() {
		try {
			String fenstring = PieceMovement.getFenstring();
			String startpos = PieceMovement.getStartpos();
			String endpos = PieceMovement.getEndpos();
			String stringcommand = "position fen " + fenstring + " moves " + startpos + endpos;
			// stockfish gets command
			processWriter.write(stringcommand + "\n"); // Skriver commandstring til stockfish
			processWriter.flush();
			processWriter.write("d" + "\n"); // For å hente ut fen-streng
			processWriter.flush();
			processWriter.write("isready\n");
			processWriter.flush();
			
			// stockfish answers
			String l = "";
			String result = "";
			while (!(l = processReader.readLine()).equals("readyok")) {
				result += l + "\n";
				System.out.println(result);
			}
			if (result.contains("Fen")) { // Leter etter fen-streng i resultatet.
				String newFen= StringUtils.substringBetween(result,"Fen: ", "Key").trim();
				PieceMovement.setFenstring(newFen);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
