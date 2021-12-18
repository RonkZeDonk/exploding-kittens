public class ExplodingKittens {
	public static GameFrame gameFrame = new GameFrame();
	public static MainMenuFrame mainMenuFrame = new MainMenuFrame(gameFrame);

	public static Deck deck = new Deck();
	public static Hand hand = new Hand();

	public static void main(String[] args) {

		deck.shuffleDeck();
		gameFrame.displayCardsOnFrame(hand);

		mainMenuFrame.setVisible(true);
	}
}