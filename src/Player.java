import java.util.Random;

public class Player {
  private Random rand = new Random();
  public Hand hand;

  public boolean isDead = false;

  public int playerID;
  public boolean isBot = true;
  public boolean isLocalPlayer = false;

  boolean drawCard;

  public Player(int playerID) {
    this.playerID = playerID;
    if (playerID == 0) {
      isBot = false;
      isLocalPlayer = true;
    } else {
      hand = new Hand();
      System.out.println("Player " + playerID + "'s " + hand);
    }
    updateCardCount();

  }
  public Player(int playerID, String name) {
    this.playerID = playerID;
    this.isBot = false;

    updateCardCount();

    System.out.println(hand);
  }

  public void doTurn() {
    System.out.println("a");
    if (isDead) return; // can't do a turn if you're dead lol

    checkLastCard(ExplodingKittens.gameFrame.currentCard().name);

    // Check to make sure that not all cards are defensive
    boolean allCardsDefensive = true;
    drawCard = true;
    for (Card n : hand.cards) {
      if (n.getCardValue() == 1 || n.getCardValue() == 2) {
        allCardsDefensive = false;
      }
    }

    // Play Random Card if not all cards are defensive
    if (!allCardsDefensive) {
      int index = 0;

      do {
        index = rand.nextInt(0, hand.cards.size());
      } while (hand.cards.get(index).getCardValue() == 3);

      playCard(index);
    }

    if (drawCard) {
      drawCard();
    } else System.out.println("I didn't draw a card");

    // reset needs to draw
    drawCard = true;
    ExplodingKittens.doneTurn = true;

    System.out.println("Updated " + this.hand);
  }

  private void drawCard() {
    // Draw a card
    if (isDead) return;
    Card appendingCard = ExplodingKittens.deck.pickupCard();
    System.out.println("Picked up a(n) " + appendingCard.name);
    hand.add(0, appendingCard);
    updateCardCount();
    if (hand.cards.get(0).type == Card.EXPLODING_KITTEN) {
      aboutToExplodeState();
    }
  }

  private void checkLastCard(String cardName) {
    switch (cardName) {
      /* case "Nope":
        if (canPlayNope()) {
          doNopeRoutine();
        } else {
          doDoubleNopeRoutine();
        }
        break; */
      case "Attack":
        if (ExplodingKittens.gameFrame.previousCard.getText() != "Attack" && (canPlayNope() || canPlayAttack())) {
          nopeAttackRoutine();
        } else {
          doAttackRoutine();
        }
        break;
    }
  }

  // --------------------- Card Routines --------------------------
  private boolean canPlayNope() {
    int nopeIndex = hand.cards.indexOf(new Card(Card.NOPE));
    if (nopeIndex != -1) {
      System.out.println("Playing a Nope!");
      hand.cards.remove(nopeIndex); // TODO replace with play card
      return true;
    }
    return false;
  }
  private boolean canPlayAttack() {
    int attackIndex = hand.cards.indexOf(new Card(Card.ATTACK));
    if (attackIndex != -1) {
      System.out.println("Playing a attack!");
      hand.cards.remove(attackIndex); // TODO replace with play card
      return true;
    }
    return false;

  }

  private void nopeAttackRoutine() {
    // TODO impliment routine code here.
    // you can use a nope or use an attack
    // finish turn
    drawCard = false;

    // set the turn counter one back
    System.out.println("I attacked back");
    ExplodingKittens.turnNumber -= 2; // 2 since we will increment by 1 later
  }
  private void doAttackRoutine() {
    if (hand.cards.contains(ExplodingKittens.gameFrame.currentCard())) {
      // If you have an attack stack them
      System.out.println("I have a card!");
    } else {
      // Draw two cards and skip turn
      System.out.println("I was attacked");
      drawCard();
      drawCard();
      if (ExplodingKittens.gameFrame.previousCard.getText() != "Attack") {
        drawCard();
        drawCard();
      }
      drawCard = false;
    }

  }
  // private void doDoubleNopeRoutine() { //! scrapped i think
  //   // TODO impliment routine code here.
  // }
  // --------------------- End Card Routines ------------------------------

  private void playCard(int index) {
    // play card if it isn't the exploding kitten
    if (hand.cards.get(index).type != Card.EXPLODING_KITTEN) {
      System.out.println("Played " + hand.cards.get(index));
      hand.cards.get(index).playCard();

      // Do special moves
      switch (hand.cards.get(index).type) {
        case Card.SKIP:
          drawCard = false;
          break;
        case Card.ATTACK:
          drawCard = false;
          break;
        case Card.SHUFFLE:
          ExplodingKittens.deck.shuffleDeck();
          break;
        case Card.FAVOR:
          // Steal a person's card (never the player to make things simple)
          int roll;
          do {
            roll = rand.nextInt(1,4);
          } while (playerID == roll);
          Hand otherHand = ExplodingKittens.otherPlayers[roll].hand;
          ExplodingKittens.otherPlayers[roll].hand.cards.remove(rand.nextInt(0, otherHand.cards.size()));
          break;
      }

      hand.cards.remove(index);
    } else {
      // reinsert to deck and shuffle
      ExplodingKittens.gameFrame.previousCard.setText(
        ExplodingKittens.gameFrame.currentCard().name
      );
      ExplodingKittens.deck.cards.add(hand.cards.remove(index));
      ExplodingKittens.deck.shuffleDeck();
    }
  }

  private void playCard(Card card) {
    playCard(hand.cards.indexOf(card));
  }

  private void aboutToExplodeState() {
    playCard(0);
    boolean saved = false;
    for (Card n : hand.cards) {
      if (n.getCardValue() == 3 && n.type != Card.NOPE) {
        saved = true;
        ExplodingKittens.gameFrame.previousCard.setText("Exploding Kitten");
        playCard(n);
        break;
      }
    }

    if (!saved) die();
  }

  private void updateCardCount() {
    switch (playerID) {
      case 1:
        ExplodingKittens.gameFrame.player1Name.setText("Player 1 (" + getCardCount() + " Cards)");
        break;
      case 2:
        ExplodingKittens.gameFrame.player2Name.setText("Player 2 (" + getCardCount() + " Cards)");
        break;
      case 3:
        ExplodingKittens.gameFrame.player3Name.setText("Player 3 (" + getCardCount() + " Cards)");
        break;
    }
  }

  private void die() {
    System.out.println("I'm dead");

    switch (playerID) {
      case 1:
        ExplodingKittens.gameFrame.player1Name.setText("Player 1 (Dead)");
        break;
      case 2:
        ExplodingKittens.gameFrame.player2Name.setText("Player 2 (Dead)");
        break;
      case 3:
        ExplodingKittens.gameFrame.player3Name.setText("Player 3 (Dead)");
        break;
    }

    isDead = true;
  }

  public int getCardCount() {
    return hand.cards.size();
  }

  public int getTotalCardValue() {
    int total = 0;
    for (Card card : hand.cards) {
      total += card.getCardValue();
    }

    return total;
  }
}
