
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes
{
   public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0; j < howMany; j++) {
            int minIndex = 0;
            for(int k=1; k < copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                Location loc = quake.getLocation();
                if (loc.distanceTo(current) < 
                    copy.get(minIndex).getLocation().distanceTo(current)){
                    minIndex = k;   
                }
            }

            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
   }

    public ArrayList<QuakeEntry> getClosest2(ArrayList<QuakeEntry> quakeData, Location current, int howMany){

        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();

        if (howMany >= quakeData.size()){
            result.addAll(quakeData);
        }
        else {
            copy.sort(new Comparator<QuakeEntry>() {
                @Override
                public int compare(QuakeEntry first, QuakeEntry second) {

                    //distance from QuakeEntry first to the Current location
                    double firstToCurrent = first.getLocation().distanceTo(current);

                    //distance from QuakeEntry second to the Current location
                    double secondToCurrent = second.getLocation().distanceTo(current);

                    if (firstToCurrent < secondToCurrent)
                        return -1;
                    else if (firstToCurrent > secondToCurrent)
                        return 1;
                    else
                        return 0;
                }
            });

            for (int i = 0; i < howMany ; i++) {
                result.add(copy.get(i));
            }
        }
        return result;
    }


    public void findClosestQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedatasmall.atom";
		//String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list  = parser.read(source);
		System.out.println("read data for " + list.size());

		Location jakarta  = new Location(-6.211, 106.845);

		ArrayList<QuakeEntry> close = getClosest2(list, jakarta, 3);

		for(int k=0; k < close.size(); k++){
			QuakeEntry entry = close.get(k);
			double distanceInMeters = jakarta.distanceTo(entry.getLocation());
			System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
		}
		System.out.println("number found: " + close.size());
    }

}
