/**
 * Created by Hamit on 8/9/2016.
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDist;

    DistanceFilter(Location loc, double maxDistance){
        location = loc;
        maxDist = maxDistance;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDist;
    }

    @Override
    public String getName() {
        return "Distance";
    }
}
