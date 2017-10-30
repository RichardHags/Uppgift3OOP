import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {

	private int ROWS = 4;
	private int COLS = 4;

	private final JPanel gamePanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final JPanel textPanel = new JPanel();
	private final JLabel difficulty = new JLabel("Change difficulty max 4");
	private final JTextField changeDifficulty = new JTextField(5);
	private final JButton newGame = new JButton("New game");
	private final JButton endGame = new JButton("End game");
	private final JButton cheat = new JButton("Cheat");

	JButton[][] slideButton = new JButton[ROWS][COLS];
	

	public Frame() {

		// lägger ut 3 delpaneler, en för framen och en för knapparna
		setLayout(new BorderLayout());
		add("Center", gamePanel);
		add("West", textPanel);
		add("South", buttonPanel);

		// Knapparna för new game och avsluta spelet samt actionlisterners för dem
		buttonPanel.add(newGame);
		buttonPanel.add(endGame);
		buttonPanel.add(cheat);
		
		textPanel.add(difficulty);
		textPanel.add(changeDifficulty);
		
		endGame.addActionListener(this);
		newGame.addActionListener(this);
		cheat.addActionListener(this);
		changeDifficulty.addActionListener(this);

		// GridLayout för game panelen
		gamePanel.setLayout(new GridLayout(ROWS, COLS));
		gamePanel.setPreferredSize(new Dimension(400, 400));

		// Resten av koden för framen
		setSize(520, 500);
		setMinimumSize(new Dimension(520, 500));
		setLocation(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createButtons();
		shuffle(); // shuffla spelet i början av programmet
	}
	
	// skapar en metod som skapar knapparna

	private void createButtons() {
		for (int row = 0; row < slideButton.length; row++) { // loopar först igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton(); // skapar en button

				// Addar Button klassen osäker om det kommer fungera:
				slideButton[row][col] = new Button("", row, col);
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25)); // for the lulz
				slideButton[row][col].addActionListener(this);
				gamePanel.add(slideButton[row][col]); // sätter ut knapparna i panelen
			}
		}
	}

	public void setRowsCols(int r, int c) {
		ROWS = r;
		COLS = c;
	}
	public void checkWin() {
		int x = 1;
		int win = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (slideButton[i][j].getText().equals(x + "")) {
					win++;
					if (win == (ROWS * COLS) - 1) {
						JOptionPane.showMessageDialog(null, "You have won!");		
					}
				}
				x++;
			}
		}
	}

	public void setBlack(int row, int col) {
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
	public void moveButton(int r, int c) {
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
			System.out.println("Ogiltig flytt!"); // TODO ändra till något snyggare
		else {
			// Här byter knapparna namn och bakgrund med varandra
			temp.setText(slideButton[r][c].getText());
			temp.setBackground(Color.ORANGE);
			setBlack(r, c);
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
					setBlack(r, c);
				}
			}

		}
		checkWin();
		gamePanel.revalidate();
		gamePanel.repaint();
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
					slideButton[r][c].setBackground(Color.BLACK);
				if (slideButton[r][c].getBackground() == Color.BLACK) {
					slideButton[r][c].setText("");
				}

			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else if (e.getSource() == newGame) {
			shuffle();
		} else if (e.getSource() == cheat) {
			cheatAllignement();
		}else if (e.getSource() == changeDifficulty) {
			int r = Integer.parseInt(changeDifficulty.getText());
			gamePanel.removeAll();
			setRowsCols(r, r);
			createButtons();
			shuffle();
			gamePanel.revalidate();
			gamePanel.repaint();

		} else { // om man inte trycker på endGame eller newGame:
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (slideButton[r][c] == e.getSource()) {
						moveButton(r, c);
					}
				}
			}
		}
	}
}
