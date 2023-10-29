package mru.application;

import java.util.Comparator;

public class PerformerComparator implements Comparator<Avenger>{


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

