import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

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
		gamePanel.setLayout(new GridLayout(4, 4, 4, 4));
		gamePanel.setPreferredSize(new Dimension(400, 400));
		int buttonName = 1; // knappnamnet b�rjar p� 1, �kar sen i loopen
		for (int row = 0; row < slideButton.length; row++) { // loopar f�rst igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton(); // skapar en button
				slideButton[row][col].setText(Integer.toString(buttonName)); // S�tter ut namnet
				slideButton[row][col].setBackground(Color.ORANGE);
				if (buttonName == 16) { // Sista platsen blir tom och svart
					slideButton[row][col].setText("");
					slideButton[row][col].setBackground(Color.BLACK);
				}
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25)); // for the lulz
				slideButton[row][col].setPreferredSize(new Dimension(100, 100));
				buttonName++; // �kar buttonName
				gamePanel.add(slideButton[row][col]); // s�tter ut knapparna i panelen
			}
		}
		
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
				System.out.println(row);
				System.out.println(col);
				nr++;
				}
		}
		

		// Resten av koden f�r framen
		setSize(520, 500);
		setMinimumSize(new Dimension(520, 500));
		setLocation(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// shuffle(); // shuffla spelet i b�rjan av programmet

	}

	// M�ste klura lite mer p� det h�r

	// public void shuffleButtons() {
	// if(slideButton != null) {
	// setGameField();
	// Collections.shuffle(Arrays.asList(slideButton));
	// }
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else if (e.getSource() == newGame) {
			//shuffle();
		}

	}
}
