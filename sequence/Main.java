package sequence;

import java.util.HashMap;
import java.util.LinkedList;

import needleman.ReadFile;
import needleman.needlemanWunch;

public class Main {
    
    public static void main(String[] args) {

        LinkedList<Long> times = new LinkedList<Long>();
        ReadFile rf = new ReadFile();
        HashMap<Integer, LinkedList<String>> map = rf.getList();
        LinkedList<String> list = map.get(1);
        LinkedList<String> list2 = map.get(2);


        for(int i = 0; i < list.size(); i++){
            long startTime = System.nanoTime();
            String s1 = rf.read("project/1000bases/"+list.get(i));
            String s2 = rf.read("project/1000bases/"+list2.get(i));
    
            needlemanWunch nw = new needlemanWunch();
            String[] allingment = nw.getAllignments(s1, s2);

            singleNucleotideVariants snv = new singleNucleotideVariants();
            indel id = new indel();
            structuralVariation sv = new structuralVariation();
        
            snv.getSingleNucleotideVariants(allingment);
            id.frameshift(allingment);
            sv.checkPairwiseStructuralVariation(allingment);


            long endTime = System.nanoTime();
            times.add((endTime-startTime)/1000000);
        }

        System.out.println("Times: " + times);
        System.out.println("Total time: " + times.stream().mapToLong(Long::longValue).sum());
        System.out.println("Average time: " + times.stream().mapToLong(Long::longValue).sum()/times.size());
    } 
    
}
