import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Card {
	public static final int CARD_WIDTH = 220;
	public static final int CARD_HEIGHT = 300;

	public String type;
	public Color color;
	public boolean handCard = true;

	public JPanel cardPanel = new JPanel();

	public Card(String type, Color color) {
		this.type = type;
		this.color = color;
		switch (type)
		{
			case "Skip":
			cardPanel.setToolTipText("trolling");
			break;
		}
		makeCardFrame();
	
	}

	private void makeCardFrame() {
		final String FONT_FACE = "Sans Serif";

		cardPanel.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
		cardPanel.setLayout(new GridBagLayout());
		cardPanel.setBackground(color);
		// Padding with border (stackoverflow.com/questions/5328405/jpanel-padding-in-java (first comment in answer))
		cardPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2),
				new EmptyBorder(10, 10, 10, 10)
			));

		GridBagConstraints c = new GridBagConstraints();

		JLabel title = new JLabel(this.type);
		title.setFont(new Font(FONT_FACE, Font.PLAIN, 24));
		title.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		cardPanel.add(title, c);

		JLabel typeText = new JLabel("Details");
		typeText.setFont(new Font(FONT_FACE, Font.PLAIN, 16));
		title.setHorizontalAlignment(JLabel.CENTER);
		c.insets = new Insets(220, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		cardPanel.add(typeText, c);

		cardPanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				if (handCard)
					cardPanel.setBackground(Color.lightGray);
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				if (handCard)
					cardPanel.setBackground(color);
			}

			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (handCard)
					destory();
			}
		});
	}

	public void isHandCard(boolean value) {
		this.handCard = value;
	}

	public void destory() {
		ExplodingKittens.gameFrame.removeCardFromFrame(this);
		ExplodingKittens.hand.cards.remove(this);
	}

	public String toString() {
		return "Card: " + this.type + ", Color: " + this.color;
	}
	
	interface onClickEvent {
		void call();
	}
	public void onClick(onClickEvent event) {
		cardPanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {}

			public void mouseExited(java.awt.event.MouseEvent e) {}

			public void mouseClicked(java.awt.event.MouseEvent e) {
				event.call();
			}
		});
	}
}