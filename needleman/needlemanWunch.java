package needleman;

import java.io.FileWriter;
import java.io.IOException;

public class needlemanWunch {
    
    public static void main(String[] args) {
        needlemanWunch nw = new needlemanWunch();
        ReadFile rf = new ReadFile();
        String s1 = rf.read("project/10000base2.txt");
        String s2 = rf.read("project/10000base3.txt");
        int match = 1;
        int mismatch = -1;
        int gap = -1;
        int[][] matrix = nw.algorithm(s1, s2, match, mismatch, gap);
        nw.printMatrix(matrix, s1, s2);
        System.out.println("Score: " + matrix[s1.length()][s2.length()]);
        System.out.println("Alignment: ");
        String[] al = nw.traceback(matrix, s1, s2, match, mismatch, gap);

            try {
                FileWriter myWriter = new FileWriter("filename1.txt");
                myWriter.write(al[0]);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("filename2.txt");
                myWriter.write(al[1]);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    //needlemanwunch algorithm
    public int[][] algorithm(String s1, String s2, int match, int mismatch, int gap) {

        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];
        int i, j;
        //initialization
        for (i = 0; i <= s1.length(); i++) {
            matrix[i][0] = i * gap;
        }
        for (j = 0; j <= s2.length(); j++) {
            matrix[0][j] = j * gap;
        }
        //filling the matrix
        for (i = 1; i <= s1.length(); i++) {
            for (j = 1; j <= s2.length(); j++) {
                int scoreDiag = matrix[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? match : mismatch);
                int scoreLeft = matrix[i][j - 1] + gap;
                int scoreUp = matrix[i - 1][j] + gap;
                matrix[i][j] = Math.max(Math.max(scoreDiag, scoreLeft), scoreUp);
            }
        }
        return matrix;
    }
    //traceback
    public String[] traceback(int[][] matrix, String s1, String s2, int match, int mismatch, int gap) {
        int i = s1.length();
        int j = s2.length();
        String align1 = "";
        String align2 = "";
        while (i > 0 && j > 0) {
            int score = matrix[i][j];
            int scoreDiag = matrix[i - 1][j - 1];
            int scoreLeft = matrix[i][j - 1];
            int scoreUp = matrix[i - 1][j];
            if (score == scoreDiag + (s1.charAt(i - 1) == s2.charAt(j - 1) ? match : mismatch)) {
                align1 = s1.charAt(i - 1) + align1;
                align2 = s2.charAt(j - 1) + align2;
                i--;
                j--;
            } else if (score == scoreUp + gap) {
                align1 = s1.charAt(i - 1) + align1;
                align2 = "-" + align2;
                i--;
            } else if (score == scoreLeft + gap) {
                align1 = "-" + align1;
                align2 = s2.charAt(j - 1) + align2;
                j--;
            }
        }
        while (i > 0) {
            align1 = s1.charAt(i - 1) + align1;
            align2 = "-" + align2;
            i--;
        }
        while (j > 0) {
            align1 = "-" + align1;
            align2 = s2.charAt(j - 1) + align2;
            j--;
        }

        String[] allignments;

        if (align1.charAt(0) == '-'){
            allignments = new String[] {align2, align1};
        } else {
            allignments = new String[] {align1, align2};
        }

        //print the allignments
        for( String allignment : allignments){
            System.out.println(allignment);
        }
        return allignments;
    }

    //print the matrix
    public void printMatrix(int[][] matrix, String s1, String s2) {
        System.out.print("  ");
        for (int i = 0; i < s2.length(); i++) {
            System.out.print(s2.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            if (i > 0) {
                System.out.print(s1.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    //get allignments
    public String[] getAllignments(String s1, String s2){
        int match = 1;
        int mismatch = -1;
        int gap = -1;
        int[][] matrix = algorithm(s1, s2, match, mismatch, gap);
        printMatrix(matrix, s1, s2);
        System.out.println("Score: " + matrix[s1.length()][s2.length()]);
        System.out.println("Alignment: ");
        return traceback(matrix, s1, s2, match, mismatch, gap);
    }

}

