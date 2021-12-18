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
}
