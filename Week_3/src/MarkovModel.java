import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hamit on 8/12/2016.
 */
public class MarkovModel implements IMarkovModel {

    private String myText;
    private Random myRandom;
    private int myOrder;

    public MarkovModel(int n) {
        myRandom = new Random();
        myOrder = n;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String key = myText.substring(index, index + myOrder);
        sb.append(key);
        for (int k = 0; k < numChars - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("MarkovModel of order %d", myOrder);
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1) {
                break;
            }
            if (start >= myText.length() - 1) {
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
}
