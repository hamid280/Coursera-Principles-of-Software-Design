import java.util.ArrayList;

/**
 * Created by Hamit on 8/12/2016.
 */
public class AbstractMarkovFour extends AbstractMarkovModel {
    public AbstractMarkovFour(int n) {
        super(n);
    }

    @Override
    public String getRandomText(int numChars){
        if (myString == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myString.length()-4);
        String key = myString.substring(index, index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}
