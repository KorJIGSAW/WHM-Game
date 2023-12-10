import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;

public class Start extends JFrame {
    private Clip clip;
    private FloatControl gainControl;

    public Start(){
        setTitle("Image Duel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Center
        ImageIcon imageIcon = new ImageIcon("./image/versus.png");
        JLabel imageLabel = new JLabel(imageIcon);
        c.add(imageLabel, BorderLayout.CENTER);

        // South
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 2 rows, 3 columns, 10 pixels gap

        JButton startButton = new JButton("게임 시작");
        JButton tutorialButton = new JButton("튜토리얼");
        JButton exitButton = new JButton("나가기");
        JButton playButton = new JButton("음악 재생");
        JButton stopButton = new JButton("음악 중지");

        //startButton ActionListener
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getPreferredSize().height / 2));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.stop();
                Theme theme = new Theme();
                new ThemeFrame(theme);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (clip != null && !clip.isRunning()){
                    clip.start();
                }
            }
        });

        stopButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (clip != null && clip.isRunning()){
                    clip.stop();
                }
            }
        });

        // Music
        playMusic("./music/GameStart.wav", -20f);

        int volumeMax = (int) gainControl.getMaximum();
        int volumeMin = (int) gainControl.getMinimum();
        int volumeInit = (int) gainControl.getValue();

        JSlider volumeSlider = new JSlider(volumeMin, volumeMax, volumeInit);
        volumeSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                if (clip != null && clip.isRunning()){
                    int volume = source.getValue();
                    if (volume >= volumeMin && volume <= volumeMax) {
                        gainControl.setValue(volume); // change volume
                    } 
                }
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(volumeSlider);

        c.add(buttonPanel, BorderLayout.SOUTH);

        setSize(1400, 900); // Set size
        setVisible(true);
    }

    private void playMusic(String filePath, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by 20 decibels.
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}