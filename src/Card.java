import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Card extends JPanel {
  public static final int CARD_WIDTH = 220;
  public static final int CARD_HEIGHT = 300;

  public String type;
  public Color color;
  private boolean handCard = true;

  public Card(String type) {
    this.type = type;

    initCard();
    makeCardFrame();
  }
  public Card(String type, Color color) {
    this.type = type;
    this.color = color;

    initCard();
    makeCardFrame();
  }

  private void initCard() {
    switch (type) {
      case "Skip":
        setToolTipText("trolling");
        break;
    }
  }

  private void makeCardFrame() {
    final String FONT_FACE = "Sans Serif";

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

    JLabel title = new JLabel(this.type);
    title.setFont(new Font(FONT_FACE, Font.PLAIN, 24));
    title.setHorizontalAlignment(JLabel.CENTER);
    c.gridx = 0;
    c.gridy = 0;
    add(title, c);

    JLabel typeText = new JLabel("Details");
    typeText.setFont(new Font(FONT_FACE, Font.PLAIN, 16));
    title.setHorizontalAlignment(JLabel.CENTER);
    c.insets = new Insets(220, 0, 0, 0);
    c.gridx = 0;
    c.gridy = 1;
    add(typeText, c);

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent e) {
        if (handCard)
          setBackground(Color.lightGray);
      }

      public void mouseExited(java.awt.event.MouseEvent e) {
        if (handCard)
          setBackground(color);
      }

      public void mouseClicked(java.awt.event.MouseEvent e) {
        if (handCard)
          destory();
      }
    });
  }

  public void isHandCard(boolean value) {
    this.handCard = value;
  }

  public void destory() {
    ExplodingKittens.gameFrame.removeCardFromFrame(this);
    ExplodingKittens.hand.cards.remove(this);
  }

  public String toString() {
    return "Card: " + this.type + ", Color: " + this.color;
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
        event.call();
      }
    });
  }
}
