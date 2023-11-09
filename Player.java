public class Player {
    private String id;
    private int points;

    public Player(String id) {
        this.id = id;
        this.points = 0;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}