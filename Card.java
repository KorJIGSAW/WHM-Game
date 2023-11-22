import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Card {
    // 카드 9장 가져오기 Card의 객체 정보저장담당
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
        File folder = new File("./images");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("Cannot find directory ./images");
            return null;
        }

        List<Card> deck = new ArrayList<>();
        if (listOfFiles[0].isFile()) { //가을
            deck.add(new Card(listOfFiles[0].getName(), false, 103));
        }
        if (listOfFiles[1].isFile()) { //공항
            deck.add(new Card(listOfFiles[1].getName(), false, 3));
        }
        if (listOfFiles[2].isFile()) { //기차
            deck.add(new Card(listOfFiles[2].getName(), false, 18));
        }
        if (listOfFiles[3].isFile()) { //나무
            deck.add(new Card(listOfFiles[3].getName(), false, 2));
        }
        if (listOfFiles[3].isFile()) { //런던
            deck.add(new Card(listOfFiles[4].getName(), false, 24));
        }
        if (listOfFiles[3].isFile()) { //산토리니
            deck.add(new Card(listOfFiles[5].getName(), false, 10));
        }
        if (listOfFiles[3].isFile()) { //일본
            deck.add(new Card(listOfFiles[6].getName(), false, 300));
        }
        if (listOfFiles[3].isFile()) { //타지마할
            deck.add(new Card(listOfFiles[7].getName(), false, 40));
        }
        if (listOfFiles[3].isFile()) { //훗카이도
            deck.add(new Card(listOfFiles[8].getName(), false, 0));
        }

        return deck;
    }

}