import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.*;

public class OpenCard extends JFrame {
    private Player player;
    private Player nextPlayer;
    private Theme theme;
    private int visibleWidth = 30;
    private int visibleHeight = 30;

    public OpenCard(Player player, Player nextPlayer, Theme theme) {
        this.player = player;
        this.nextPlayer = nextPlayer;
        this.theme = theme;

        setSize(900,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel();
        JLabel themeLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 20, 400, 50);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));  // 폰트와 크기 변경
        themeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        themeLabel.setBounds(150, 60, 400, 50);
        themeLabel.setFont(new Font("Serif", Font.BOLD, 30));  // 폰트와 크기 변경
        /*
         * 현재 코드에 테마출력이  안됨.
         * 문제를 수정할 시 이 주석은 삭제할것.
         */

        Card FirstPlayerCardInfo = player.getLastCard();  
        Card SecondPlayerCardInfo = nextPlayer != null ? nextPlayer.getLastCard() : null; 

        JLabel themeCountLabel = new JLabel();
        themeCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        themeCountLabel.setBounds(150, 650, 400, 50);
        themeCountLabel.setFont(new Font("Serif", Font.BOLD, 20)); 
        themeCountLabel.setVisible(false);
        
        if (nextPlayer != null) {
            titleLabel.setText("첫번째 플레이어의 카드공개!");
            themeLabel.setText("첫번째 플레이어의 테마는 " + theme.getPlayer1Theme() + "입니다.");            
        } else {
            titleLabel.setText("두번째 플레이어의 카드공개!");
            themeLabel.setText("두번째 플레이어의 테마는 " + theme.getPlayer2Theme() + "입니다.");
        }
        getContentPane().add(titleLabel);
        getContentPane().add(themeLabel);

        Card lastCard = player.getLastCard();
        ImageIcon originalIcon = null; 

        try {
            ArrayList<String> themes = new ArrayList<>(Arrays.asList("./image/사람/", "./image/동물/", "./image/나무/"));
            for (String th : themes) {
                String imagePath = th + lastCard.getImageName();
                if (Files.exists(Paths.get(imagePath))) {
                    originalIcon = new ImageIcon(imagePath);
                    break;
                } else {
                    originalIcon = null;
                }
            }

            if (originalIcon != null) {
                ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

                JLabel label = new JLabel(scaledIcon) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setClip(0, 0, visibleWidth, visibleHeight);
                        super.paintComponent(g);
                    }
                };
                label.setBounds(150, 120, 500, 500);
                getContentPane().add(label);

                JButton leftButton = new JButton("←");
                leftButton.setBounds(600, 450, 50, 50);
                leftButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (visibleWidth > 0) {
                            visibleWidth -= 30;
                            label.repaint();
                            if (visibleWidth == 500 && visibleHeight == 500) {
                                themeCountLabel.setVisible(true);
                            }
                        }
                    }
                });
                getContentPane().add(leftButton);

                JButton rightButton = new JButton("→");
                rightButton.setBounds(700, 450, 50, 50);
                rightButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (visibleWidth < 500) {
                            visibleWidth += 30;
                            label.repaint();
                            if (visibleWidth == 500 && visibleHeight == 500) {
                                themeCountLabel.setVisible(true);
                            }
                        }
                    }
                });
                getContentPane().add(rightButton);

                JButton upButton = new JButton("↑");
                upButton.setBounds(650, 400, 50, 50);
                upButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (visibleHeight > 0) {
                            visibleHeight -= 30;
                            label.repaint();
                            if (visibleWidth == 500 && visibleHeight == 500) {
                                themeCountLabel.setVisible(true);
                            }
                        }
                    }
                });
                getContentPane().add(upButton);

                JButton downButton = new JButton("↓");
                downButton.setBounds(650, 500, 50, 50);
                downButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (visibleHeight < 500) {
                            visibleHeight += 30;
                            label.repaint();
                            if (visibleWidth == 500 && visibleHeight == 500) {
                                themeCountLabel.setVisible(true);
                            }
                        }
                    }
                });
                getContentPane().add(downButton);

                JButton revealButton = new JButton("한번에 공개");
                revealButton.setBounds(660,400,150,50);
                revealButton.addActionListener(e -> {
                    visibleWidth = 500;
                    visibleHeight = 500;
                    label.repaint();
                    themeCountLabel.setVisible(true);
                    if (nextPlayer != null) {
                        if(theme.getPlayer1Theme().equals("사람")){
                            themeCountLabel.setText("첫번째 플레이어 : " + FirstPlayerCardInfo.getPeopleCount() + "개");
                        }
                        else if(theme.getPlayer1Theme().equals("동물")){
                            themeCountLabel.setText("첫번째 플레이어 : " + FirstPlayerCardInfo.getAnimalCount() + "개");
                        }
                        else if(theme.getPlayer1Theme().equals("나무")){
                            themeCountLabel.setText("첫번째 플레이어 : " + FirstPlayerCardInfo.getTreeCount() + "개");
                        }
                    } else {
                        if(theme.getPlayer2Theme().equals("사람")){
                            themeCountLabel.setText("두번째 플레이어 : " + SecondPlayerCardInfo.getPeopleCount() + "개");
                        }
                        else if(theme.getPlayer2Theme().equals("동물")){
                            themeCountLabel.setText("두번째 플레이어 : " + SecondPlayerCardInfo.getAnimalCount() + "개");
                        }
                        else if(theme.getPlayer2Theme().equals("나무")){
                            themeCountLabel.setText("두번째 플레이어 : " + SecondPlayerCardInfo.getTreeCount() + "개");
                        }
                    }
                });
                getContentPane().add(revealButton);

                JButton nextButton = new JButton("다음 플레이어 카드 공개");
                nextButton.setBounds(660,450,200,50);
                nextButton.addActionListener(e -> {
                    if (visibleWidth == 500 && visibleHeight == 500) {
                        this.dispose();
                        if (nextPlayer != null) {
                            new OpenCard(nextPlayer, null, theme);
                        }
                    }
                });
                getContentPane().add(nextButton);
                getContentPane().add(themeCountLabel);

                setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * 방향키버튼 위치도 조정되어야한다.
 */