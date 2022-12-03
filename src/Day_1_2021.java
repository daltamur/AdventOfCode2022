import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_1_2021 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2021day1.txt"));
        String line;
        int increased = 0;
        ArrayList<Integer>values = new ArrayList<>();
        while((line = reader.readLine()) != null){
            values.add(Integer.parseInt(line));
        }

        int curVal = values.get(0)+values.get(1)+values.get(2);
        for(int i = 1; i<=values.size()-3; i++){
            int nextVal = values.get(i)+values.get(i+1)+values.get(i+2);
            if(nextVal>curVal){
                increased++;
            }
            curVal = nextVal;
        }

        System.out.println(increased);
    }
}
