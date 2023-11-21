import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;  // Point 클래스를 import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private Player player1;
    private Player player2;
    private int visibleWidth = 0;  
    private int visibleHeight = 0;

    public NextFrame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        setSize(800, 800);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // 첫 번째 플레이어의 마지막 카드를 가져옴
        Card lastCard1 = player1.getLastCard();
        // 첫 번째 플레이어의 마지막 카드 이미지를 레이블로 생성
        ImageIcon imageIcon = new ImageIcon("./images/" + lastCard1.getImage());
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();

        // Custom label that shows only a portion of the image
        JLabel label = new JLabel(imageIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setClip(0, 0, visibleWidth, visibleHeight);
                super.paintComponent(g);
            }
        };
        label.setBounds(0, 0, imageWidth, imageHeight);
        getContentPane().add(label);

        // 4방향 버튼
        JButton leftButton = new JButton("←");
        leftButton.setBounds(500, 300, 50, 50);
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visibleWidth > 0) {
                    visibleWidth -= 30;
                    label.repaint();
                }
            }
        });
        getContentPane().add(leftButton);

        JButton rightButton = new JButton("→");
        rightButton.setBounds(600, 300, 50, 50);
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visibleWidth < imageWidth) {
                    visibleWidth += 30;
                    label.repaint();
                }
            }
        });
        getContentPane().add(rightButton);

        JButton upButton = new JButton("↑");
        upButton.setBounds(550, 250, 50, 50);
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visibleHeight > 0) {
                    visibleHeight -= 30;
                    label.repaint();
                }
            }
        });
        getContentPane().add(upButton);

        JButton downButton = new JButton("↓");
        downButton.setBounds(550, 300, 50, 50);
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visibleHeight < imageHeight) {
                    visibleHeight += 30;
                    label.repaint();
                }
            }
        });
        getContentPane().add(downButton);

        setVisible(true);
    }
}
