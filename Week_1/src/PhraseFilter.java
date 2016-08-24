/**
 * Created by Hamit on 8/9/2016.
 */
public class PhraseFilter implements Filter{

    private String phrase;
    private String request;

    PhraseFilter(String inPhrase, String inRequest){
        phrase = inPhrase;
        request = inRequest;
    }
    public boolean satisfies(QuakeEntry qe) {

        if (request.equals("start")){
            if ((qe.getInfo().substring(0,phrase.length())).equals(phrase)) {
                return true;
            }
        }

        if(request.equals("end")) {
            if((qe.getInfo().substring(qe.getInfo().lastIndexOf(" ") + 1)).equals(phrase)){
                return true;
            }
        }

        if(request.equals("any")){
            if (qe.getInfo().contains(phrase)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "Phrase";
    }
}
