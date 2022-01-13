import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
  public ArrayList<Card> cards = new ArrayList<Card>();

  public Deck() {
    createDeck();
  }

  // Some cards are renamed to better fit screen
  private void createDeck() {
    for (int i = 0; i < 56; i++) {
      if (i <= 3)
      {
        // 3 skip cards
        cards.add(new Card("Skip", Color.blue));
      }
      else if ((i > 3) && (i <= 7))
      {
        // 4 favor cards
        cards.add(new Card("Favor", Color.black));
      }
      else if ((i > 7) && (i <= 11))
      {
        // 3 attack cards
        cards.add(new Card("Attack", Color.orange));
      }
      else if ((i > 11) && (i <= 15))
      {
        // 4 shuffle cards
        cards.add(new Card("Shuffle", Color.gray));
      }
      else if ((i > 15) && (i <= 20))
      {
        // 5 nope cards
        cards.add(new Card("Nope", Color.red));
      }
      else if ((i > 20) && (i <= 25))
      {
        // Original name: "See the future"
        // 5 future peek cards
        cards.add(new Card("Peek Future", Color.pink));
      }
      else if ((i > 25) && (i <= 29))
      {
        // Original name: "Rainbow-Ralphing-Cat"
        // 4 cards
        cards.add(new Card("Rainbow Cat", Color.white));
      }
      else if ((i > 29) && (i <= 35))
      {
        // 4 cards
        cards.add(new Card("Beard Cat", Color.white));
      }
      else if ((i > 35) && (i <= 37))
      {
        // 4 cards
        cards.add(new Card("Cattermelon", Color.white));
      }
      else if ((i > 37) && (i <= 41))
      {
        // Original name: "Hairy Potato Cat"
        // 4 cards
        cards.add(new Card("Potato Cat", Color.white));
      }
      else if ((i > 41) && (i <= 45))
      {
        // 4 cards
        cards.add(new Card("Tacocat", Color.white));
      }
    }
  }

  public void shuffleDeck() {
    Collections.shuffle(cards);
  }

  public void addExplodingKittens(int n) {
    for (int i = 0; i < n; i++) {
      cards.add(new Card("Exploding Kitten", Color.black));
    }
    shuffleDeck();
    ExplodingKittens.gameFrame.refreshCardsFrame();
  }

  public String toString() {
    String returnString = "Deck: ";

    for (int i = 0; i < cards.size(); i++) {
      if (i != 0)
        returnString += ", ";
      returnString += cards.get(i).type;
    }

    return returnString;
  }
}
