import java.util.ArrayList;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter MagFilter = new MagnitudeFilter(4.0, 5.0);
        Filter DepFilter = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> magFilter  = filter(list, MagFilter);
        ArrayList<QuakeEntry> depFilter = filter(magFilter,DepFilter);
        for (QuakeEntry qe: depFilter) {
            System.out.println(qe);
        }
        System.out.println("Japan filter");
        Location japan = new Location(35.42, 139.43);
        double maxDistance = 10000000;
        Filter distanceFromJapan = new DistanceFilter(japan, maxDistance);
        Filter onlyInJapan = new PhraseFilter("Japan", "end");

        ArrayList<QuakeEntry> fromJapan = filter(list, distanceFromJapan);
        ArrayList<QuakeEntry> inJapan = filter(fromJapan, onlyInJapan);
        for (QuakeEntry qe : inJapan){
            System.out.println(qe);
        }
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();

        MagnitudeFilter magnitudeFilter = new MagnitudeFilter(0.0, 2.0);
        DepthFilter depthFilter = new DepthFilter(-100000.0, -10000.0);
        PhraseFilter phraseFilter = new PhraseFilter("a", "any");
        maf.addFilter(magnitudeFilter);
        maf.addFilter(depthFilter);
        maf.addFilter(phraseFilter);
        System.out.println("Filters used are : " + maf.getName());
        ArrayList<QuakeEntry> result = filter(list,maf);
        for (QuakeEntry qe : result){
            System.out.println(qe);
        }
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();

        MagnitudeFilter magnitudeFilter = new MagnitudeFilter(0.0, 3.0);
        Location Oklahama = new Location(36.1314, -95.9372);
        DistanceFilter distanceFilter = new DistanceFilter(Oklahama,10000000 );
        PhraseFilter phraseFilter = new PhraseFilter("Ca", "any");
        maf.addFilter(magnitudeFilter);
        maf.addFilter(distanceFilter);
        maf.addFilter(phraseFilter);

        ArrayList<QuakeEntry> result = filter(list,maf);
        for (QuakeEntry qe : result){
            System.out.println(qe);
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
