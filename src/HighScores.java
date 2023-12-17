import java.util.ArrayList;

public class HighScores {
    ArrayList<ScoreInfo> scores;

    public HighScores() {
        scores = new ArrayList<ScoreInfo>();
    }

    public void addScore(String score) {
        int indexSeparator = score.indexOf("&");

        scores.add(new ScoreInfo(
                score.substring(0, indexSeparator).trim(),
                Integer.parseInt(score.substring(indexSeparator + 1).trim())
        ));
    }

    public String toString() {
        String s = "HIGH$$";
        for (ScoreInfo si : scores) {
            s += si.getName() + "&" + si.getScore() + "&";
        }
        return s;
    }

    public void saveScores(){
        // backup scores
        System.out.println("Saving scores");
    }
}
