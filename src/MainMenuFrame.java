import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame {
  public String playerName;

  public MainMenuFrame(GameFrame gameFrame) {
    super("Exploding Kittens | Main Menu");
    setIconImage(GlobalConstants.ICON.getImage());
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(null);

    JLabel title = new JLabel("Exploding Kittens");
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setFont(new Font(GlobalConstants.FONT_FACE, Font.PLAIN, 64));
    title.setBounds(0, 50, 1000, 200);
    // title.setOpaque(true);
    // title.setBackground(Color.red);
    add(title);

    JButton normalMode_btn = new JButton("Singleplayer");
    add(normalMode_btn);
    normalMode_btn.setBounds(0, 315, 250, 250);
    normalMode_btn.setFocusable(false);
    normalMode_btn.setBackground(Color.lightGray);
    normalMode_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameFrame.changeGamemode("Singleplayer");
        gameFrame.setVisible(true);
        gameFrame.setLocation(getLocation());
        ExplodingKittens.startGame();
        setVisible(false);
      }
    });

    JButton partyMode_btn = new JButton("Party (Multiplayer)");
    add(partyMode_btn);
    partyMode_btn.setBounds(250, 315, 250, 250);
    partyMode_btn.setEnabled(false);
    partyMode_btn.setFocusable(false);
    partyMode_btn.setBackground(Color.lightGray);
    partyMode_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameFrame.changeGamemode("Party (Multiplayer)");
        gameFrame.setVisible(true);
        gameFrame.setLocation(getLocation());
        ExplodingKittens.startGame();
        setVisible(false);
      }
    });

    // Settings pannel
    JPanel userPanel = new JPanel(new GridLayout(0,1));
    add(userPanel);
    userPanel.setBounds(500, 315, 500, 250);
    userPanel.setBorder(BorderFactory.createEtchedBorder());
    // Username
    userPanel.add(new JLabel("Username:"));
    JTextField username = new JTextField("", 16);
    userPanel.add(username);
    JButton submitUsername = new JButton("Change!");
    submitUsername.setBackground(Color.lightGray);
    userPanel.add(submitUsername);
    submitUsername.addActionListener(e -> System.out.println(username.getText()));
    // Server IP
    userPanel.add(new JSeparator());
    userPanel.add(new JLabel("IP Address:"));
    JTextField ipBox = new JTextField("", 16);
    userPanel.add(ipBox);
    JButton submitIp = new JButton("Connect!");
    submitIp.setBackground(Color.lightGray);
    userPanel.add(submitIp);
    submitIp.addActionListener(e -> System.out.println(ipBox.getText()));
  }
}
