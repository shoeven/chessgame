package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Chesspiece extends JLabel implements MouseListener{
	
	PieceMovement piecemovement;
	private ImageIcon imageicon;
	private BufferedImage img = null;
	private String piecename ="";
	private char color;
	private Container parent;
	private Chessfield cf;
	
	public Chesspiece(String p, char c) {
		piecename = p;
		color = c;
		newPiece();
	}
	
	private void newPiece() {
		setName(piecename);
		setLayout(new GridLayout(1,0));
		
		try {
			img = ImageIO.read(new File(color+piecename+".png")); // Filnavn i prosjektmappe til bilder
		} catch (IOException e) {}
		Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		imageicon = new ImageIcon(resizedImg);
		
		JLabel l = new JLabel(imageicon);
		add(l);
		
		setVisible(true);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		String currentPlayer = PieceMovement.getCurrentPlayer();
		if (PieceMovement.getYourTurn()) {
			Component a = (Component) arg0.getSource();
			if (a != null && !PieceMovement.getClicked()) {
				if ((currentPlayer=="playerOne" && color=='w') || (currentPlayer=="playerTwo" && color=='b')) {
					PieceMovement.setClicked(true);
					parent = a.getParent();
					PieceMovement.setStartParent(parent, parent.getBackground());
					parent.setBackground(Color.GRAY);
					PieceMovement.setStartpos(parent.getName());
					piecename = a.getName();
					PieceMovement.setColor(color);
					PieceMovement.setPiecename(piecename);
					PieceMovement.setStartLocation(this);
				}
			}
			else if (PieceMovement.getClicked() && parent != PieceMovement.getStartParent()) {
				try {
					PieceMovement.removePiece(getParent(), this, this.color);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if (PieceMovement.getClicked() && parent == PieceMovement.getStartParent()) {
				parent.setBackground(PieceMovement.getStartColor());
				PieceMovement.setPiecename("");
				PieceMovement.setStartLocation(null);
				PieceMovement.setClicked(false);
			}
		}
	}
	
	public String getPiecename() {
		return piecename;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
