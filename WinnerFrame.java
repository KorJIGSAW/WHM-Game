import javax.swing.*;
import java.awt.*;

public class WinnerFrame extends JFrame {
    public WinnerFrame(int player1, int player2, Theme theme) {
        System.out.println(player1 );
        System.out.println(player2 );
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel winnerLabel = new JLabel();
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(50, 50, 800, 50);
        winnerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        if(player1 > player2) {
            winnerLabel.setText("첫번째 플레이어가 승리하였습니다!");
        } else if(player1 < player2) {
            winnerLabel.setText("두번째 플레이어가 승리하였습니다!");
        } else {
            winnerLabel.setText("무승부입니다!");
        }
        
        getContentPane().add(winnerLabel);
        setVisible(true);
        /*
         * 다시하기 버튼 추가 및 기능 구현 필요
         * 첫번째 Frame제작및 연결해야함
         */
    }
}
