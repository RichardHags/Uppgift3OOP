import java.awt.BorderLayout;
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

	
	// Testar att g�ra en array av int, ska sen f�rs�ka loopa igenom och s�tta ut dem p� framen
	private JButton[][]  slideButton = new JButton [4][4];

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
		gamePanel.setPreferredSize(new Dimension(400, 400));  // �ndrar storleken p� panelen f�r knapparna
				
	}
	
	//M�ste klura lite mer p� det h�r
	
	//public void shuffleButtons() {
	//	if(slideButton != null) {
		//	setGameField();
		//	Collections.shuffle(Arrays.asList(slideButton));
	//	}
	//}

	
	public void setGameField() {
				
		int buttonName = 1; // knappnamnet b�rjar p� 1, �kar sen i loopen
		for (int row = 0; row < slideButton.length; row++) { // loopar f�rst igenom raderna
			for (int col = 0; col < slideButton.length; col++) { // sen kolumnerna
				slideButton[row][col] = new JButton();  // skapar en button
				slideButton[row][col].setText(Integer.toString(buttonName)); //S�tter ut namnet
				slideButton[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 25));  //for the lulz
				slideButton[row][col].setPreferredSize(new Dimension(100, 100));
				buttonName++;  // �kar buttonName
				gamePanel.add(slideButton[row][col]); // s�tter ut knapparna i panelen
			
			}
		}
		
			setSize(520,600);
			setMinimumSize(new Dimension(520,600));
			setLocation(700, 300);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
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
