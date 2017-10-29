import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Button extends JButton  {
	
	protected int row;
	protected int col;
	protected String name;
	
	public Button(String name, int row, int col) {
		this.name = name;
		this.row = row;
		this.col = col;
		//this.addMouseListener(this);
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

	/*@Override
	public void mouseClicked(MouseEvent e) {
		Button mouseClick = (Button) (e.getSource());
		int r = mouseClick.getRow();
		int c = mouseClick.getCol();
		System.out.println("row: " + r + ", Col: " + c);
		int r = mouseClick.getRow() + 1;
		int c = mouseClick.getCol() + 1;
		System.out.println("row: " + r + ", Col: " + c);
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
		
	}*/
	
}
