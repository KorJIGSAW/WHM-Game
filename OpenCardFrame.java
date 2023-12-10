import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OpenCardFrame extends JFrame {
    private Player player;
    private Player nextPlayer;
    private Player tempPlayer;
    private Theme theme;
    private int visibleWidth = 30;
    private int visibleHeight = 30;
    private int WinnerPlayer2;
    // 이미지 최대 크기 정의
    private static final int IMAGE_MAX_WIDTH = 500;
    private static final int IMAGE_MAX_HEIGHT = 500;

    static Clip clip = null;
    static boolean isPlaying = false;

    JLabel imageLabel = null;
    JSlider volumeSlider;

    public OpenCardFrame(Player player, Player nextPlayer, Theme theme, int isPlayerTurn) {
        this.player = player;
        this.nextPlayer = nextPlayer;
        this.tempPlayer = player;
        this.theme = theme;
        this.WinnerPlayer2 = 0;

        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel();
        JLabel themeLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 20, 450, 50);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30)); // 폰트와 크기 변경
        themeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        themeLabel.setBounds(100, 60, 600, 50);
        themeLabel.setFont(new Font("Serif", Font.BOLD, 30)); // 폰트와 크기 변경

        // 변경점
        Card PlayerCardInfo = player.getLastCard();

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
        // 음악이 재생중인지 확인
        // 음악재생
        try {
            File musicPath = new File("./music/Result.wav");
            if (musicPath.exists() && !isPlaying) {
                isPlaying = true;
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("./music/Result.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -20.0f;
                gainControl.setValue(volume);

                clip.start();
            } else {
                System.out.println("음악 파일을 찾을 수 없습니다.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

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
                ImageIcon scaledIcon = new ImageIcon(
                        originalIcon.getImage().getScaledInstance(IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT,
                                Image.SCALE_DEFAULT));

                imageLabel = new JLabel(scaledIcon) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setClip(0, 0, visibleWidth, visibleHeight);
                        super.paintComponent(g);
                    }
                };
                imageLabel.setBounds(130, 130, IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
                getContentPane().add(imageLabel);

                JButton leftButton = new JButton("←");
                leftButton.setBounds(660, 550, 50, 50);
                leftButton.addActionListener(e -> makeVisible(-30, true));
                getContentPane().add(leftButton);

                JButton rightButton = new JButton("→");
                rightButton.setBounds(760, 550, 50, 50);
                rightButton.addActionListener(e -> makeVisible(30, true));
                getContentPane().add(rightButton);

                JButton upButton = new JButton("↑");
                upButton.setBounds(710, 500, 50, 50);
                upButton.addActionListener(e -> makeVisible(-30, false));
                getContentPane().add(upButton);

                JButton downButton = new JButton("↓");
                downButton.setBounds(710, 550, 50, 50);
                downButton.addActionListener(e -> makeVisible(30, false));
                getContentPane().add(downButton);

                JButton revealButton = new JButton("한번에 공개");
                revealButton.setBounds(660, 400, 150, 50);
                revealButton.addActionListener(e -> {
                    visibleWidth = IMAGE_MAX_WIDTH;
                    visibleHeight = IMAGE_MAX_HEIGHT;
                    imageLabel.repaint();

                    // Opening first player
                    if (nextPlayer != null) {
                        if (theme.getPlayer1Theme().equals("사람")) {
                            System.out.println(PlayerCardInfo.getPeopleCount());
                            Global.WinnerPlayer1 = PlayerCardInfo.getPeopleCount();
                            themeCountLabel.setText("첫번째 플레이어 : " + PlayerCardInfo.getPeopleCount() + "개");
                        } else if (theme.getPlayer1Theme().equals("동물")) {
                            System.out.println(PlayerCardInfo.getAnimalCount());
                            Global.WinnerPlayer1 = PlayerCardInfo.getAnimalCount();
                            themeCountLabel.setText("첫번째 플레이어 : " + PlayerCardInfo.getAnimalCount() + "개");
                        } else if (theme.getPlayer1Theme().equals("나무")) {
                            System.out.println(PlayerCardInfo.getTreeCount());
                            Global.WinnerPlayer1 = PlayerCardInfo.getTreeCount();
                            themeCountLabel.setText("첫번째 플레이어 : " + PlayerCardInfo.getTreeCount() + "개");
                        }
                    }
                    // Opening second player
                    else {
                        if (theme.getPlayer2Theme().equals("사람")) {
                            System.out.print(PlayerCardInfo.getPeopleCount());
                            WinnerPlayer2 = PlayerCardInfo.getPeopleCount();
                            themeCountLabel.setText("두번째 플레이어 : " + PlayerCardInfo.getPeopleCount() + "개");
                        } else if (theme.getPlayer2Theme().equals("동물")) {
                            System.out.print(PlayerCardInfo.getAnimalCount());
                            WinnerPlayer2 = PlayerCardInfo.getAnimalCount();
                            themeCountLabel.setText("두번째 플레이어 : " + PlayerCardInfo.getAnimalCount() + "개");
                        } else if (theme.getPlayer2Theme().equals("나무")) {
                            System.out.print(PlayerCardInfo.getTreeCount());
                            WinnerPlayer2 = PlayerCardInfo.getTreeCount();
                            themeCountLabel.setText("두번째 플레이어 : " + PlayerCardInfo.getTreeCount() + "개");
                        }
                    }
                    themeCountLabel.setVisible(true);
                });
                getContentPane().add(revealButton);

                // 노래 소리 조절용 슬라이더 생성
                volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
                volumeSlider.setBounds(660, 730, 200, 50);
                volumeSlider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        adjustVolume();
                    }
                });
                getContentPane().add(volumeSlider);

                // isPlayerTurn 첫 번째 플레이어의 턴인지 나타내는 변수. 1이면 first 2면 second 플레이어
                JButton nextButton;
                if (isPlayerTurn == 1) {
                    nextButton = new JButton("다음 플레이어 카드 공개");
                } else
                    nextButton = new JButton("결과 보기");

                nextButton.setBounds(660, 660, 200, 50);

                nextButton.addActionListener(e -> {
                    if (visibleWidth == 500 && visibleHeight == 500) {
                        this.dispose();

                        if (nextPlayer != null) {
                            tempPlayer = nextPlayer;
                            // 첫 번째 플레이어의 턴이 끝났으므로, isFirstPlayer를 false로 설정
                            new OpenCardFrame(nextPlayer, null, theme, 2);
                        } else {
                            // 다음 플레이어가 없을 경우 WinnerFrame을 호출한다.
                            new WinnerFrame(Global.WinnerPlayer1, WinnerPlayer2, theme);
                            clip.stop();
                        }
                    }
                });
                // 버튼의 텍스트를 isFirstPlayer 값에 따라 변경

                getContentPane().add(nextButton);
                getContentPane().add(themeCountLabel);

                setVisible(true);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }   

    // refresh the visible range of picture
    private void makeVisible(int range, boolean isWidth) {
        if (isWidth) {
            visibleWidth += range;
            visibleWidth = visibleWidth < 0 ? 0 : Math.min(visibleWidth, IMAGE_MAX_WIDTH);
        } else {
            visibleHeight += range;
            visibleHeight = visibleHeight < 0 ? 0 : Math.min(visibleHeight, IMAGE_MAX_HEIGHT);
        }
        imageLabel.repaint();
    }
    private void adjustVolume(){
        float volume = volumeSlider.getValue() / 100.0f;
        if(clip != null){
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();    
            float range = maxVolume - minVolume;
            float gain = (range * volume) + minVolume;
            gainControl.setValue(gain);
        }
    }
}