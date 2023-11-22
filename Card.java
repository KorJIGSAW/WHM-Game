import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Card {
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

    public int getPeopleCount(){
        return peopleCount;
    }
    
    public static List<Card> insert_card(){
        File folder1 = new File("./image/풍경");
        File folder2 = new File("./image/동물");
        File[] listOfFiles1 = folder1.listFiles();
        File[] listOfFiles2 = folder2.listFiles();

        if (listOfFiles1 == null || listOfFiles2 == null) {
            System.out.println("Cannot find directory ./image/풍경 or ./image/동물");
            return null;
        }

        List<Card> deck = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < 9; i++) {
            // '풍경'과 '동물' 폴더 중 어느 폴더에서 이미지를 선택할지 랜덤하게 결정
            File[] chosenFolder = rand.nextBoolean() ? listOfFiles1 : listOfFiles2;
            File file = chosenFolder[rand.nextInt(chosenFolder.length)];
            
            if (file.isFile()) {
                deck.add(new Card(file.getPath(), false, 103)); // peopleCount는 예시입니다. 실제로는 적절한 값을 사용해주세요.
            }
        }

        return deck;
    }
}
