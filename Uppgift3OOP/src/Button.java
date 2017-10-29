import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button {
	
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
