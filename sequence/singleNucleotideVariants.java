package sequence;


public class singleNucleotideVariants {

    public int getSingleNucleotideVariants(String[] allingment){

        int count = 0;

        for (int i = 0; i < allingment[0].length(); i++) {
            if ((allingment[0].charAt(i) != '-' & allingment[1].charAt(i) != '-') & allingment[0].charAt(i) != allingment[1].charAt(i)) {
                count++;
            }
        }
        System.out.println("Single Nucleotide Variants: " + count);
        return count;
    }
}
