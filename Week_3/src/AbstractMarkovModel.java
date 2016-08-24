import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hamit on 8/12/2016.
 */
public abstract class AbstractMarkovModel implements IMarkovModel {

    protected String myString;
    protected Random myRandom;
    protected int myOrder;

    public AbstractMarkovModel(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    public void setTraining(String text){
        myString = text;
    }

    abstract public String getRandomText(int numChars);

    protected  ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myString.length()) {
            int start = myString.indexOf(key, pos);
            if(start == -1) {
                break;
            }
            if(start >= myString.length() - 1) {
                break;
            }
            String next = myString.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }

    public void setRandom(int seed) {
    }
}
