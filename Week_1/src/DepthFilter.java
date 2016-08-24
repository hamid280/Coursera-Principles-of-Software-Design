/**
 * Created by Hamit on 8/9/2016.
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;

    DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }

    @Override
    public String getName() {
        return "Depth";
    }
}
