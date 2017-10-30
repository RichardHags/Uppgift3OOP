import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener {

	private final JPanel gamePanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final JButton newGame = new JButton("New game");
	private final JButton endGame = new JButton("End game");

	JButton[][] slideButton = new JButton[4][4];

	public Frame() {

		// l�gger ut 2 delpaneler, en f�r framen och en f�r knapparna
		setLayout(new BorderLayout());
		add("North", gamePanel);
		add("South", buttonPanel);

		// Knapparna f�r new game och avsluta spelet samt actionlisterners f�r dem
		buttonPanel.add(newGame);
		buttonPanel.add(endGame);
		endGame.addActionListener(this);
		newGame.addActionListener(this);

		// GridLayout f�r game panelen, 4x4
		gamePanel.setLayout(new GridLayout(4, 4));
		gamePanel.setPreferredSize(new Dimension(400, 400));
		int buttonName = 1; // knappnamnet b�rjar p� 1, �kar sen i loopen
		for (int row = 0; row < slideButton.length; row++) { // loopar f�rst igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton(); // skapar en button
				// Addar Button klassen os�ker om det kommer fungera:
				slideButton[row][col] = new Button(Integer.toString(buttonName), row, col);

				slideButton[row][col].setBackground(Color.ORANGE);
				slideButton[row][col].setText(Integer.toString(buttonName)); // S�tter ut namnet
				if (buttonName == 16) { // Sista platsen blir tom och svart
					setBlack(row, col);
				}
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25)); // for the lulz
				// slideButton[row][col].setPreferredSize(new Dimension(100, 100)); //Beh�vs
				// inte?
				slideButton[row][col].addActionListener(this);
				buttonName++; // �kar buttonName
				gamePanel.add(slideButton[row][col]); // s�tter ut knapparna i panelen

			}
		}

		// Testar button klassen TA BORT SEN
		Button[][] buttonTest = new Button[4][4];
		int buttonName2 = 1;
		for (int row = 0; row < buttonTest.length; row++) {
			for (int col = 0; col < buttonTest.length; col++) {
				buttonTest[row][col] = new Button(Integer.toString(buttonName2), row, col);
				buttonName2++;
			}
		}

		// Kollar positionen TA BORT DETTA SEN
		int nr = 1;
		for (int row = 0; row < buttonTest.length; row++) {
			for (int col = 0; col < buttonTest.length; col++) {
				System.out.println("knapp: " + nr);
				System.out.println(row + 1);
				System.out.println(col + 1);
				nr++;
			}
		}

		// Resten av koden f�r framen
		setSize(520, 500);
		setMinimumSize(new Dimension(520, 500));
		setLocation(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		shuffle(); // shuffla spelet i b�rjan av programmet

	}

	public void setBlack(int row, int col) {
		slideButton[row][col].setText("");
		slideButton[row][col].setBackground(Color.BLACK);
	}

	/*
	 * den h�r metoden kollar om grannarna till den tomma/svarta rutan g�r att
	 * flytta p�. Positionen f�r de olika knapparna �r 0,0 ~ 0,3 1,0 ~ 1,3 2,0 ~ 2,3
	 * 3,0 ~ 3,3 och en till�ten position kan aldrig vara mer �n en siffra ifr�n. s�
	 * koden blir: [r][c] ~ [r +-1][c +-1] Man skulle �ven kunna anv�nda row & col
	 * fr�n button klassen och k�ra int r = x.getRow() som man sedan anv�nder f�r
	 * [r][c]. Man kan �ven s�ka p� .getText().length() == 0 i if statements
	 */
	public void moveButton(int r, int c) {
		Button temp = null; // kan anv�nda JButton ist�llet
		if (c < 3 && slideButton[r][c + 1].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r][c + 1]; // skita i att casta Button om JButton anv�nds ist�llet
		if (c > 0 && slideButton[r][c - 1].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r][c - 1];
		if (r < 3 && slideButton[r + 1][c].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r + 1][c];
		if (r > 0 && slideButton[r - 1][c].getBackground() == Color.BLACK)
			temp = (Button) slideButton[r - 1][c];
		// om man trycker p� en knapp som �r f�r l�ngt ifr�n:
		if (temp == null)
			System.out.println("fel knapp"); // TODO �ndra till n�got snyggare
		else {
			// H�r byter knapparna namn och bakgrund med varandra
			temp.setText(slideButton[r][c].getText());
			temp.setBackground(Color.ORANGE);
			setBlack(r, c);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else if (e.getSource() == newGame) {
			shuffle();

		} else { // om man inte trycker p� endGame eller newGame:
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if (slideButton[r][c] == e.getSource()) {
						moveButton(r, c);
					}
				}
			}
		}

	}

	private void shuffle() {
		boolean[] used = new boolean[16];

		// genererar nya nummer f�r att randomiza spelsplanens knappar och anv�nder en
		// boolean
		// f�r att avbryta loopen n�r v�rdena blivit tillsatta, sedan nollst�lls texten
		// f�r den svarta knappen.

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				int val = (int) (16 * Math.random());

				while (used[val]) {
					val = (int) (16 * Math.random());
				}

				used[val] = true;

				if (val != 0) {
					slideButton[r][c].setText("" + val);
					slideButton[r][c].setBackground(Color.ORANGE);
				} else
					slideButton[r][c].setBackground(Color.BLACK);
				if (slideButton[r][c].getBackground() == Color.black) {
					slideButton[r][c].setText("");
				}

			}

		}
	}

}
