package chess;

public class PieceMovement {

	private static String piecename = "";
	private static String startpos = "";
	private static String endpos = "";
	private static char color;

	public static char getColor() {
		return color;
	}

	public static void setColor(char color) {
		PieceMovement.color = color;
	}

	public static String getEndpos() {
		return endpos;
	}

	public static void setEndpos(String endpos) {
		PieceMovement.endpos = endpos;
	}

	public static String getStartpos() {
		return startpos;
	}

	public static void setStartpos(String sp) {
		startpos = sp;
	}

	public static String getPiecename() {
		return piecename;
	}

	public static void setPiecename(String p) {
		piecename = p;
	}
}
