import javax.swing.*;
import java.awt.*;

public class Menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Duel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // 글꼴 변경
        JLabel duelLabel = new JLabel("Image Duel");
        duelLabel.setBounds(300, 20, 400, 70);
        duelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        duelLabel.setFont(new Font("SansSerif", Font.PLAIN, 60)); // Arial 대신 SansSerif로 변경
        duelLabel.setForeground(Color.BLUE);

        // Image Label 설정
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(Menu.class.getResource("../JavaProject/image/Menu.jpg"));
        imageLabel.setIcon(icon);
        imageLabel.setBounds(250, 200, 500, 400); // 위치 및 크기 수정

        // Duel Label과 Image Label을 Panel에 추가
        panel.add(duelLabel);
        panel.add(imageLabel);

        // 버튼 생성
        JButton startButton = new JButton("게임 시작");
        JButton tutorialButton = new JButton("튜토리얼");
        JButton exitButton = new JButton("종료");

        startButton.setBounds(450, 700, 100, 50);
        tutorialButton.setBounds(450, 760, 100, 50);
        exitButton.setBounds(450, 820, 100, 50);

        // 패널 생성 및 컴포넌트 추가
        panel.add(startButton);
        panel.add(tutorialButton);
        panel.add(exitButton);

        frame.add(panel);

        frame.setVisible(true);
    }
}
