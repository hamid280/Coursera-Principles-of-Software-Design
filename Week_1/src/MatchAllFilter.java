import java.util.ArrayList;

/**
 * Created by Hamit on 8/9/2016.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    MatchAllFilter(){
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters){
            if (!f.satisfies(qe))
                return false;
        }
        return true;
    }

    @Override
    public String getName() {
        String filterNames = " ";
        for (Filter f : filters){
            filterNames += (f.getName() + " ");
        }
        return filterNames;
    }
}
