import java.util.Random;

public class AIPlayer {
  private Random rand = new Random();
  public Hand hand = new Hand();

  public AIPlayer() {}

  public void doTurn() {
    // Temp solution: pick a random card to play lol

    hand.cards.remove(
      rand.nextInt(0, hand.cards.size())
      );
  }
}
