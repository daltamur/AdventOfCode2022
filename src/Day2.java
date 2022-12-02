import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Day2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay2.txt"));
        String line;
        int score = 0;
        while((line = reader.readLine()) != null){
            String[] moves = line.split(" ");
            switch (moves[0]){
                case "A":{
                    if(Objects.equals(moves[1], "Y")){
                        score += 6+2;
                    } else if (Objects.equals(moves[1], "X")) {
                        score += 3+1;
                    }else{
                        score += 3;
                    }
                    break;
                }

                case "B":{
                    if(Objects.equals(moves[1], "Z")){
                        score += 6+3;
                    } else if (Objects.equals(moves[1], "Y")) {
                        score += 3+2;
                    }else{
                        score += 1;
                    }
                    break;
                }

                case "C":{
                    if(Objects.equals(moves[1], "X")){
                        score += 6+1;
                    } else if (Objects.equals(moves[1], "Z")) {
                        score += 3+3;
                    }else{
                        score += 2;
                    }
                    break;
                }

                default:{
                    break;
                }
            }

        }
        System.out.println(score);

        int stratScore = 0;
        reader = new BufferedReader(new FileReader("inputDay2.txt"));
        while((line = reader.readLine()) != null){
            String[] moves = line.split(" ");
            switch (moves[0]){
                case "A":{
                    if(Objects.equals(moves[1], "Y")){
                        stratScore += 3+1;
                    } else if (Objects.equals(moves[1], "X")) {
                        stratScore += 3;
                    }else{
                        stratScore += 6+2;
                    }
                    break;
                }

                case "B":{
                    if(Objects.equals(moves[1], "Z")){
                        stratScore += 6+3;
                    } else if (Objects.equals(moves[1], "Y")) {
                        stratScore += 3+2;
                    }else{
                        stratScore += 1;
                    }
                    break;
                }

                case "C":{
                    if(Objects.equals(moves[1], "X")){
                        stratScore += 2;
                    } else if (Objects.equals(moves[1], "Z")) {
                        stratScore += 6+1;
                    }else{
                        stratScore += 3+3;
                    }
                    break;
                }

                default:{
                    break;
                }
            }

        }
        System.out.println(stratScore);
    }

}
