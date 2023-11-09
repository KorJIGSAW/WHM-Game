public class Winner {
    private Point point;

    public Winner(Point point) {
        this.point = point;
    }

    public Player determineWinner() {
        Player winner = null;
        int highestScore = Integer.MIN_VALUE;

        for(Player player : point.getPlayers()) {
            if(player.getPoints() > highestScore) {
                highestScore = player.getPoints();
                winner = player;
            }
        }

        return winner;
    }
}