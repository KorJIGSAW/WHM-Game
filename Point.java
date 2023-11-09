import java.util.ArrayList;
import java.util.List;


public class Point {
    private List<Player> players;

    public Point() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}