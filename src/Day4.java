import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay4.txt"));
        String line;
        int numberOfSubsets = 0;
        int notDisjoint = 0;
        while((line = reader.readLine()) != null){
            String[] sets = line.split(",");
            String set1Hi = sets[0].split("-")[1];
            String set1Lo = sets[0].split("-")[0];
            Set<Integer> set1 = new HashSet<>();
            for(int i = Integer.parseInt(set1Lo); i<=Integer.parseInt(set1Hi); i++){
                set1.add(i);
            }
            String set2Hi = sets[1].split("-")[1];
            String set2Lo = sets[1].split("-")[0];
            Set<Integer>set2 = new HashSet<>();
            for(int i = Integer.parseInt(set2Lo); i<=Integer.parseInt(set2Hi); i++){
                set2.add(i);
            }
            if(set1.containsAll(set2))
                numberOfSubsets++;
            else if (set2.containsAll(set1))
                numberOfSubsets++;

            if(!Collections.disjoint(set1, set2))
                notDisjoint++;
        }

        System.out.println(numberOfSubsets);
        System.out.println(notDisjoint);

    }
}
