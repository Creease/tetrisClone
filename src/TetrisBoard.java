import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TetrisBoard extends JPanel {
	
	private String[] pieces = {"Line", "T", "Square", "LShape", "LShapeReverse", "ZShape", "ZShapeReverse"};
	private ArrayList<Piece> boardPieces = new ArrayList<Piece>();
	private Piece currentPiece;
	private Random pieceGenerator;
	private final int ORIGIN_X = 0;
	private final int ORIGIN_Y = 21;
	private final int PIECE_SIZE = 40;
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public TetrisBoard() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(PIECE_SIZE*10,PIECE_SIZE*22));	
		this.pieceGenerator = new Random();
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.boardPieces.add(piece);
		this.currentPiece = piece;
		
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
 
        for (Piece piece: boardPieces) {
		    for (Point p: piece.getPoints()) {
		        g2d.setColor(piece.getColor());
		        g2d.fillRect((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
		        g2d.setColor(piece.getColor().brighter());
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE);
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        g2d.setColor(piece.getColor().darker());
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        	
		        }
        }
	        
		g2d.dispose();
		
	}
	
	public void move(int xDirection, int yDirection) {
		for (Point p: currentPiece.getPoints()) {
			p.setLocation(p.getX()+xDirection, p.getY()+yDirection);
			repaint();
		}
	}
	
	public void newPiece() {
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.boardPieces.add(piece);
		this.currentPiece = piece;
		repaint();
		
	}
	
	
}