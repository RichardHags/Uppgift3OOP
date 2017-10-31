import javax.swing.JButton;

	/*This class isnt used, but could be for future work with this project*/

public class Button extends JButton  {
	
	protected int row;
	protected int col;
	protected String name;
	
	public Button(String name, int row, int col) {
		this.name = name;
		this.row = row;
		this.col = col;
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
}
