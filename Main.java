import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Card> deck = Card.insert_card(); // insert_card() 메소드로 카드 덱을 받아옴
        new GFrame(deck);
    }
}
