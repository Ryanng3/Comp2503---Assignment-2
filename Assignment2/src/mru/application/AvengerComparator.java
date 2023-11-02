package mru.application;

import java.util.Comparator;


public class AvengerComparator implements Comparator<Avenger>{

	/**
	 * compares two avenger objects based on their performer frequency
	 * hero name length and hero alias alphabetical order, all added up 
	 * @param avenger1 the first avenger to compare 
	 * @param avenger2 the second avenger to compare 
	 * @return negative if avenger1 is less than avenger2, 
	 * postive if otherwise and 0 is they are equal 
	 */
    @Override
    public int compare(Avenger avenger1, Avenger avenger2) {

        int total1 = avenger1.getNameFreq() + avenger1.getAliasFreq() + avenger1.getPerformerFreq();
        int total2 = avenger2.getNameFreq() + avenger2.getAliasFreq() + avenger2.getPerformerFreq();
        String performer1 = avenger1.getPerformer();
        String performer2 = avenger2.getPerformer();
        
        if (total1 > total2){
            return -1;
        }
        else if (total1 < total2) {
            return 1;
        }
        else {

            if (performer1 == null && performer2 == null) {
                return 0;
            }
            else if (performer1 == null){
                return -1;
            }
            else if (performer2 == null){
                return 1;
            }
            else {
                return performer1.compareTo(performer2);
            }
        }
    }  
}




