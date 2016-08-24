/**
 * Created by Hamit on 8/12/2016.
 */
public class AbstractMarkovZero extends AbstractMarkovModel {
    public AbstractMarkovZero(int n) {
        super(n);
    }

    @Override
    public String getRandomText(int numChars) {
        if (myString == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numChars ; i++) {
            int index = myRandom.nextInt(myString.length());
            stringBuilder.append(myString.charAt(index));
        }

        return stringBuilder.toString();
    }
}
