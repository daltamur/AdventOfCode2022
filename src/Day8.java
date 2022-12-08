import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Day8 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay8.txt"));
        String line;
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        int i = 0;
        while((line = reader.readLine()) != null){
            grid.add(new ArrayList<>());
            for(char c: line.toCharArray())
                grid.get(i).add(Integer.parseInt(String.valueOf(c)));
            i++;
        }


        int numOfVisibleTrees = 0;
        for(i = 0; i<grid.size(); i++)
            for(int j = 0; j<grid.get(i).size(); j++){
                //check if on edge
                if(i == 0 || i == grid.size()-1 || j== 0 || j == grid.get(0).size()-1) {
                    numOfVisibleTrees++;
                    continue;
                }

                //check top neighbor
                boolean cont = false;
                int x = i - 1;
                while(x >= 0){
                    if(grid.get(x).get(j)>=grid.get(i).get(j)) {
                        cont = true;
                        break;
                    }

                    x--;
                }

                if(!cont) {
                    numOfVisibleTrees++;
                    continue;
                }

                //check bottom neigbor
                cont = false;
                x = i + 1;
                while(x <= grid.size()-1){
                    if(grid.get(x).get(j)>=grid.get(i).get(j)) {
                        cont = true;
                        break;
                    }

                    x++;
                }

                if(!cont) {
                    numOfVisibleTrees++;
                    continue;
                }


                //check left neighbor
                if(Collections.max(grid.get(i).subList(0, j))<grid.get(i).get(j)) {
                    numOfVisibleTrees++;
                    continue;
                }

                //check right neighbor
                if(Collections.max(grid.get(i).subList(j+1, grid.get(i).size()))<grid.get(i).get(j)) {
                    numOfVisibleTrees++;
                }

            }

        System.out.println(numOfVisibleTrees);
        int bestTreeScore = 1;

        for(i = 0; i<grid.size(); i++)
            for(int j = 0; j<grid.get(i).size(); j++){
                int curTreeScore = 1;
                //check if on edge
                if(i == 0 || i == grid.size()-1 || j== 0 || j == grid.get(0).size()-1) {
                    continue;
                }

                //check top neighbor
                int topAmount = 0;
                int x = i - 1;
                while(x >= 0){
                    if(grid.get(x).get(j)>=grid.get(i).get(j)) {
                        topAmount++;
                        break;
                    }
                    topAmount++;
                    x--;
                }
                curTreeScore = curTreeScore*topAmount;




                //check bottom neigbor
                int bottomScore = 0;
                x = i + 1;
                while(x <= grid.size()-1){
                    if(grid.get(x).get(j)>=grid.get(i).get(j)) {
                        bottomScore++;
                        break;
                    }

                    bottomScore++;
                    x++;
                }

                curTreeScore = curTreeScore*bottomScore;


                //check left neighbor
                int leftScore = 0;
                x = j - 1;
                while(x >= 0){
                    if(grid.get(i).get(x)>=grid.get(i).get(j)) {
                        leftScore++;
                        break;
                    }

                    leftScore++;
                    x--;
                }

                curTreeScore = curTreeScore*leftScore;
                //check right neighbor
                int rightScore = 0;
                x = j + 1;
                while(x <= grid.get(i).size()-1){
                    if(grid.get(i).get(x)>=grid.get(i).get(j)) {
                        rightScore++;
                        break;
                    }

                    rightScore++;
                    x++;
                }

                curTreeScore = curTreeScore*rightScore;
                if(curTreeScore > bestTreeScore)
                    bestTreeScore = curTreeScore;

            }

        System.out.println(bestTreeScore);





    }
}
