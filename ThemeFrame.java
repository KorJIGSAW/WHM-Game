import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeFrame extends JFrame {
    private Theme theme;

    public ThemeFrame(Theme theme) {
        this.theme = theme;

        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        String[] themes = {"사람", "동물", "의자"};

        for (String themeName : themes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            ImageIcon icon = new ImageIcon("./images/주제/" + themeName + ".jpg");
            Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            panel.add(imageLabel, BorderLayout.CENTER);

            JButton button = new JButton(themeName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (theme.getPlayer1Theme() == null) {
                        theme.setPlayer1Theme(themeName);
                    } else if (theme.getPlayer2Theme() == null) {
                        theme.setPlayer2Theme(themeName);
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
