import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Card extends JPanel {
  public static final int CARD_WIDTH = 220;
  public static final int CARD_HEIGHT = 300;

  public static final int DEFUSE = 0;
  public static final int SKIP = 1;
  public static final int FAVOR = 2;
  public static final int ATTACK = 3;
  public static final int SHUFFLE = 4;
  public static final int NOPE = 5;
  public static final int PEAK_FUTURE = 6;
  public static final int RAINBOW_CAT = 7;
  public static final int BEARD_CAT = 8;
  public static final int CATTERMELON = 9;
  public static final int POTATO_CAT = 10;
  public static final int TACOCAT = 11;
  public static final int EXPLODING_KITTEN = 12;

  public int type;
  public String name;
  public Color color;
  private int cardValue;
  private boolean handCard = true;
  private boolean disabled = false;

  public Card(int type) {
    this.type = type;

    initCard();
    makeCardFrame();
  }
  public Card(String name, Color color) {
    this.name = name;
    this.color = color;

    makeCardFrame();
  }

  private void initCard() {
    switch (type) {
      case DEFUSE:
        name = "Defuse";
        color = Color.green;
        cardValue = 3;
        setToolTipText("Defuse an exploding Kitten");
        break;
      case SKIP:
        name = "Skip";
        color = Color.blue;
        cardValue = 3;
        setToolTipText("Skip your turn without drawing a card");
        break;
      case FAVOR:
        name = "Favor";
        color = Color.black;
        cardValue = 2;
        setToolTipText("Another player must give the player a card from their hand");
        break;
      case ATTACK:
        name = "Attack";
        color = Color.orange;
        cardValue = 2;
        setToolTipText("End turn without playing and make next player pickup 2 cards");
        break;
      case SHUFFLE:
        name = "Shuffle";
        color = Color.gray;
        cardValue = 2;
        setToolTipText("Shuffle the deck");
        break;
      case NOPE:
        name = "Nope";
        color = Color.red;
        cardValue = 3;
        setToolTipText("Stop a player from playing their card");
        break;
      case PEAK_FUTURE:
        name = "Peak Future";
        color = Color.pink;
        cardValue = 2;
        setToolTipText("See the next 3 cards in the deck");
        break;
      case RAINBOW_CAT:
        name = "Rainbow Cat";
        color = Color.white;
        cardValue = 1;
        setToolTipText("Steal a random card if you place a pair");
        break;
      case BEARD_CAT:
        name = "Beard Cat";
        color = Color.white;
        cardValue = 1;
        setToolTipText("Steal a random card if you place a pair");
        break;
      case CATTERMELON:
        name = "Cattermelon";
        color = Color.white;
        cardValue = 1;
        setToolTipText("Steal a random card if you place a pair");
        break;
      case POTATO_CAT:
        name = "Potato Cat";
        color = Color.white;
        cardValue = 1;
        setToolTipText("Steal a random card if you place a pair");
        break;
      case TACOCAT:
        name = "Tacocat";
        color = Color.white;
        cardValue = 1;
        setToolTipText("Steal a random card if you place a pair");
        break;
      case EXPLODING_KITTEN:
        name = "Exploding Kitten";
        color = Color.black;
        cardValue = 1;
        setToolTipText("Explode");
        break;
    }
  }

  private void makeCardFrame() {
    setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
    setLayout(new GridBagLayout());
    setBackground(color);
    // Padding with border
    // (stackoverflow.com/questions/5328405/jpanel-padding-in-java (first comment in
    // answer))
    setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.darkGray, 2),
        new EmptyBorder(10, 10, 10, 10)));

    GridBagConstraints c = new GridBagConstraints();

    JLabel title = new JLabel(this.name);
    title.setFont(new Font(GlobalConstants.FONT_FACE, Font.PLAIN, 24));
    title.setHorizontalAlignment(JLabel.CENTER);
    c.gridx = 0;
    c.gridy = 0;
    add(title, c);

    JLabel typeText = new JLabel("Details");
    typeText.setFont(new Font(GlobalConstants.FONT_FACE, Font.PLAIN, 16));
    title.setHorizontalAlignment(JLabel.CENTER);
    c.insets = new Insets(220, 0, 0, 0);
    c.gridx = 0;
    c.gridy = 1;
    add(typeText, c);

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent e) {
        if (!disabled)
          setBackground(Color.lightGray);
      }

      public void mouseExited(java.awt.event.MouseEvent e) {
        if (!disabled)
          setBackground(color);
      }

      public void mouseClicked(java.awt.event.MouseEvent e) {
        if (!disabled && handCard)
        playCard();
        destory();
      }
    });
  }

  public void playCard() {
    ExplodingKittens.gameFrame.placeCardOnTable(this);
  }

  public void setHandCard(boolean value) {
    this.handCard = value;
  }

  public void setDisabled(boolean value) {
    disabled = value;
  }

  public int getCardValue() {
    return cardValue;
  }

  public void destory() {
    ExplodingKittens.gameFrame.removeCardFromFrame(this);
    ExplodingKittens.hand.cards.remove(this);
  }

  public String toString() {
    return "Card: " + this.name + ", Color: " + this.color;
  }

  interface onClickEvent {
    void call();
  }

  public void onClick(onClickEvent event) {
    addMouseListener(new MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent e) {
      }

      public void mouseExited(java.awt.event.MouseEvent e) {
      }

      public void mouseClicked(java.awt.event.MouseEvent e) {
        if (!disabled)
          event.call();
      }
    });
  }
}
