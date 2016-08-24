import java.util.ArrayList;

/**
 * Created by Hamit on 8/9/2016.
 */
public class LargestQuakes {

    public void findLargestQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

//        for (QuakeEntry qe : list){
//            System.out.println(qe);
//        }
        System.out.println("there are " + list.size() + " earthquakes");
        int largest = indexOfLargest(list);
        System.out.println("the index of largest is " + largest + " and its magnitude is " + list.get(largest).getMagnitude());

        ArrayList<QuakeEntry> result = getLargest(list,5);
        System.out.println("The 5 earthquake with the largest magnitude are ");
        for (QuakeEntry quakeEntry : result) {
            System.out.println(quakeEntry);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxSofar = 0;

        for (int i = 1; i < data.size() ; i++) {
            if (data.get(i).getMagnitude() > data.get(maxSofar).getMagnitude()){
                maxSofar = i;
            }
        }
        return maxSofar;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData , int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        if (howMany >= quakeData.size()){
            answer.addAll(quakeData);
        }
        else {
            for (int i = 0; i < howMany ; i++) {
                int maxMag = indexOfLargest(copy);
                answer.add(copy.get(maxMag));
                copy.remove(maxMag);
            }
        }
        return answer;
    }


}
