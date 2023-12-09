import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.*;
import java.util.Scanner;
import java.util.HashMap;

public class ThemeFrame extends JFrame {
    private Theme theme;
    private HashMap<String, String> imagePaths;

    public ThemeFrame(Theme theme) {
        this.theme = theme;

        // 이미지 경로를 로드합니다.
        loadImagePaths();

        setSize(900, 300); // 프레임의 크기도 가로로 길게 변경
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3)); // 수정된 부분

        String[] themes = {"human", "animal", "tree"};
        for (String themeName : themes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // 이미지 경로를 map에서 가져옵니다.
            String imagePath = imagePaths.get(themeName);
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
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

    // imageNames.txt 파일에서 이미지 경로를 읽어옵니다.
    private void loadImagePaths() {
        imagePaths = new HashMap<>();
        Path filePath = Paths.get("./imageNames.txt");
        try (Scanner scanner = new Scanner(filePath)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    imagePaths.put(key, value);
                } else {
                    System.out.println("invalid line: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot read image paths file: " + e.getMessage());
        }
    }
}
