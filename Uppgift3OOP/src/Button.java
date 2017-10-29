import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Button extends JButton implements MouseListener {
	
	protected int row;
	protected int col;
	protected String name;
	
	public Button(String name, int row, int col) {
		this.name = name;
		this.row = row;
		this.col = col;
		this.addMouseListener(this);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Button mouseClick = (Button) (e.getSource());
		int r = mouseClick.getRow() + 1;
		int c = mouseClick.getCol() + 1;
		int r2 = e.getX();
		int c2 = e.getY();
		System.out.println("row: " + r + ", Col: " + c);
		System.out.println("row: " + r2 + ", Col: " + c2);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
