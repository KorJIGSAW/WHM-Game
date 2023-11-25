import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ThemeFrame extends JFrame {
    private Theme theme;

    public ThemeFrame(Theme theme) {
        this.theme = theme;

        setSize(900, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        List<String> imageNames = getImageNamesFromFile("./imageNames.txt");

        for (String imageName : imageNames) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            ImageIcon icon = new ImageIcon("./image/" + imageName + ".png");
            Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            panel.add(imageLabel, BorderLayout.CENTER);

            JButton button = new JButton(imageName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(imageName + " 버튼이 클릭되었습니다."); // 로그 출력

                    if (theme.getPlayer1Theme() == null) {
                        theme.setPlayer1Theme(imageName);
                        System.out.println("플레이어 1의 테마가 " + imageName + "로 설정되었습니다."); // 로그 출력
                    } else if (theme.getPlayer2Theme() == null) {
                        theme.setPlayer2Theme(imageName);
                        System.out.println("플레이어 2의 테마가 " + imageName + "로 설정되었습니다."); // 로그 출력
                        ThemeFrame.this.dispose();
                        new GFrame(theme); // GFrame의 생성자에 theme 인스턴스를 인자로 넘김
                    }
                }
            });

            panel.add(button, BorderLayout.SOUTH);

            add(panel);
        }

        setVisible(true);
    }

    private List<String> getImageNamesFromFile(String filename) {
        List<String> imageNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                imageNames.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageNames;
    }
}
