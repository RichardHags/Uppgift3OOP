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

	JButton[] slideButton = new JButton[16];

	public Frame() {

		// lägger ut 2 delpaneler, en för framen och en för knapparna
		setLayout(new BorderLayout());
		add("North", gamePanel);
		add("South", buttonPanel);

		// Knapparna för new game och avsluta spelet samt actionlisterners för dem
		buttonPanel.add(newGame);
		buttonPanel.add(endGame);
		endGame.addActionListener(this);
		newGame.addActionListener(this);

		for (int rc = 0; rc < slideButton.length; rc++) { // Loopa igenom arrayen
			slideButton[rc] = new JButton("" + (rc + 1)); // skapar en button
			slideButton[rc].setBackground(Color.ORANGE);
			slideButton[rc].setFont(new Font("Comic Sans MS", Font.PLAIN, 25)); // for the lulz
			slideButton[rc].setPreferredSize(new Dimension(100, 100));
			slideButton[rc].addActionListener(this);
		}
		// "tomma" knappen
		slideButton[15] = new JButton("");
		slideButton[15].setBackground(Color.BLACK);
		slideButton[15].setPreferredSize(new Dimension(100, 100));
		slideButton[15].addActionListener(this);

		// GridLayout för game panelen, 4x4
		gamePanel.setLayout(new GridLayout(4, 4, 4, 4));
		gamePanel.setPreferredSize(new Dimension(400, 400)); // ändrar storleken på panelen för knapparna
		for (int i = 0; i < 16; i++)
			gamePanel.add(slideButton[i]); // sätter ut knapparna i panelen

		// Resten av koden för framen
		setSize(520, 500);
		setMinimumSize(new Dimension(520, 500));
		setLocation(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// shuffle(); // shuffla spelet i början av programmet

	}

	/*Metod för shuffle, den loopar x antal gånger och "klickar" på en random knapp
	i arrayen. meningen är att den ska flytta på knapparna x antal gånger. Behöver en metod
	som flyttar på knapparna innan den fungerar som den ska (eller bara i actionEventet)*/
	public void shuffle() {
		for (int i = 0; i < 30; i++) {
			int random = (int) (Math.random() * 16);
			slideButton[random].doClick(); // klickar (från programmet) på x om (x.doClick())
		}
	}

	// Måste klura lite mer på det här

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
			shuffle();
		}

	}
}
