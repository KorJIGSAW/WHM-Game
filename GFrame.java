import java.awt.Color;
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
    private List<Card> deck;
    private List<JButton> buttons;

    public GFrame(List<Card> deck) {
        this.deck = deck;

        player1 = new Player("Player1");
        player2 = new Player("Player2");

        // GUI 설정
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 카드 버튼 생성
        buttons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton(deck.get(i).getImage());
            button.setBorder(new LineBorder(Color.WHITE)); // 초기 테두리 색상은 하얀색
            int finalI = i;
            button.addActionListener(new ActionListener() {
                private boolean isClicked = false; // 버튼이 클릭되었는지 확인하는 변수

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 버튼을 클릭하면 카드를 뽑고 테두리 색상을 변경
                    if (!isClicked) {
                        player1.drawCard(deck.get(finalI));
                        deck.remove(finalI);
                        button.setBorder(new LineBorder(Color.BLUE)); // 첫 클릭시 파란색 테두리
                        isClicked = true;
                    } else {
                        player2.drawCard(deck.get(finalI));
                        deck.remove(finalI);
                        button.setBorder(new LineBorder(Color.RED)); // 두번째 클릭시 빨간색 테두리
                        button.setEnabled(false); // 두번 클릭하면 버튼 비활성화
                    }
                }
            });
            buttons.add(button);
            add(button);
        }

        setVisible(true);
    }
}
