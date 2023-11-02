package mru.application;

import java.util.Comparator;

public class PerformerComparator implements Comparator<Avenger>{

	/**
	 * compares two avenger objects based on their performer frequency
	 * hero name length and hero alias alphabetical order 
	 * @param avenger1 the first avenger to compare 
	 * @param avenger2 the second avenger to compare 
	 * @return negative if avenger1 is less than avenger2, 
	 * postive if otherwise and 0 is they are equal 
	 */
    @Override
    public int compare(Avenger avenger1, Avenger avenger2) {
        int result = 0;
        int lengthCompare = 0;
        if (avenger2.getPerformerFreq() > avenger1.getPerformerFreq()) {
        	result = 1;
        }
        else if (avenger2.getPerformerFreq() < avenger1.getPerformerFreq()) {
        	result = -1;
        }else {
            lengthCompare = avenger1.getHeroName().length() - avenger2.getHeroName().length();
        		result = lengthCompare;
            if (lengthCompare == 0) {
                result = avenger1.getHeroAlias().compareTo(avenger2.getHeroAlias());
            }
            else {
                result = lengthCompare;
            }
        }
     

        return result;
    }
	
	

}

