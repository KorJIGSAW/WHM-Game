import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public Card getLastCard() {
        return cards.get(cards.size() - 1);
    }

    public void drawCard(Card card) {
        cards.add(card);
    }
    private String id;
    private int points;

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}

