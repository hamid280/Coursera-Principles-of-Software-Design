import edu.duke.FileResource;

/**
 * Created by Hamit on 8/12/2016.
 */
public class MarkovRunnerWithInterface {

    private void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int i = 0; i < 3 ; i++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        runModel(new AbstractMarkovZero(), st, 500, 35 );
        runModel(new AbstractMarkovOne(), st, 500, 25 );
        runModel(new MarkovModel_abstract(3), st, 500,34 );
        runModel(new AbstractMarkovFour(), st, 500, 54 );

    }
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
