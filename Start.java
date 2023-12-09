import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import javax.swing.event.*;

public class Start extends JFrame {
    private Theme theme;
    private Clip clip;

    public Start() {
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Play background music
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("./music/GameStart.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

        GridBagConstraints c = new GridBagConstraints();
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Font titleFont = new Font("Lucida Calligraphy", Font.BOLD, 50);

        // Game title
        JLabel gameTitle = new JLabel("Image Duel");
        gameTitle.setFont(titleFont);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 0.5;
        add(gameTitle, c);

        // Create a new JPanel with BoxLayout for the music control buttons
        JPanel musicControlPanel = new JPanel();
        musicControlPanel.setLayout(new BoxLayout(musicControlPanel, BoxLayout.X_AXIS));

        // Music control buttons
        JButton playButton = new JButton("음악 재생");
        playButton.setFont(buttonFont);
        playButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, playButton.getPreferredSize().height / 2));
        musicControlPanel.add(playButton);

        musicControlPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Add space

        JButton stopButton = new JButton("음악 정지");
        stopButton.setFont(buttonFont);
        stopButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, stopButton.getPreferredSize().height / 2));
        musicControlPanel.add(stopButton);
        
        // Create a new JPanel with GridLayout for the main buttons
        JPanel mainButtonPanel = new JPanel(new GridLayout(1, 4, 10, 0));  // Set horizontal gap to 10

        // Main buttons
        JButton gameStartButton = new JButton("게임 시작");
        gameStartButton.setFont(buttonFont);
        gameStartButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, gameStartButton.getPreferredSize().height / 2));
        mainButtonPanel.add(gameStartButton);

        JButton tutorialButton = new JButton("튜토리얼");
        tutorialButton.setFont(buttonFont);
        tutorialButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, tutorialButton.getPreferredSize().height / 2));
        mainButtonPanel.add(tutorialButton);
        
        JButton exitButton = new JButton("나가기");
        exitButton.setFont(buttonFont);
        exitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, exitButton.getPreferredSize().height / 2));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainButtonPanel.add(exitButton);

        // Add the music control JPanel to the main button JPanel
        mainButtonPanel.add(musicControlPanel);

        // Add the JPanel to the GridBagLayout
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5; 
        c.gridheight = 1;
        c.weightx = 0.5;  // Decrease the weight
        c.weighty = 0.25;  // Decrease the weight
        add(mainButtonPanel, c);

        setVisible(true);
    }
}
