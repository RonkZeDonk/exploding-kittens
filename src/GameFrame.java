import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {
	public JPanel cardsPanel;
	public JScrollPane cardsScrollPane;

	public String gamemode;
	
	public GameFrame() {
		super("Exploding Kittens");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addKeyListener(this);
		setResizable(false);

		cardsPanel = new JPanel();

		cardsScrollPane = new JScrollPane(cardsPanel);
		cardsScrollPane.setBounds(0, 0, 1000, 330);
		cardsScrollPane.getHorizontalScrollBar().setUnitIncrement(24);
		add(cardsScrollPane);
	}

	public void changeGamemode(String gamemode) {
		this.gamemode = gamemode;
		this.setTitle("Exploding Kittens | " + this.gamemode);
	}

	public void displayCardsOnFrame(ArrayList<Card> deck) {
		this.cardsPanel.removeAll();

		deck.forEach(n -> this.cardsPanel.add(n.cardPanel));
	}
	
	public void keyReleased(KeyEvent e) {
		// temp testing stuff
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// cardsPanel.add(new Card("Insert", Color.orange).cardPanel, 0);
			System.out.println("Removed a " + ExplodingKittens.deck.cards.remove(0).type);
			displayCardsOnFrame(ExplodingKittens.deck.cards);
			
			// Repaint cards
			cardsScrollPane.invalidate();
			cardsScrollPane.validate();
			cardsScrollPane.repaint();
		}
	}

	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}