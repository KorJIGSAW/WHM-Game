import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

public class GFrame extends JFrame {
    private Player player1;
    private Player player2;
    private List<JButton> buttons;
    private Player currentPlayer;
    private int clickCount;

    public GFrame(List<Card> deck) {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        currentPlayer = player1; // 처음에는 player1이 선택
        clickCount = 0;
        Font font1 =  new Font("Showcard Gothic", Font.BOLD, 30);

        // GUI 설정
        setLayout(new GridLayout(3, 3));
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 카드 버튼 생성
        buttons = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            JButton button = new JButton("?"); // 버튼의 초기 텍스트를 "?"로 설정
            button.setBorder(new LineBorder(Color.WHITE)); // 초기 테두리 색상은 하얀색
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 버튼을 클릭하면 카드를 뽑고 테두리 색상을 변경
                    String fileName = deck.get(finalI).getImage();
                    String baseName = fileName.substring(0, fileName.lastIndexOf('.')); // 확장자 제거
                    button.setFont(font1);
                    button.setText(baseName); // 버튼의 텍스트를 해당 카드의 파일 이름으로 변경(확장자 제외)
                    currentPlayer.drawCard(deck.get(finalI)); // 현재 플레이어에 카드 정보 전달
                    if (currentPlayer == player1) {
                        button.setBorder(new LineBorder(Color.BLUE, 10)); // player1의 턴일 때는 파란색 테두리
                        currentPlayer = player2; // player를 변경
                    } else {
                        button.setBorder(new LineBorder(Color.RED, 10)); // player2의 턴일 때는 빨간색 테두리
                        currentPlayer = player1; // player를 변경
                    }
                    button.setEnabled(false); // 클릭한 버튼은 비활성화
                    clickCount++;
                    if (clickCount == 2) { // 두 번 클릭되면 모든 버튼 비활성화
                        for (JButton btn : buttons) {
                            btn.setEnabled(false);
                        }
                    }
                }
            });
            buttons.add(button);
            add(button);
        }
        setVisible(true);
    }
}
