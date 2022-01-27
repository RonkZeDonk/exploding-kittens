import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EndScreen extends JFrame {
  public EndScreen(int winnerID) {
    super();

    boolean playerWon = (winnerID == 0);

    String title;
    if (playerWon)
      title = "Your the Winner :)";
    else
      title = "You lost :(";

    setTitle(title);
    setSize(500, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(null);

    JLabel winner;
    if (!playerWon) winner = new JLabel("The winner was player " + winnerID);
    else winner = new JLabel("Congratulations, You won!");
    winner.setBounds(-5, 55, 500, 64);
    winner.setFont(new Font(GlobalConstants.FONT_FACE, Font.BOLD, 32));
    winner.setHorizontalAlignment(SwingConstants.CENTER);
    add(winner);

  }
}
