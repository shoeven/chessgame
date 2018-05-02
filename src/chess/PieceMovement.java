package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.List;

import playerOne.PlayerOneClient;
import playerTwo.PlayerTwoClient;
import stockfishModelProcessbuilderstrategy.StockfishProcess;

public class PieceMovement {

	private static String piecename = ""; // navn på brikke (feks pawn)
	private static String startpos = ""; // startposisjon for brikke
	private static String endpos = ""; // sluttposisjon for brikke
	private static char color; // farge på brikke (b eller w)
	private static String fenstring = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"; // fenstring, hentes ut fra stockfish (debugwindow)
	private static String movestring = ""; // Hele strengen som skal inn i stockfish, hentes fra ClientThread
	private static Component startpiece; // Den sjakkbrikken man først trykker på for å flytte
	private static Chessfield endpiece; // Ruten man ønsker å flytte brikken til
	private static Container startParent; // Parent til startpiece, altså det chessfield'et den tilhører
	private static Color startColor; // startfarge på sjakkfelt, for å stille tilbake etter flytting
	private static boolean clicked; // Om man har trykket på en brikke eller ikke
	private static String currentPlayer;
	private static boolean yourTurn;

	public static void movePiece() throws InterruptedException { // Beveger en brikke lokalt, sender melding til motspiller om trekk
		startpiece.getParent().remove(startpiece);
		startpiece.revalidate();
		endpiece.add(new Chesspiece(piecename, color), BorderLayout.CENTER);
		startParent.setBackground(startColor);
		endpiece.revalidate();
		endpiece.getParent().repaint();
		clicked = false;
		new StockfishProcess();
		if (currentPlayer=="playerOne") {
			new PlayerOneClient();
		}
		else if (currentPlayer=="playerTwo") {
			new PlayerTwoClient();
		}
	}
	
	public static void updateBoard() throws ClassNotFoundException, InterruptedException { // Oppdaterer brettet når serveren får info fra klient om trekk fra motspiller
		Chessfield chessfieldStart = findChessfield(startpos);
		Chessfield chessfieldEnd = findChessfield(endpos);
		chessfieldStart.removeAll();
		chessfieldStart.revalidate();
		chessfieldStart.getParent().repaint();
		chessfieldEnd.removeAll();
		chessfieldEnd.add(new Chesspiece(piecename, color), BorderLayout.CENTER);
		chessfieldEnd.revalidate();
		chessfieldEnd.getParent().repaint();
		new StockfishProcess();
		if (yourTurn) {
			yourTurn = false;
		}
		else if (!yourTurn) {
			yourTurn = true;
		}
	}
	
	private static Chessfield findChessfield(String s) {
		List<Chessfield> fieldList = Chessfield.getFieldList();
		for (Chessfield chessfield : fieldList) {
			if (s.equals(chessfield.getName())) {
				return chessfield;
			}
		}
		return null;
	}
	
	public static void removePiece(Container parentfield, Chesspiece chesspiece, char c) throws InterruptedException {
		if (color != c) { 
			endpos = parentfield.getName();
			endpiece = (Chessfield) parentfield;
			parentfield.remove(chesspiece);
			movePiece();
		}
	}
	
	public static boolean getYourTurn() {
		return yourTurn;
	}

	public static void setYourTurn(boolean yourTurn) {
		PieceMovement.yourTurn = yourTurn;
	}
	
	public static boolean getClicked() {
		return clicked;
	}

	public static void setClicked(boolean b) {
		clicked = b;
	}
	
	public static String getFenstring() {
		return fenstring;
	}

	public static void setFenstring(String s) {
		fenstring = s;
	}
	
	public static void setStartLocation(Component c) {
		startpiece = c;
	}
	
	public static Component getStartLocation() {
		return startpiece;
	}
	
	public static void setEndLocation (Chessfield cf) {
		endpiece = cf;
	}

	public static char getColor() {
		return color;
	}

	public static void setColor(char c) {
		color = c;
	}

	public static String getStartpos() {
		return startpos;
	}

	public static void setStartpos(String startpos) {
		PieceMovement.startpos = startpos;
	}

	public static String getEndpos() {
		return endpos;
	}

	public static void setEndpos(String endpos) {
		PieceMovement.endpos = endpos;
	}

	public static String getPiecename() {
		return piecename;
	}

	public static void setPiecename(String p) {
		piecename = p;
	}

	public static void setStartParent(Container parent, Color col) {
		startParent = parent;
		startColor = col;
	}
	
	public static Color getStartColor() {
		return startColor;
	}

	public static Container getStartParent() {
		return startParent;
	}

	public static void setMovestring(String s) {
		movestring = s;
	}
	
	public static String getMovestring() {
		movestring = "position fen " + fenstring + " moves " + startpos + endpos;
		return movestring;
	}

	public static void setCurrentPlayer(String s) {
		currentPlayer = s;
	}
	
	public static String getCurrentPlayer() {
		return currentPlayer;
	}
}
