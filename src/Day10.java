import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay10.txt"));
        String line;
        int x = 1;
        int curCycle = 0;
        String curRow = "";
        int spritePos = 0;
        ArrayList<Integer>cycleVals = new ArrayList<>();
        while((line = reader.readLine()) != null){
            if (!line.startsWith("noop")) {
                for (int i = 0; i < 2; i++) {
                    cycleVals.add(x);
                    if(curCycle>=spritePos && curCycle<spritePos+3){
                        curRow+="#";
                    }else {
                        curRow+=".";
                    }
                    curCycle++;
                    if ((curCycle)%40 == 0){
                        System.out.println(curRow);
                        curRow = "";
                        curCycle=0;
                    }
                }
                x += Integer.parseInt(line.split(" ")[1]);
                spritePos = x-1;
            } else{
                cycleVals.add(x);
                if(curCycle>=spritePos && curCycle<spritePos+3){
                    curRow+="#";
                }else {
                    curRow+=".";
                }
                curCycle++;
                if ((curCycle)%40 == 0){
                    System.out.println(curRow);
                    curRow = "";
                    curCycle=0;
                }
            }


        }

        //Find the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles.

        System.out.println((cycleVals.get(19)*20)+(cycleVals.get(59)*60)+(cycleVals.get(99)*100)+(cycleVals.get(139)*140)+(cycleVals.get(179)*180)+(cycleVals.get(219)*220));
    }
}
