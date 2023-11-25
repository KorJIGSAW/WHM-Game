import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
    private String image;
    private boolean isFaceUp;
    private int peopleCount;

    public Card(String image, boolean isFaceUp, int peopleCount) {
        this.image = image;
        this.isFaceUp = isFaceUp;
        this.peopleCount = peopleCount;
    }

    public String getImage() {
        return image;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public int getPeopleCount() {
        return peopleCount;
    }
    
    public static List<Card> insert_card() {
    List<String> imageNames = new ArrayList<>();
    try {
        imageNames = Files.readAllLines(Paths.get("imageNames.txt"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    List<Card> deck = new ArrayList<>();
    for (String name : imageNames) {
        File file = new File("./image/나무/" + name);
        if (!file.exists()) {
            file = new File("./image/동물/" + name);
            if (!file.exists()) {
                file = new File("./image/사람/" + name);
            }
        }
        if (file.exists()) {
            deck.add(new Card(file.getPath(), false, 103)); // peopleCount는 예시입니다. 실제로는 적절한 값을 사용해주세요.
        }
    }
    return deck;
}
}

/*
 * 고쳐야할 부분이 있다. 
 * 풍경, 동물, 사람 폴더에 있는 이미지를 랜덤으로 뽑아서 카드를 만들어야 한다.
 * 현재 코드를 보면 아래쪽에 Boolean형태로 갈라서 둘중 하나로 넣고 있는 image/사람 폴더에 사진 9개를 추가하고
 * 3폴더에서 총 27장중 9장을 랜덤으로 선별하는 코드가 필요하다.
 * 이 내용은 수정시 주석을 삭제한다.
 */
