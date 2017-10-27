import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener {

	private final JPanel gamePanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final JButton newGame = new JButton("New game");
	private final JButton endGame = new JButton("End game");
	
	// Testar att göra en array av int, ska sen försöka loopa igenom och sätta ut dem på framen
	JButton[][]  slideButton = new JButton [4][4];

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
		
		// GridLayout för game panelen, 4X4
		gamePanel.setLayout(new GridLayout(4,4));
		//gamePanel.setLayout(new FlowLayout());
		gamePanel.setPreferredSize(new Dimension(4*100, 4*100));  // ändrar storleken på panelen för knapparna

		// Resten av koden för framen
		setSize(600, 600);
		//pack();
		setLocation(900, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Testar array[][] loopningen:
		
		int buttonName = 1; // knappnamnet börjar på 1, ökar sen i loopen
		for (int row = 0; row < slideButton.length; row++) { // loopar först igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton();  // skapar en button
				slideButton[row][col].setText(Integer.toString(buttonName)); //Sätter ut namnet
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25));  //for the lulz
				buttonName++;  // ökar buttonName
				gamePanel.add(slideButton[row][col]); // sätter ut knapparna i panelen
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else {
			System.out.println("hmm vi får fixa något här senare :D");
		}

	}
}
