/**
 * Created by Hamit on 8/12/2016.
 */
public interface IMarkovModel {
    public void setTraining(String text);
    public String getRandomText(int numChars);

    public void setRandom(int seed);
}
