import java.io.File;
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
        File folder1 = new File("./image/풍경");
        File folder2 = new File("./image/동물");
        File folder3 = new File("./image/사람");
        File[][] listOfFiles = {folder1.listFiles(), folder2.listFiles(), folder3.listFiles()};

        if (listOfFiles[0] == null || listOfFiles[1] == null || listOfFiles[2] == null) {
            System.out.println("Cannot find directory ./image/풍경 or ./image/동물 or ./image/사람");
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
                deck.add(new Card(file.getPath(), false, 103)); // peopleCount는 예시입니다. 실제로는 적절한 값을 사용해주세요.
            }
            
            // 해당 폴더에서 다음에 뽑을 이미지의 인덱스 증가
            selectedIndexes[selectedFolderIndex]++;
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