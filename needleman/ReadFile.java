package needleman;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner; // Import the Scanner class to read text files


public class ReadFile {
    
    public String read(String path){
        StringBuilder data = new StringBuilder();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        return data.toString();
    }

    public HashMap<Integer, LinkedList<String>> getList(){

        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> list2 = new LinkedList<String>();
        HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();

        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq1.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq2.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq3.txt");
        list.add("seq4.txt");
        list.add("seq4.txt");
        list.add("seq4.txt");
        list.add("seq4.txt");
        list.add("seq4.txt");
        list.add("seq4.txt");
        list.add("seq5.txt");
        list.add("seq5.txt");
        list.add("seq5.txt");
        list.add("seq5.txt");
        list.add("seq5.txt");
        list.add("seq6.txt");
        list.add("seq6.txt");
        list.add("seq6.txt");
        list.add("seq6.txt");
        list.add("seq7.txt");
        list.add("seq7.txt");
        list.add("seq7.txt");
        list.add("seq8.txt");
        list.add("seq8.txt");
        list.add("seq9.txt");

        list2.add("seq2.txt");
        list2.add("seq3.txt");
        list2.add("seq4.txt");
        list2.add("seq5.txt");
        list2.add("seq6.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq3.txt");
        list2.add("seq4.txt");
        list2.add("seq5.txt");
        list2.add("seq6.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq4.txt");
        list2.add("seq5.txt");
        list2.add("seq6.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq5.txt");
        list2.add("seq6.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq6.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq7.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq8.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq9.txt");
        list2.add("seq10.txt");
        list2.add("seq10.txt");

        map.put(1, list);
        map.put(2, list2);


        return map;
    }
}
