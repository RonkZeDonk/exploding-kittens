import java.util.ArrayList;
import java.awt.*;

public class Hand {
  public ArrayList<Card> cards = new ArrayList<Card>();

  public Hand() {
    // Add a defuse to each hand
    add(new Card("Defuse", Color.green));

    // Start hand with 7 cards from the deck
    for (int i = 0; i < 7; i++) {
      add(ExplodingKittens.deck.cards.remove(0));
    }
  }

  public void add(Card card) {
    cards.add(card);
  }

  public void play(Card card) {
    cards.remove(card);
    ExplodingKittens.gameFrame.refreshCardsFrame();
  }

  public int handValue() {
    // Hand value based on the current hand
    // used to see which AI wins after player loses
    return 0;
  }

  public String toString() {
    String returnString = "Hand: ";

    for (int i = 0; i < cards.size(); i++) {
      if (i != 0)
        returnString += ", ";
      returnString += cards.get(i).type;
    }

    return returnString;
  }
}
