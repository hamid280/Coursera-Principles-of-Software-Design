/**
 * Created by Hamit on 8/9/2016.
 */
public class MagnitudeFilter implements Filter {
    private double maxMag;
    private double minMag;

    public MagnitudeFilter(double min, double max) {
        maxMag = max;
        minMag = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }

    @Override
    public String getName() {
        return "Magnitude";
    }
}
