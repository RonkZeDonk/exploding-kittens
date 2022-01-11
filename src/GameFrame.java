import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {
	public JPanel cardsPanel;
	public JScrollPane cardsScrollPane;

	public JPanel tablePanel;

	public String gamemode;

	public GameFrame() {
		super("Exploding Kittens");
		ImageIcon icon = new ImageIcon("img/icon.png");
		setIconImage(icon.getImage());
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addKeyListener(this);
		setResizable(false);

		createCardsSection();
		createTableSection();
	}

	private void createCardsSection() {
		cardsPanel = new JPanel();
		cardsPanel.setBackground(new Color(0x654B2A));
		
		cardsScrollPane = new JScrollPane(cardsPanel);
		cardsScrollPane.setBounds(0, 450, 985, 330);
		cardsScrollPane.getHorizontalScrollBar().setUnitIncrement(24);
		add(cardsScrollPane);
	}
	
	public void refreshCardsFrame() {
		cardsScrollPane.invalidate();
		cardsScrollPane.validate();
		cardsScrollPane.repaint();
	}
	
	public void displayHandOnFrame(Hand hand) {
		this.cardsPanel.removeAll();
		
		hand.cards.forEach(n -> this.cardsPanel.add(n.cardPanel));
	}
	
	public void removeCardFromFrame(Card card) {
		this.cardsPanel.remove(card.cardPanel);
		refreshCardsFrame();
	}
	
	private void createTableSection() {
		tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBounds(0, 0, 1000, 500);
		tablePanel.setBackground(new Color(0x755831)); // set background to a wood-like colour
		add(tablePanel);

		Font playerNameFont = new Font("Sans Serif", Font.BOLD, 21);
		JLabel player1Name = new JLabel("Player 1 (10 Cards)");
		player1Name.setBounds(100, 8, 200, 24);
		player1Name.setHorizontalAlignment(SwingConstants.CENTER);
		player1Name.setFont(playerNameFont);
		player1Name.setForeground(Color.black);
		tablePanel.add(player1Name);
		JLabel player2Name = new JLabel("Player 2 (12 Cards)");
		player2Name.setBounds(400, 8, 200, 24);
		player2Name.setHorizontalAlignment(SwingConstants.CENTER);
		player2Name.setFont(playerNameFont);
		player2Name.setForeground(Color.black);
		tablePanel.add(player2Name);
		JLabel player3Name = new JLabel("Player 3 (4 Cards)");
		player3Name.setBounds(700, 8, 200, 24);
		player3Name.setHorizontalAlignment(SwingConstants.CENTER);
		player3Name.setFont(playerNameFont);
		player3Name.setForeground(Color.black);
		tablePanel.add(player3Name);

		Card tableCard = new Card("Exploding Kitten", Color.black);
		tableCard.isHandCard(false);
		tableCard.cardPanel.setBounds(50, 100, Card.CARD_WIDTH, Card.CARD_HEIGHT);
		tablePanel.add(tableCard.cardPanel);

		Card cardPileFrame = new Card("Pickup Card", Color.white);
		cardPileFrame.cardPanel.setBounds(400, 100, Card.CARD_WIDTH, Card.CARD_HEIGHT);
		cardPileFrame.onClick(() -> {
			// Add new card from deck to hand
			// if the card is an exploding kitten show on table...
			System.out.println("Hey");
		});
		tablePanel.add(cardPileFrame.cardPanel);
	}

	public void changeGamemode(String gamemode) {
		this.gamemode = gamemode;
		this.setTitle("Exploding Kittens | In-Game | " + this.gamemode);
	}

	public void keyReleased(KeyEvent e) {
		// temp testing stuff (remove the first card)
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// cardsPanel.add(new Card("Insert", Color.orange).cardPanel, 0);
			System.out.println("Removed a " + ExplodingKittens.deck.cards.remove(0).type);
			displayHandOnFrame(ExplodingKittens.hand);

			refreshCardsFrame();
		}
	}

	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}