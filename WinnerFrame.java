import javax.swing.*;
import java.awt.*;

public class WinnerFrame extends JFrame {
    public WinnerFrame(Player player1, Player player2, Theme theme) {
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel winnerLabel = new JLabel();
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(50, 50, 300, 50);
        winnerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        if(player1.getLastCard().getCount(theme.getPlayer1Theme()) > player2.getLastCard().getCount(theme.getPlayer2Theme())) {
            winnerLabel.setText("첫번째 플레이어가 승리하였습니다!");
        } else if(player1.getLastCard().getCount(theme.getPlayer1Theme()) < player2.getLastCard().getCount(theme.getPlayer2Theme())) {
            winnerLabel.setText("두번째 플레이어가 승리하였습니다!");
        } else {
            winnerLabel.setText("무승부입니다!");
        }
        /* 고쳐야할 사항 추가 현재 첫번째 플레이이가 승리하였... 이런식으로 나옴
         * 수정할 시 이 주석은 삭제할 것.
         */

        getContentPane().add(winnerLabel);
        setVisible(true);
    }
}
