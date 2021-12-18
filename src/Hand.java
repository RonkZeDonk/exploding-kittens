import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> handCards = new ArrayList<Card>();

    public Hand() {}

    public void add(Card card) {
        handCards.add(card);
    }
}
