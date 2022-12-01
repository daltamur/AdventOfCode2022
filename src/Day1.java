import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay1.txt"));
        int curGreatest = 0;
        int curSum = 0;
        String line;
        ArrayList<Integer> elfVals = new ArrayList<>();
        while((line = reader.readLine()) != null){
            if(!line.equals("")) {
                int lineVal = Integer.parseInt(line);
                curSum+=lineVal;
            }else{
                elfVals.add(curSum);
                curSum = 0;
            }
        }
        System.out.println("Highest Val: ");
        System.out.println(Collections.max(elfVals));

        System.out.println();

        System.out.println("Top 3:");
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
        System.out.println(Collections.max(elfVals));
        elfVals.remove(Collections.max(elfVals));
    }
}
