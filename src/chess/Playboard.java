package chess;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Playboard extends JFrame {
	
	private PieceMovement pm;
	
	public Playboard(String player) {
		PieceMovement.setCurrentPlayer(player);
		if (player == "playerOne") {
			PieceMovement.setYourTurn(true);
			setTitle("Spiller 1 (hvit)");
			setLocation(50,150);
		}
		if (player == "playerTwo") {
			PieceMovement.setYourTurn(false);
			setTitle("Spiller 2 (sort)");
			setLocation(600,150);
		}
		drawFields();
		setSize(500,500);
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
