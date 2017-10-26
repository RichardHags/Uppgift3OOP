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
		
		buttonPanel.add(newGame); buttonPanel.add(endGame);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
