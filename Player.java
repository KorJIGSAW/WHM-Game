import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private int points;
    private List<Card> hand;  // 카드를 저장할 리스트

    public Player(String id) {
        this.id = id;
        this.points = 0;
        this.hand = new ArrayList<>();  // 리스트 초기화
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }
}
