package stockfish.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.lang3.StringUtils;

import chess.PieceMovement;
import stockfish.model.Messagetype;

public class DebugWindow extends JFrame implements Observer {
	
	private PieceMovement piecemovement;
	private static final int W = 500;
	private JTextPane p = new JTextPane();
	private int messagecount;
	static int count = 0;
	private StyleToggler styleToggler = new StyleToggler(); 
	
	public DebugWindow(String title) {
		setTitle(title);
		add(new JScrollPane(p));
		setLocation(200 + (++count * W), 40);
		setSize(W,400);
		setVisible(true);
		p.setContentType("HTML/plain");
	}

	public void write(String s) {
		Document doc = p.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), "\n" + s, styleToggler.next());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Messagetype m = (Messagetype) arg;
		write(" #" + ++messagecount + " " + m.getType());
		write(m.getMsg() + "\n --------------");
		if (m.getMsg().contains("Fen")) {
			String fenString= StringUtils.substringBetween(m.getMsg(),"Fen: ", "Key");
			System.out.println(fenString);
			PieceMovement.setFenstring(fenString);
		}
	}
	
}
