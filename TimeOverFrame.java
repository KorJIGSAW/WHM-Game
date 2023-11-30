import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeOverFrame extends JFrame {
    public TimeOverFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("시간이 초과되었습니다!", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        JButton button = new JButton("처음으로 돌아가기");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Theme theme = new Theme(); // Theme 객체 생성
                new ThemeFrame(theme); // ThemeFrame 객체 생성 후 Theme 객체 전달
                dispose();
            }
        });
        panel.add(button, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
