import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GFrame extends JFrame {
    private JTextField nameField1, nameField2, scoreField1, scoreField2;
    private JLabel winnerLabel;

    public GFrame() {
        setLayout(new GridLayout(5, 2));
        add(new JLabel("첫번째 플레이어의 이름을 입력 : "));
        nameField1 = new JTextField();
        add(nameField1);

        add(new JLabel("첫번째 플레이어의 점수를 입력 : "));
        scoreField1 = new JTextField();
        add(scoreField1);

        add(new JLabel("두번째 플레이어의 이름을 입력 : "));
        nameField2 = new JTextField();
        add(nameField2);

        add(new JLabel("두번째 플레이어의 점수를 입력 : "));
        scoreField2 = new JTextField();
        add(scoreField2);

        JButton button = new JButton("Determine Winner");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = new Point();

                Player player1 = new Player(nameField1.getText());
                player1.addPoints(Integer.parseInt(scoreField1.getText()));
                point.addPlayer(player1);

                Player player2 = new Player(nameField2.getText());
                player2.addPoints(Integer.parseInt(scoreField2.getText()));
                point.addPlayer(player2);

                Winner winner = new Winner(point);
                Player winningPlayer = winner.determineWinner();

                winnerLabel.setText("Winner is " + winningPlayer.getId() + " with " + winningPlayer.getPoints() + " points.");
            }
        });
        add(button);

        winnerLabel = new JLabel();
        add(winnerLabel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000,1000);
    }
}
