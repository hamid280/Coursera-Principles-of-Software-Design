import java.util.ArrayList;

/**
 * Created by Hamit on 8/12/2016.
 */
public class MarkovModel_abstract extends AbstractMarkovModel {
    private int myOrder;

    public MarkovModel_abstract(int order){
        super(order);
        myOrder = order;

    }

    @Override
    public String getRandomText(int numChars) {
        if (myString == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myString.length() - myOrder);
        String key = myString.substring(index, index + myOrder);
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
}
