import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {
  public JPanel cardsPanel;
  public JScrollPane cardsScrollPane;
  private boolean cardsPanelOpen = false;

  public JPanel tablePanel;
  public JLabel previousCard;
  private Card cardPileFrame;
  private Card tableCard;

  JLabel player1Name;
  JLabel player2Name;
  JLabel player3Name;

  public String gamemode;

  public GameFrame() {
    super("Exploding Kittens");

    // Misc Settings
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    addKeyListener(this);

    // Add Icon
    setIconImage(GlobalConstants.ICON.getImage());

    // Set Size
    setSize(1000, 600);
    setResizable(false);

    createCardsSection();
    createTableSection();
  }

  private void createCardsSection() {
    cardsPanel = new JPanel();
    cardsPanel.setBackground(new Color(0x654B2A));

    cardsScrollPane = new JScrollPane(cardsPanel);
    cardsScrollPane.setBounds(0, 500, 985, 330);
    cardsScrollPane.getHorizontalScrollBar().setUnitIncrement(24);
    add(cardsScrollPane);

    // △▽ ▲▼
    JButton expandCardsPanel = new JButton("▲");
    expandCardsPanel.setBounds(470, 475, 60, 25);
    expandCardsPanel.setFocusable(false);
    expandCardsPanel.setBackground(Color.white);
    expandCardsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
    expandCardsPanel.setToolTipText("Expand/Shrink Cards Drawer");
    expandCardsPanel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!cardsPanelOpen) {
          // Open drawer
          cardsScrollPane.setLocation(0, 232);
          expandCardsPanel.setLocation(470, 207);
          expandCardsPanel.setText("▼");
          cardPileFrame.setDisabled(true);
        } else {
          // Close drawer
          cardsScrollPane.setLocation(0, 500);
          expandCardsPanel.setLocation(470, 475);
          expandCardsPanel.setText("▲");
          cardPileFrame.setDisabled(false);
        }
        cardsPanelOpen = !cardsPanelOpen;
      }
    });
    expandCardsPanel.setBounds(470, 475, 60, 25);
    add(expandCardsPanel);
  }

  public void refreshCardsFrame() {
    cardsScrollPane.invalidate();
    cardsScrollPane.validate();
    cardsScrollPane.repaint();
  }

  public void displayHandOnFrame(Hand hand) {
    this.cardsPanel.removeAll();

    hand.cards.forEach(n -> this.cardsPanel.add(n));
    refreshCardsFrame();
  }

  public void removeCardFromFrame(Card card) {
    this.cardsPanel.remove(card);
    refreshCardsFrame();
  }

  private void createTableSection() {
    tablePanel = new JPanel();
    tablePanel.setLayout(null);
    tablePanel.setBounds(0, 0, 1000, 500);
    tablePanel.setBackground(new Color(0x755831)); // set background to a wood-like colour
    add(tablePanel);

    Font boldFont = new Font(GlobalConstants.FONT_FACE, Font.BOLD, 21);
    player1Name = new JLabel("Player 1 (10 Cards)");
    player1Name.setBounds(100, 8, 200, 24);
    player1Name.setHorizontalAlignment(SwingConstants.CENTER);
    player1Name.setFont(boldFont);
    player1Name.setForeground(Color.black);
    tablePanel.add(player1Name);

    player2Name = new JLabel("Player 2 (12 Cards)");
    player2Name.setBounds(400, 8, 200, 24);
    player2Name.setHorizontalAlignment(SwingConstants.CENTER);
    player2Name.setFont(boldFont);
    player2Name.setForeground(Color.black);
    tablePanel.add(player2Name);

    player3Name = new JLabel("Player 3 (4 Cards)");
    player3Name.setBounds(700, 8, 200, 24);
    player3Name.setHorizontalAlignment(SwingConstants.CENTER);
    player3Name.setFont(boldFont);
    player3Name.setForeground(Color.black);
    tablePanel.add(player3Name);

    tableCard = new Card(Card.EXPLODING_KITTEN);
    tableCard.setHandCard(false);
    tableCard.setDisabled(true);
    tableCard.setBounds(400, 100, Card.CARD_WIDTH, Card.CARD_HEIGHT);
    tablePanel.add(tableCard);

    JLabel previousCardTitle = new JLabel("Previous Card:");
    previousCardTitle.setBounds(410, 40, 200, 24);
    previousCardTitle.setHorizontalAlignment(SwingConstants.CENTER);
    previousCardTitle.setFont(boldFont);
    previousCardTitle.setForeground(Color.black);
    tablePanel.add(previousCardTitle);

    previousCard = new JLabel("Exploding Kitten");
    previousCard.setBounds(410, 64, 200, 24);
    previousCard.setHorizontalAlignment(SwingConstants.CENTER);
    previousCard.setFont(boldFont);
    previousCard.setForeground(Color.black);
    tablePanel.add(previousCard);

    cardPileFrame = new Card("Pickup Card", Color.white);
    cardPileFrame.setHandCard(false);
    cardPileFrame.setBounds(50, 100, Card.CARD_WIDTH, Card.CARD_HEIGHT);
    cardPileFrame.onClick(() -> pickupCard());
    tablePanel.add(cardPileFrame);
  }

  public void pickupCard() {
    // Add new card from deck to hand
    // if the card is an exploding kitten show on table...
    ExplodingKittens.hand.add(0, ExplodingKittens.deck.pickupCard());
    if (ExplodingKittens.hand.cards.get(0).type == Card.EXPLODING_KITTEN && !tryToSave()) {
      endGame(false);
    } else {
      displayHandOnFrame(ExplodingKittens.hand);
      ExplodingKittens.turnNumber++;
      ExplodingKittens.loop();
    }
  }

  public void placeCardOnTable(Card card) {
    tablePanel.remove(tableCard);
    tableCard = card;
    tableCard.setHandCard(false);
    tableCard.setDisabled(true);

    tableCard.setHandCard(false);
    tableCard.setBounds(400, 100, Card.CARD_WIDTH, Card.CARD_HEIGHT);
    tableCard.setBackground(tableCard.color);
    tablePanel.add(tableCard);

    tableCard.invalidate();
    tableCard.validate();
    tableCard.repaint();
  }

  public Card currentCard() {
    return tableCard;
  }

  public void endGame(boolean won) {
    System.out.println("Game Ended");
    int winnerID = countOtherCards();
    System.out.println("The winner is Player " + winnerID);
    new EndScreen(winnerID).setVisible(true);
    dispose();
  }

  public boolean tryToSave() {
    // return true if you saved yourself
    System.out.println("trying to save myself");
    return false;
  }

  private int countOtherCards() {
    int highest = 0;
    int highestID = 0;
    for (Player player : ExplodingKittens.otherPlayers) {
      if (player.playerID != 0 && player.getTotalCardValue() > highest) {
        highest = player.getTotalCardValue();
        highestID = player.playerID;
      }
    }
    return highestID; // returns id of winner
  }

  public void changeGamemode(String gamemode) {
    this.gamemode = gamemode;
    this.setTitle("Exploding Kittens | In-Game | " + this.gamemode);
  }

  public void keyReleased(KeyEvent e) {
    // temp testing stuff (remove the first card)
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {

      ExplodingKittens.otherPlayers[0].doTurn();

      refreshCardsFrame();
    }
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {
  }
}
