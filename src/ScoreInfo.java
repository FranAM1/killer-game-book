public class ScoreInfo {
    String name;

    int score;

    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return name + " " + score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
