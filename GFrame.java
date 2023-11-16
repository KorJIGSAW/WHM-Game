import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GFrame extends JFrame {
    private Player player1;
    private Player player2;
    private List<JButton> buttons;
    private Player currentPlayer;
    private int clickCount;

    public GFrame(List<Card> deck) {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        currentPlayer = player1; 
        clickCount = 0;
        Font font1 =  new Font("Showcard Gothic", Font.BOLD, 30);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttons = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            JButton button = new JButton("?"); 
            button.setBorder(new LineBorder(Color.WHITE)); 
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fileName = deck.get(finalI).getImage();
                    String baseName = fileName.substring(0, fileName.lastIndexOf('.')); 
                    button.setFont(font1);
                    button.setText(baseName); 
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
            buttonPanel.add(button);
        }

        // 다음 버튼 추가
        JPanel nextButtonPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("다음");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NextFrame(player1, player2);
                dispose();
            }
        });
        nextButtonPanel.add(nextButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
        add(nextButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class NextFrame extends JFrame {
    public NextFrame(Player player1, Player player2) {
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // player1의 마지막 카드를 가져옴
        Card lastCard1 = player1.getLastCard();
        // player1의 마지막 카드 이미지를 레이블로 생성
        JLabel label1 = new JLabel(new ImageIcon("./images/" + lastCard1.getImage()));
        add(label1);

        // player2의 마지막 카드를 가져옴
        Card lastCard2 = player2.getLastCard();
        // player2의 마지막 카드 이미지를 레이블로 생성
        JLabel label2 = new JLabel(new ImageIcon("./images/" + lastCard2.getImage()));
        add(label2);

        setVisible(true);
    }
}

