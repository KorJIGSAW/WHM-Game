import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeFrame extends JFrame {
    private Theme theme;

    public ThemeFrame(Theme theme) {
        this.theme = theme;

        setSize(900, 300); // 프레임의 크기도 가로로 길게 변경
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3)); // 수정된 부분

        String[] themes = {"사람", "동물", "의자"};

        for (String themeName : themes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            ImageIcon icon = new ImageIcon("./image/주제/" + themeName + ".png");
            Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            panel.add(imageLabel, BorderLayout.CENTER);

            JButton button = new JButton(themeName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(themeName + " 버튼이 클릭되었습니다."); // 로그 출력
            
                    if (theme.getPlayer1Theme() == null) {
                        theme.setPlayer1Theme(themeName);
                        System.out.println("플레이어 1의 테마가 " + themeName + "로 설정되었습니다."); // 로그 출력
                    } else if (theme.getPlayer2Theme() == null) {
                        theme.setPlayer2Theme(themeName);
                        System.out.println("플레이어 2의 테마가 " + themeName + "로 설정되었습니다."); // 로그 출력
                        ThemeFrame.this.dispose();
                        new GFrame(Card.insert_card(), theme);
                    }
                }
            });
            
            panel.add(button, BorderLayout.SOUTH);
            
            add(panel);
        }

        setVisible(true);
    }
}
