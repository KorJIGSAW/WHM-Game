import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialFrame extends JFrame {
    public TutorialFrame() {
        setTitle("Tutorial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Center
        ImageIcon tutorialIcon = new ImageIcon(new ImageIcon("./image/Tutorial.png").getImage().getScaledInstance(1200, 800, Image.SCALE_DEFAULT));
        JLabel tutorialLabel = new JLabel(tutorialIcon);
        c.add(tutorialLabel, BorderLayout.CENTER);

        // South
        JPanel buttonPanel = new JPanel();
        JButton goBackButton = new JButton("게임으로 돌아가기");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Start();
            }
        });
        buttonPanel.add(goBackButton);
        c.add(buttonPanel, BorderLayout.SOUTH);

        setSize(1300, 900); // Set size
        setVisible(true);
    }
}
