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
	
	// Testar att g�ra en array av int, ska sen f�rs�ka loopa igenom och s�tta ut dem p� framen
	JButton[][]  slideButton = new JButton [4][4];

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
		
		// GridLayout f�r game panelen, 4X4
		gamePanel.setLayout(new GridLayout(4,4));
		//gamePanel.setLayout(new FlowLayout());
		gamePanel.setPreferredSize(new Dimension(4*100, 4*100));  // �ndrar storleken p� panelen f�r knapparna

		// Resten av koden f�r framen
		setSize(600, 600);
		//pack();
		setLocation(900, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Testar array[][] loopningen:
		
		int buttonName = 1; // knappnamnet b�rjar p� 1, �kar sen i loopen
		for (int row = 0; row < slideButton.length; row++) { // loopar f�rst igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton();  // skapar en button
				slideButton[row][col].setText(Integer.toString(buttonName)); //S�tter ut namnet
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25));  //for the lulz
				buttonName++;  // �kar buttonName
				gamePanel.add(slideButton[row][col]); // s�tter ut knapparna i panelen
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endGame)
			System.exit(0);
		else {
			System.out.println("hmm vi f�r fixa n�got h�r senare :D");
		}

	}
}
