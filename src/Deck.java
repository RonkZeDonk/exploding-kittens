import java.awt.*;
import java.util.Random;

public class Deck {
    public Card cards[] = new Card[56];

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        for (int i = 0; i < 56; i++) {
            if ((i <= 3))
            {
                cards[i] = new Card("Skip", Color.blue); // 3 skip cards
            }
            else if ((i > 3) && (i <= 7))
            {
                cards[i] = new Card("Favor", Color.black); // 4 favor cards
            }
            else if ((i > 7) && (i <= 11))
            {
                cards[i] = new Card("Attack", Color.orange); // 3 attack cards
            }
            else if ((i > 11) && (i <= 15))
            {
                cards[i] = new Card("Shuffle", Color.gray); // 4 shuffle cards
            }
            else if ((i > 15) && (i <= 20))
            {
                cards[i] = new Card("Nope", Color.red); // 5 nope cards
            }
            else if ((i > 20) && (i <= 25))
            {
                // Original name: "See the future"
                cards[i] = new Card("Peek Future", Color.pink); // 5 future peek cards
            }
            else if ((i > 25) && (i <= 29))
            {
                // Original name: "Rainbow-Ralphing-Cat"
                cards[i] = new Card("Rainbow Cat", Color.white); // 4 cards
            }
            else if ((i > 29) && (i <= 35))
            {
                cards[i] = new Card("Beard Cat", Color.white); // 4 cards
            }
            else if ((i > 35) && (i <= 37))
            {
                cards[i] = new Card("Cattermelon", Color.white); // 4 cards
            }
            else if ((i > 37) && (i <= 41))
            {
                // Original name: "Hairy Potato Cat"
                cards[i] = new Card("Potato Cat", Color.white); // 4 cards
            }
            else if ((i > 41) && (i <= 45))
            {
                cards[i] = new Card("Tacocat", Color.white); // 4 cards
            }
        }
    }

    public void shuffleDeck() {
		int index;
		Card temp;
		Random random = new Random();
		for (int i = cards.length - 1; i > 0; i--)
		{
			index = random.nextInt(i + 1);
			temp = cards[index];
			cards[index] = cards[i];
			cards[i] = temp;
		}
	}
}