import java.util.ArrayList;

public class HighScores {
    ArrayList<ScoreInfo> scores;

    public HighScores() {
        scores = new ArrayList<ScoreInfo>();
    }

    public void addScore(String name, int score) {
        scores.add(new ScoreInfo(name, score));
    }

    public String toString() {
        String result = "";
        for (ScoreInfo score : scores) {
            result += score.toString() + "\n";
        }
        return result;
    }
}
