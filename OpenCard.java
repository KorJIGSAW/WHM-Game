import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OpenCard extends JFrame {
    private Player player1;
    private Player player2;
    private int visibleWidth = 0;
    private int visibleHeight = 0;

    public OpenCard(Player player1, Player player2, Theme theme) {
        this.player1 = player1;
        this.player2 = player2;

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        /*
         * 여기도 해결해야할 문제가 있다. 
         * Player1과 player2간의 테마 차이점으로 인해 서로 다른 이미지 폴더에서 사진을 가져와야한다.
         * 수정하면 이 주석은 삭제할것.
         */
        Card lastCard1 = player1.getLastCard();
        ImageIcon originalIcon = null; // originalIcon을 null로 초기화
        if(theme.getPlayer1Theme() == "사람"){
            originalIcon = new ImageIcon("./image/사람/" + lastCard1.getImage());
        }
        else if(theme.getPlayer1Theme() == "동물"){
            originalIcon = new ImageIcon("./image/동물/" + lastCard1.getImage());
        }
        else{
            originalIcon = new ImageIcon("./image/풍경/" + lastCard1.getImage());
        }
       
        ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        JLabel label = new JLabel(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setClip(0, 0, visibleWidth, visibleHeight);
                super.paintComponent(g);
            }
        };
        label.setBounds(0, 0, 500, 500);
        getContentPane().add(label);

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
