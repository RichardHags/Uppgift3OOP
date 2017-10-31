import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener {

	private int ROWS = 4;
	private int COLS = 4;

	private final JPanel gamePanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final JPanel textPanel = new JPanel();
	private final JLabel difficulty = new JLabel("Change difficulty:");
	
	// skapar en strängarray med namnen för svårighetsgraderna
	private final String[] setDifficulty = {"Hard","Medium","Easy"};
	
	// skapar en combobox med svårighetsgraderna
	private final JComboBox<String> changeDifficulty = new JComboBox<>(setDifficulty);
	
	private final JButton newGame = new JButton("New game");
	private final JButton endGame = new JButton("End game");
	private final JButton cheat = new JButton("Cheat");

	JButton[][] slideButton = new JButton[ROWS][COLS];

	public Frame() {

		// lägger ut 3 delpaneler, spelet, difficulty och val-knappar
		setLayout(new BorderLayout());
		add("North", gamePanel);
		add("Center", textPanel);
		add("South", buttonPanel);

		// Knapparna för new game, avsluta, cheat samt actionlisteners för dem
		buttonPanel.add(newGame); newGame.addActionListener(this);
		buttonPanel.add(cheat); cheat.addActionListener(this);
		buttonPanel.add(endGame); endGame.addActionListener(this);
		
		// Panel för difficulty
		textPanel.add(difficulty);
		textPanel.add(changeDifficulty);
		changeDifficulty.addActionListener(this);
		
		// GridLayout för game panelen
		gamePanel.setLayout(new GridLayout(ROWS, COLS));
		gamePanel.setPreferredSize(new Dimension(400, 400));

		// Resten av koden för framen
		setSize(520, 510);
		setMinimumSize(new Dimension(520, 510));
		setLocation(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createButtons();
		shuffle(); // shuffla spelet i början av programmet
	}

	public void createButtons() {
		gamePanel.removeAll();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) { 
				slideButton[row][col] = new JButton(); 
				slideButton[row][col] = new Button("", row, col);
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25)); 
				slideButton[row][col].addActionListener(this);
				gamePanel.add(slideButton[row][col]);
			}
		}
		gamePanel.repaint();
	}

	private void setRowsCols(int rc) {
		ROWS = rc;
		COLS = rc;
	}

	private void checkWin() {
		int x = 1;
		int win = 0;
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++) {
				if (slideButton[i][j].getText().equals(x + "")) {
					win++;
					if (win == (ROWS * COLS) - 1)
						JOptionPane.showMessageDialog(null, "You have won! \nClick New game to play again!");
				}
				x++;
			}
	}

	private void setEmpty(int row, int col) {
		slideButton[row][col].setText("");
		slideButton[row][col].setBackground(Color.BLACK);
	}

	/*
	 * den här metoden kollar om grannarna till den tomma/svarta rutan går att
	 * flytta på. Positionen för de olika knapparna är 0,0 ~ 0,3 1,0 ~ 1,3 2,0 ~ 2,3
	 * 3,0 ~ 3,3 och en tillåten position kan aldrig vara mer än en siffra ifrån. så
	 * koden blir: [r][c] ~ [r +-1][c +-1] Man skulle även kunna använda row & col
	 * från button klassen och köra int r = x.getRow() som man sedan använder för
	 * [r][c]. Man kan även söka på .getText().length() == 0 i if statements
	 */
	private void moveButton(int r, int c) {
		Button temp = null; // kan använda JButton istället
		if (c < COLS - 1 && slideButton[r][c + 1].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r][c + 1]; // skita i att casta Button om JButton används istället
		if (c > 0 && slideButton[r][c - 1].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r][c - 1];
		if (r < ROWS - 1 && slideButton[r + 1][c].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r + 1][c];
		if (r > 0 && slideButton[r - 1][c].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r - 1][c];
		// om man trycker på en knapp som är för långt ifrån:
		if (temp == null)
			System.out.println("Ogiltig flytt!");
		else {
			// Här byter knapparna namn och bakgrund med varandra
			temp.setText(slideButton[r][c].getText());
			temp.setBackground(Color.ORANGE);
			setEmpty(r, c);
			checkWin();
		}
	}

	// Metod som fuskar fram sig en vinst i spelet
	private void cheatAllignement() {
		gamePanel.removeAll();
		int buttonName = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				slideButton[r][c] = new JButton();
				slideButton[r][c] = new Button("", r, c);
				slideButton[r][c].setText(Integer.toString(buttonName + 1));
				slideButton[r][c].setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				slideButton[r][c].setBackground(Color.ORANGE);
				slideButton[r][c].addActionListener(this);
				buttonName++;
				gamePanel.add(slideButton[r][c]);
				if (buttonName == (ROWS * COLS)) {
					setEmpty(r, c);
				}
			}

		}
		gamePanel.repaint();
		checkWin();
	}

	private void shuffle() {
		boolean[] used = new boolean[(ROWS * COLS)];

		/*
		 * genererar nya nummer för att randomiza spelsplanens knappar och använder en
		 * boolean för att avbryta loopen när värdena blivit tillsatta, sedan nollställs
		 * texten för den svarta knappen. Ser även till att värdena inte dubbleras
		 */

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				int val = (int) ((ROWS * COLS) * Math.random());

				// ser till så att det inte blir dubletter på knappsiffrorna
				while (used[val]) {
					val = (int) ((ROWS * COLS) * Math.random());
				}
				// avbryter loopen när siffrorna löpts igenom
				used[val] = true;

				if (val != 0) {
					slideButton[r][c].setText("" + val);
					slideButton[r][c].setBackground(Color.ORANGE);
				} else
					setEmpty(r,c);
				

			}

		}
	}

	private void changeDifficulty() {
		// ComboBox-variant av tidigare svårighetsgradsbyte, svårigheter att pusha upp..
		if(changeDifficulty.getSelectedIndex() == 0) {
				setRowsCols(4);
			}
		else if(changeDifficulty.getSelectedIndex() == 1) {
				setRowsCols(3);
		}
		else {
				setRowsCols(2);
		}	
			gamePanel.setLayout(new GridLayout(ROWS, COLS));
			createButtons();
			shuffle();
			gamePanel.repaint();
		}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else if (e.getSource() == newGame)
			shuffle();
		else if (e.getSource() == cheat)
			cheatAllignement();
		else if (e.getSource() == changeDifficulty)
			changeDifficulty();
		else { // om man inte trycker på någon av de andra knapparna
			for (int r = 0; r < ROWS; r++)  // TODO Gör en metod för snyggare.
				for (int c = 0; c < COLS; c++) {
					if (slideButton[r][c] == e.getSource())
						moveButton(r, c);  
				}
		}
	}
}
