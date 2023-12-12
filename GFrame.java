import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class GFrame extends JFrame {
    private Player player1;
    private Player player2;
    private List<JButton> buttons;
    private Player currentPlayer;
    private int clickCount;

    private JProgressBar progressBar;
    private Thread timerThread;
    private int timeLimit = 10;  // Set your time limit

    public GFrame(List<Card> deck, Theme theme, Clip clip) {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        currentPlayer = player1;
        clickCount = 0;

        setLayout(new BorderLayout()); // Change layout to BorderLayout
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UIManager.put("ProgressBar.foreground", Color.GREEN);  // Progress bar color
        UIManager.put("ProgressBar.selectionBackground", Color.BLACK);  // Text color when not filled
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);  // Text color when filled

        progressBar = new JProgressBar();
        progressBar.setMaximum(timeLimit);
        progressBar.setStringPainted(true);
        progressBar.setString(timeLimit + " 초");

        timerThread = new TimerThread(timeLimit, progressBar, this, clip);
        timerThread.start();

        // Progress bar at the top
        progressBar.setPreferredSize(new Dimension(300, 20));  // Increase length
        progressBar.setForeground(Color.GREEN);  // Change color to green
        progressBar.setString(timeLimit + " 초");
        
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(progressBar);
        add(topPanel, BorderLayout.NORTH);

        GFrame thisFrame = this;

        // Card buttons in the center
        JPanel centerPanel = new JPanel(new GridLayout(3, 3));
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

                    timerThread.interrupt();
                    timerThread = new TimerThread(timeLimit, progressBar, thisFrame, clip);
                    timerThread.start();
                }
            });

            buttons.add(button);
            centerPanel.add(button);
        }
        add(centerPanel, BorderLayout.CENTER);

        // Next button at the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton nextButton = new JButton("다음");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OpenCardFrame(player1, player2, theme, 1); // OpenCard 실행
                timerThread.interrupt(); // Stop the timer thread when moving to the next frame
                clip.stop(); // Clip 객체 제거
                dispose();
            }
        });

        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

