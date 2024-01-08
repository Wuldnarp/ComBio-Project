package sequence;

public class indel {

    public int[] getIndelCount(String[] allingment){
        int[] count = new int[2];
        for (int i = 0; i < allingment[0].length(); i++) {
            if (allingment[0].charAt(i) == '-' & allingment[1].charAt(i) != '-') {
                count[0]++;
            } else if (allingment[0].charAt(i) != '-' & allingment[1].charAt(i) == '-') {
                count[1]++;
            }
        }
        System.out.println("Insertions: " + count[0]);
        System.out.println("Deletions: " + count[1]);
        return count;
    }

    public boolean[] frameshift(String[] allingment){
        int[] indels = getIndelCount(allingment);
        boolean[] frameshift = new boolean[2];
        if (indels[0] % 3 == 0) {
            frameshift[0] = false;
        } else {
            frameshift[0] = true;
        }
        if (indels[1] % 3 == 0) {
            frameshift[1] = false;
        } else {
            frameshift[1] = true;
        }
        System.out.println("Frameshift Insertion: " + frameshift[0]);
        System.out.println("Frameshift Deletion: " + frameshift[1]);
        return frameshift;
    }
}
