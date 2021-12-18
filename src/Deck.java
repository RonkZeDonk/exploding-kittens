import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // public Card cards[] = new Card[56];
    public ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        for (int i = 0; i < 56; i++) {
            if ((i <= 3))
            {
                deck.add(new Card("Skip", Color.blue)); // 3 skip cards
            }
            else if ((i > 3) && (i <= 7))
            {
                deck.add(new Card("Favor", Color.black)); // 4 favor cards
            }
            else if ((i > 7) && (i <= 11))
            {
                deck.add(new Card("Attack", Color.orange)); // 3 attack cards
            }
            else if ((i > 11) && (i <= 15))
            {
                deck.add(new Card("Shuffle", Color.gray)); // 4 shuffle cards
            }
            else if ((i > 15) && (i <= 20))
            {
                deck.add(new Card("Nope", Color.red)); // 5 nope cards
            }
            else if ((i > 20) && (i <= 25))
            {
                // Original name: "See the future"
                deck.add(new Card("Peek Future", Color.pink)); // 5 future peek cards
            }
            else if ((i > 25) && (i <= 29))
            {
                // Original name: "Rainbow-Ralphing-Cat"
                deck.add(new Card("Rainbow Cat", Color.white)); // 4 cards
            }
            else if ((i > 29) && (i <= 35))
            {
                deck.add(new Card("Beard Cat", Color.white)); // 4 cards
            }
            else if ((i > 35) && (i <= 37))
            {
                deck.add(new Card("Cattermelon", Color.white)); // 4 cards
            }
            else if ((i > 37) && (i <= 41))
            {
                // Original name: "Hairy Potato Cat"
                deck.add(new Card("Potato Cat", Color.white)); // 4 cards
            }
            else if ((i > 41) && (i <= 45))
            {
                deck.add(new Card("Tacocat", Color.white)); // 4 cards
            }
        }
    }

    public void shuffleDeck() {
		Collections.shuffle(deck);
	}
}