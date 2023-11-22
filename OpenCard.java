import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// OpenCard.java
class OpenCard extends JFrame {
    private Player player1;
    private Player player2;
    private int visibleWidth = 0;
    private int visibleHeight = 0;

    public OpenCard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // 첫 번째 플레이어의 마지막 카드를 가져옴
        Card lastCard1 = player1.getLastCard();
        // 첫 번째 플레이어의 마지막 카드 이미지를 레이블로 생성
        ImageIcon originalIcon = new ImageIcon("./images/" + lastCard1.getImage());
        ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        // Custom label that shows only a portion of the image
        JLabel label = new JLabel(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setClip(0, 0, visibleWidth, visibleHeight);
                super.paintComponent(g);
            }
        };
        label.setBounds(0, 0, 500, 500);
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
                if (visibleWidth < 500) {
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
                if (visibleHeight < 500) {
                    visibleHeight += 30;
                    label.repaint();
                }
            }
        });
        getContentPane().add(downButton);

        JButton revealButton = new JButton("한번에 공개");
        revealButton.setBounds(500,400,150,50);
        revealButton.addActionListener(e -> {
            visibleWidth = 500;
            visibleHeight = 500;
            label.repaint();
        });
        getContentPane().add(revealButton);

        setVisible(true);
    }
}