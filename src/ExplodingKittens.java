public class ExplodingKittens {
  public static GameFrame gameFrame = new GameFrame();
  public static MainMenuFrame mainMenuFrame = new MainMenuFrame(gameFrame);

  public static Deck deck = new Deck();
  public static Hand hand;

  public static boolean gameRunning = false;
  public static boolean doneTurn = false;
  public static int turnNumber = 0;

  public static Player[] otherPlayers = {
    new Player(0),
    new Player(1),
    new Player(2),
    new Player(3)
  };

  public static void main(String[] args) {
    hand = new Hand();
    System.out.println("Player's " + hand);

    deck.addExplodingKittens(3);

    System.out.println(deck);

    gameFrame.displayHandOnFrame(hand);

    mainMenuFrame.setVisible(true);
  }

  public static void startGame() {
    gameRunning = true;
    turnNumber = 0;

    loop();
  }

  public static void loop() {
    if (turnNumber % 4 != 0) System.out.println("\n\nPlayer: " + turnNumber % 4);

    if (turnNumber % 4 == 0) {
      // Local Player Turn
      // TODO turn on/enable the card panel
      System.out.println("\n\nMy Turn");
      System.out.println(deck);
      // TODO turn off/disable the card panel
    } else {
      // Other player turn
      doneTurn = false;
      if (!otherPlayers[turnNumber % 4].isDead) {
        otherPlayers[turnNumber % 4].doTurn();

        if (doneTurn) {
          new Thread(() -> {
            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {}
            turnNumber++;
            loop();
          }).start();
        }
      } else {
        turnNumber++;
        loop();
      }
    }
  }
}
