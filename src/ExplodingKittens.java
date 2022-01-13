public class ExplodingKittens {
  public static GameFrame gameFrame = new GameFrame();
  public static MainMenuFrame mainMenuFrame = new MainMenuFrame(gameFrame);

  public static Deck deck = new Deck();
  public static Hand hand;

  public static void main(String[] args) {

    deck.shuffleDeck();

    hand = new Hand();
    System.out.println(hand);
    Hand handp2 = new Hand();
    System.out.println(handp2);
    Hand handp3 = new Hand();
    System.out.println(handp3);
    Hand handp4 = new Hand();
    System.out.println(handp4);

    deck.addExplodingKittens(3);

    System.out.println(deck);

    gameFrame.displayHandOnFrame(hand);

    mainMenuFrame.setVisible(true);
  }
}
