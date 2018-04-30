package chess;

public class PieceMovement {

	private static String piecename = ""; // navn på brikke (feks pawn)
	private static String startpos = ""; // startposisjon for brikke
	private static String endpos = ""; // sluttposisjon for brikke
	private static char color; // farge på brikke (b eller w)
	private static String fenstring = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"; // fenstring, hentes ut fra stockfish (debugwindow)
	
	public static String getFenstring() {
		return fenstring;
	}

	public static void setFenstring(String s) {
		fenstring = s;
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
}
