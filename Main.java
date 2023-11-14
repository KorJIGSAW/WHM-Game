import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // 프로그램의 실행을 담당하는 클래스
    public static void main(String [] args) {
        File folder = new File("./images");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("Cannot find directory ./images");
            return;
        }

        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (listOfFiles[i].isFile()) {
                deck.add(new Card(listOfFiles[i].getName(), false));
            }
        }

        new GFrame(deck);
    }
}

