public class Card {
    // 카드 9장 가져오기 Card의 객체 정보저장담당
	private String image;
    private boolean isFaceUp;

    public Card(String image, boolean isFaceUp) {
        this.image = image;
        this.isFaceUp = isFaceUp;
    }

    public String getImage() {
        return image;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }
}
