import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            BufferedReader reader = new BufferedReader(new FileReader("RandomCard.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                imageNames.add(line.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.shuffle(imageNames);

        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            deck.add(new Card(imageNames.get(i), false, 0));
        }
        return deck;
    }
}