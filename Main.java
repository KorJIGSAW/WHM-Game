import java.util.Scanner;

public class Main {
    // 프로그램의 실행을 담당하는 클래스
	public static void main(String [] args) {
        new GFrame();
		Scanner scanner = new Scanner(System.in);
		Point point = new Point();
		
		for(int i=0; i<2; i++) {
            System.out.println((i+1) + "번째 플레이어의 이름을 입력하세요: ");
            String playerId = scanner.nextLine();

            System.out.println(playerId + "의 점수를 입력하세요: ");
            int playerPoints = scanner.nextInt();
            scanner.nextLine();  // 줄바꿈 문자 제거

            Player player = new Player(playerId);
            player.addPoints(playerPoints);
            point.addPlayer(player);
        }

        Winner winner = new Winner(point);
        Player winningPlayer = winner.determineWinner();
        System.out.println("승자는 " + winningPlayer.getId() + "입니다. 점수는 " + winningPlayer.getPoints() + "점입니다.");
	}
}







