import java.util.ArrayList;

/**
 * Created by Hamit on 8/12/2016.
 */
public class AbstractMarkovOne extends AbstractMarkovModel {
    @Override
    public String getRandomText(int numChars) {
        if(myString == null)
            return " ";
        StringBuilder stringBuilder = new StringBuilder();
        int index = myRandom.nextInt(myString.length()-1);
        String key = myString.substring(index, index+1);
        stringBuilder.append(key);
        for (int i = 0; i < numChars-1 ; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            stringBuilder.append(next);
            key = key.substring(1) + next;
        }

        return stringBuilder.toString();
    }


}
