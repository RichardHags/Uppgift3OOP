import java.awt.BorderLayout;
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
	
	public Frame() {
		
		// panelLayout etc
		
		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		setSize(600,600);
		setLocation(900,400);
		
		buttonPanel.add(newGame); buttonPanel.add(endGame);
		endGame.addActionListener(this);
		newGame.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == endGame)
			System.exit(0);
		else {
			System.out.println("hmm vi får fixa något här senare :D");
		}
		
	}
}
