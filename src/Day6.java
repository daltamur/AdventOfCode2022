import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay6.txt"));
        String line;
        line = reader.readLine();
        ArrayList<Character> curChars = new ArrayList<>();
        for(int i = 0; i<line.length(); i++){
            if(curChars.size() != 4)
                curChars.add(line.charAt(i));
            else{
                if(new HashSet<>(curChars).size() != curChars.size()){
                    curChars.remove(0);
                    curChars.add(line.charAt(i));
                }else{
                    System.out.println(i);
                    break;
                }
            }
        }

        curChars.removeAll(curChars);
        for(int i = 0; i<line.length(); i++){
            if(curChars.size() != 14)
                curChars.add(line.charAt(i));
            else{
                if(new HashSet<>(curChars).size() != curChars.size()){
                    curChars.remove(0);
                    curChars.add(line.charAt(i));
                }else{
                    System.out.println(i);
                    break;
                }
            }
        }


    }
}
