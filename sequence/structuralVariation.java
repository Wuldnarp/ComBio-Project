package sequence;
import java.util.ArrayList;

public class structuralVariation {

    public void checkPairwiseStructuralVariation(String[] allignments){

        boolean b1 = checkForStructuralVariation(lengthOfEachIndel(allignments[0]));
        if (b1) {
            System.out.println("Structural Variation in first sequence");
        }else{
            System.out.println("No Structural Variation in first sequence");
        }
        boolean b2 = checkForStructuralVariation(lengthOfEachIndel(allignments[1]));
        if (b2) {
            System.out.println("Structural Variation in second sequence");
        }else{
            System.out.println("No Structural Variation in second sequence");
        }
    }

    public ArrayList<Integer> lengthOfEachIndel(String s){

        ArrayList<Integer> indelLengths = new ArrayList<Integer>();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                count++;
            } else {
                if (count != 0) {
                    indelLengths.add(count);
                    count = 0;
                }
            }
        }
        if (count != 0) {
            indelLengths.add(count);
        }
        return indelLengths;
    }

    public boolean checkForStructuralVariation(ArrayList<Integer> indelLengths){
        boolean structuralVariation = false;
        for (int i = 0; i < indelLengths.size(); i++) {
            if (indelLengths.get(i) >= 50) {
                structuralVariation = true;
            }
        }
        return structuralVariation;
    }
}