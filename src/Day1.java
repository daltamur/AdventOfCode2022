import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int curChampion = 0;
        int curGreatest = 0;
        int curSum = 0;
        int curElf = 0;
        String line = null;
        ArrayList<Integer> elfVals = new ArrayList<>();
        while((line = reader.readLine()) != null){
            if(!line.equals("")) {
                int lineVal = Integer.parseInt(line);
                curSum+=lineVal;
            }else{
                if(curSum>curGreatest){
                    curGreatest = curSum;
                    curChampion = curElf;
                }
                elfVals.add(curSum);
                curSum = 0;
                curElf++;
            }
        }
        System.out.println("Top 3:");
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
    }
}
