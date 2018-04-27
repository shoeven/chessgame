package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Chessfield extends JPanel implements MouseListener{
	
	private boolean occupied = false;
	PieceMovement piecemovement = new PieceMovement();

	public Chessfield(Playboard playboard, int x, int y) {
		int a = x+1;//Så navnet skal gå fra 1-8 istedet for 0-7
		int b = x+1;
		setName(a+""+b); 
		setLayout(new BorderLayout());
		setSize(100,100);
		setBackground(calcColor(x, y)); // Annenhver farge sort og hvit på brett
		
		// Legger til alle sjakkbrikkene på starten av spillet.
		if(spawnWPawn(x,y)) {
			add(new Chesspiece("pawn", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnWRook(x,y)) {
			add(new Chesspiece("rook", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnWKnight(x,y)) {
			add(new Chesspiece("knight", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnWBishop(x,y)) {
			add(new Chesspiece("bishop", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnWQueen(x,y)) {
			add(new Chesspiece("queen", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnWKing(x,y)) {
			add(new Chesspiece("king", 'w'), BorderLayout.CENTER);
			occupied = true;
		}
		if(spawnBPawn(x,y)) {
			add(new Chesspiece("pawn", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnBRook(x,y)) {
			add(new Chesspiece("rook", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnBKnight(x,y)) {
			add(new Chesspiece("knight", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnBBishop(x,y)) {
			add(new Chesspiece("bishop", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnBQueen(x,y)) {
			add(new Chesspiece("queen", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		if (spawnBKing(x,y)) {
			add(new Chesspiece("king", 'b'), BorderLayout.CENTER);
			occupied = true;
		}
		
		setVisible(true);
		addMouseListener(this);
	}
	
	private Color calcColor(int x, int y) {
		boolean everyOtherSquare = ((y+1) % 2 == 0);
		boolean everyOtherRow = ((x+1) % 2 == 0);
		return (everyOtherSquare != everyOtherRow ? Color.WHITE : Color.BLACK);
	}
	
	
	
	private boolean spawnWRook(int x, int y) { // hvite tårn
		if (x==7) {
			if (y==7 || y ==0) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnWKnight(int x, int y) { // hvite hester
		if (x==7) {
			if (y==6 || y ==1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnWBishop(int x, int y) { // hvite springere
		if (x==7) {
			if (y==5 || y ==2) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnWQueen(int x, int y) { // hvit dronning 
		if (x==7 && y == 4) {
				return true;
		}
		else {
			return false;
		}
	}
	
	private boolean spawnWKing(int x, int y) { // hvit dronning 
		if (x==7 && y == 3) {
				return true;
		}
		else {
			return false;
		}
	}
	
	private boolean spawnWPawn(int x, int y) { // hvite bønder
		if (x==6) {
			for (y=0; y<=7;) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	private boolean spawnBRook(int x, int y) { // hvite tårn
		if (x==0) {
			if (y==7 || y ==0) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnBKnight(int x, int y) { // hvite hester
		if (x==0) {
			if (y==6 || y ==1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnBBishop(int x, int y) { // hvite springere
		if (x==0) {
			if (y==5 || y ==2) {
				return true;
			}
		}
		return false;
	}
	
	private boolean spawnBQueen(int x, int y) { // hvit dronning 
		if (x==0 && y == 4) {
				return true;
		}
		else {
			return false;
		}
	}
	
	private boolean spawnBKing(int x, int y) { // hvit dronning 
		if (x==0 && y == 3) {
				return true;
		}
		else {
			return false;
		}
	}
	
	private boolean spawnBPawn(int x, int y) { // sorte bønder
		if (x==1) {
			for (y=0; y<=7;) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	public void setOccupied(boolean a) {
		occupied = a;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) { 
		
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
		String piecename = PieceMovement.getPiecename();
		char color = PieceMovement.getColor();
		if (occupied == false && piecename != "") {
			color = PieceMovement.getColor();
			add(new Chesspiece(piecename, color), BorderLayout.CENTER);
			occupied = true;
			PieceMovement.setPiecename("");
			this.getName();
			PieceMovement.setEndpos(this.getName());
			revalidate();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
