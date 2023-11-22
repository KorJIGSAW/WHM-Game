import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GFrame extends JFrame {
    private Player player1;
    private Player player2;
    private List<JButton> buttons;
    private Player currentPlayer;
    private int clickCount;

    private Theme theme;  // 추가된 멤버 변수

    public GFrame(List<Card> deck, Theme theme) {  // 수정된 생성자
        this.theme = theme;
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        currentPlayer = player1; 
        clickCount = 0;
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 30);

        setLayout(new GridLayout(3, 3));
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            int finalI = i;
            JButton button = new JButton("?");
            button.setBorder(new LineBorder(Color.WHITE)); 

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fileName = deck.get(finalI).getImage();
                    ImageIcon icon = new ImageIcon(fileName);
                    Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(image));

                    currentPlayer.drawCard(deck.get(finalI)); 

                    if (currentPlayer == player1) {
                        button.setBorder(new LineBorder(Color.BLUE, 10)); 
                        currentPlayer = player2; 
                    } else {
                        button.setBorder(new LineBorder(Color.RED, 10)); 
                        currentPlayer = player1; 
                    }
                    button.setEnabled(false); 
                    clickCount++;
                    if (clickCount == 2) { 
                        for (JButton btn : buttons) {
                            btn.setEnabled(false);
                        }
                    }
                }
            });
            buttons.add(button);
            add(button);
        }
        setVisible(true);
    }
}
