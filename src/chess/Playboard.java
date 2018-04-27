package chess;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Playboard extends JFrame{
	
	protected Chessfield[][] gamematrix;
	
	public Playboard() {
		setTitle("Sjakk");
		drawFields();
		setSize(800,800);
		setVisible(true);
	}
	
	private void drawFields() {
		gamematrix = new Chessfield[8][8];
		setLayout(new GridLayout(8,8));
		for (int x=0; x<8; x++) {
			for (int y=0;y<8; y++) {
				Chessfield cf = new Chessfield(this, x, y);
				gamematrix[x][y] = cf;
				add(new Chessfield(this, x, y));
			}
		}
	}
}
