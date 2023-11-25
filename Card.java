import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
    private String image;
    private boolean isFaceUp;
    private int peopleCount;
    private int treeCount;
    private int animalCount;

    public Card(String image, boolean isFaceUp, int peopleCount, int treeCount, int animalCount) {
        this.image = image;
        this.isFaceUp = isFaceUp;
        this.peopleCount = peopleCount;
        this.animalCount = animalCount;
        this.treeCount = treeCount;
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
    
    public int getTreeCount(){
        return treeCount;
    }

    public int getAnimalCount(){
        return animalCount;
    }

    public String getImageName() {
        File file = new File(image);
        return file.getName();
    }

    public static List<Card> insert_card() {
        File folder1 = new File("./image/나무");
        File folder2 = new File("./image/동물");
        File folder3 = new File("./image/사람");
        File[][] listOfFiles = {folder1.listFiles(), folder2.listFiles(), folder3.listFiles()};

        if (listOfFiles[0] == null || listOfFiles[1] == null || listOfFiles[2] == null) {
            System.out.println("Cannot find directory ./image/나무 or ./image/동물 or ./image/사람");
            return null;
        }

        List<Card> deck = new ArrayList<>();
        Random rand = new Random();

        // 각 폴더에서 뽑을 이미지의 인덱스를 저장할 배열
        int[] selectedIndexes = {0, 0, 0};
        
        for (int i = 0; i < 9; i++) {
            // 랜덤하게 폴더 선택 (0: 풍경, 1: 동물, 2: 사람)
            int selectedFolderIndex = rand.nextInt(3);
            
            // 선택된 폴더에서 이미지 선택
            File[] chosenFolder = listOfFiles[selectedFolderIndex];
            File file = chosenFolder[selectedIndexes[selectedFolderIndex]];
        
            if (file.isFile()) {
                String fileNameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf("."));
                String[] counts = fileNameWithoutExtension.split("_");  // 파일 이름을 '_'로 분리
                int peopleCount = Integer.parseInt(counts[1]);
                int animalCount = Integer.parseInt(counts[2]);
                int treeCount = Integer.parseInt(counts[3]);
                System.out.println("peopleCount: " + "animalCount: " + animalCount + ", treeCount: " +  treeCount);
                deck.add(new Card(file.getPath(), false, peopleCount, treeCount, animalCount));
            }
            
            // 해당 폴더에서 다음에 뽑을 이미지의 인덱스 증가
            selectedIndexes[selectedFolderIndex]++;
        }
        return deck;
    }
}