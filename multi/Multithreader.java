package multi;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import needleman.ReadFile;
import needleman.needlemanWunch;

public class Multithreader {

    public ExecutorService es;
    public String[] alignment;
    public int snv;
    public int[] indelcount;
    
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
    
            Multithreader mt = new Multithreader(s1, s2);

            mt.getCounts();
            mt.es.shutdown();
            //waits for all threads to finish
            try {
                mt.es.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
            System.out.println("Single Nucleotide Variants: " + mt.snv);
            System.out.println("Insertions: " + mt.indelcount[0]);
            System.out.println("Deletions: " + mt.indelcount[1]);
            mt.getChecks();
            long endTime = System.nanoTime();
            times.add((endTime-startTime)/1000000);
        }
        System.out.println("Times: " + times);
        System.out.println("Total time: " + times.stream().mapToLong(Long::longValue).sum());
        System.out.println("Average time: " + times.stream().mapToLong(Long::longValue).sum()/times.size());
        
        
        
        //ReadFile rf = new ReadFile();
        //String s1 = rf.read("project/");
        //String s2 = rf.read("project/");
        Multithreader mt = new Multithreader("AATCGCCTGA", "ATGGGCTATA");
        mt.getCounts();
        mt.es.shutdown();

        //waits for all threads to finish
        try {
            mt.es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Single Nucleotide Variants: " + mt.snv);
        System.out.println("Insertions: " + mt.indelcount[0]);
        System.out.println("Deletions: " + mt.indelcount[1]);
        mt.getChecks();
        
    } 

    public Multithreader(String s1,String s2) {
        this.es = Executors.newCachedThreadPool();
        needlemanWunch nw = new needlemanWunch();
        this.alignment = nw.getAllignments(s1, s2);
        this.snv = 0;
        this.indelcount = new int[2];
    }


    public void getCounts(){

        for (int i = 0; i < alignment[0].length(); i++) {
            int j = i;
            es.submit(new Runnable() {
                @Override
                public void run() {
                    if ((alignment[0].charAt(j) != '-' & alignment[1].charAt(j) != '-') & alignment[0].charAt(j) != alignment[1].charAt(j)) {
                        snv++;
                    }
                }
            });
            es.submit(new Runnable() {
                @Override
                public void run() {
                    if (alignment[0].charAt(j) == '-' & alignment[1].charAt(j) != '-') {
                        indelcount[0]++;
                    } else if (alignment[0].charAt(j) != '-' & alignment[1].charAt(j) == '-') {
                        indelcount[1]++;
                    }
                }
            });
        }
    }

    public void getChecks(){
        //frameshiftcheck
        boolean[] frameshift = new boolean[2];
        if (indelcount[0] % 3 == 0) {
            frameshift[0] = false;
        } else {
            frameshift[0] = true;
        }
        if (indelcount[1] % 3 == 0) {
            frameshift[1] = false;
        } else {
            frameshift[1] = true;
        }
        System.out.println("Frameshift Insertion: " + frameshift[0]);
        System.out.println("Frameshift Deletion: " + frameshift[1]);

        //PairwiseStructuralVariation
        boolean b1 = checkForStructuralVariation(alignment[0]);
        if (b1) {
            System.out.println("Structural Variation in first sequence");
        }else{
            System.out.println("No Structural Variation in first sequence");
        }
        boolean b2 = checkForStructuralVariation(alignment[1]);
        if (b2) {
            System.out.println("Structural Variation in second sequence");
        }else{
            System.out.println("No Structural Variation in second sequence");
        }
    }

    public boolean checkForStructuralVariation(String s){

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
        boolean structuralVariation = false;
        for (int i = 0; i < indelLengths.size(); i++) {
            if (indelLengths.get(i) >= 50) {
                structuralVariation = true;
            }
        }
        return structuralVariation;    
    }

}
