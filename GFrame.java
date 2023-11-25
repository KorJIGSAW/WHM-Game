import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// GFrame.java
public class GFrame extends JFrame {
    private Player player1;
    private Player player2;
    private List<JButton> buttons;
    private Player currentPlayer;
    private int clickCount;

    public GFrame(Theme theme) {
    List<Card> deck = new ArrayList<>();
    try {
        // 이미지 파일 이름을 읽습니다.
        List<String> lines = Files.readAllLines(Paths.get("imageNames.txt"));

        // 각 줄에 대해 Card 객체를 생성하고 deck에 추가합니다.
        for (String line : lines) {
            deck.add(new Card(line.trim(), false, 0)); // 모든 카드를 뒷면으로 설정하고, peopleCount를 0으로 설정합니다.
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
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
                        new OpenCard(player1, player2); // OpenCard 실행
                    }
                }
            });
            buttons.add(button);
            add(button);
        }
        setVisible(true);
    }
}