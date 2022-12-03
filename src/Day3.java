import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("day3.txt"));
        String line;
        int total = 0;
        ArrayList<String> words = new ArrayList<>();

        while((line = reader.readLine()) != null){
            words.add(line);
            int mp = line.length()/2;
            String firstC = line.substring(0, mp);
            String secondC = line.substring(mp);
            ArrayList<Character>sameChars = new ArrayList<>();
            for(int i = 0; i<firstC.length();i++){
                for(int j = 0 ; j<firstC.length();j++){
                    if(firstC.charAt(i) == secondC.charAt(j) && !sameChars.contains(firstC.charAt(i))){
                        sameChars.add(firstC.charAt(i));
                    }
                }
            }
            String lowerCase = "abcdefghijklmnopqrstuvwxyz";
            String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            for(Character c: sameChars){
                if(lowerCase.contains(String.valueOf(c))){
                    for(int i = 0; i<lowerCase.toCharArray().length; i++) {
                        if (c == lowerCase.toCharArray()[i]){
                            total+=i+1;
                            break;
                        }
                    }

                } else if (upperCase.contains(String.valueOf(c))) {
                    for(int i = 0; i<upperCase.toCharArray().length; i++) {
                        if (c == upperCase.toCharArray()[i]){
                            total+=i+1+26;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(total);
        total = 0;

        for(int i = 0; i<words.size(); i+=3){
            Set<Character> chars = new HashSet<>();
            ArrayList<Character> charsInit = new ArrayList<Character>();
            for (char c : words.get(i).toCharArray()) {
                charsInit.add(c);
            }
            chars.addAll(charsInit);
            for(int j = i+1; j<i+3;j++){
                ArrayList<Character> charsCur = new ArrayList<Character>();
                for (char c : words.get(j).toCharArray()) {
                    charsCur.add(c);
                }
                Set<Character> tempSet = new HashSet<>(charsCur);
                tempSet.retainAll(chars);
                chars = tempSet;
            }

            String lowerCase = "abcdefghijklmnopqrstuvwxyz";
            String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Character curVal = (Character) chars.toArray()[0];
            if(lowerCase.contains(String.valueOf(curVal))){
                for(int x = 0; x<lowerCase.toCharArray().length; x++) {
                    if (curVal == lowerCase.toCharArray()[x]){
                        total+=x+1;
                        break;
                    }
                }

            } else if (upperCase.contains(String.valueOf(curVal))) {
                for(int z = 0; z<upperCase.toCharArray().length; z++) {
                    if (curVal == upperCase.toCharArray()[z]){
                        total+=z+1+26;
                        break;
                    }
                }
            }

        }

        System.out.println(total);
    }
}
