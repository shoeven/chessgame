package chess;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Playboard extends JFrame{
	
	PieceMovement pm;
	
	public Playboard(String player) {
		PieceMovement.setCurrentPlayer(player);
		if (player == "playerOne") {
			setTitle("Spiller 1");
		}
		if (player == "playerTwo") {
			setTitle("Spiller 2");
		}
		drawFields();
		setSize(800,800);
		setVisible(true);
	}
	
	private void drawFields() {
		setLayout(new GridLayout(8,8));
		for (int x=0; x<8; x++) {
			for (int y=0;y<8; y++) {
				add(new Chessfield(this, x, y));
			}
		}
	}
}
