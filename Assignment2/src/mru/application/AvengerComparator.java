package mru.application;

import java.util.Comparator;


public class AvengerComparator implements Comparator<Avenger>{

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




