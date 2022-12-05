import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay5Stacks.txt"));
        String line;
        ArrayList<Stack<Character>> stacks = new ArrayList<>();
        for(int i = 0; i<9; i++){
            stacks.add(new Stack<>());
        }

        int curStack = 0;
        while((line = reader.readLine()) != null){
            if (line.equals(""))
                curStack++;
            else
                stacks.get(curStack).add(line.charAt(1));
        }

        for(int i = 0; i<9; i++){
            ArrayList<Character>invertedStack = new ArrayList<>();
            while(!stacks.get(i).empty())
                invertedStack.add(stacks.get(i).pop());

            for(Character c: invertedStack){
                stacks.get(i).push(c);
            }
        }

        reader = new BufferedReader(new FileReader("inputDay5.txt"));

        while((line = reader.readLine()) != null){
            Pattern p = Pattern.compile("[0-9]+");
            Matcher matcher = p.matcher(line);
            int moveAmount;
            int src;
            int dest;
            matcher.find();
            moveAmount = Integer.parseInt(matcher.group(0));
            matcher.find();
            src = Integer.parseInt(matcher.group(0));
            matcher.find();
            dest = Integer.parseInt(matcher.group(0));

            Stack<Character> intermediateStack = new Stack<>();
            for(int i = 0; i<moveAmount; i++)
                intermediateStack.push(stacks.get(src-1).pop());

            while(!intermediateStack.empty()){
                stacks.get(dest-1).push(intermediateStack.pop());
            }
        }

        for(Stack stack: stacks){
            System.out.print(stack.pop());
        }



    }
}
