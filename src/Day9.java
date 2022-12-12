import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day9 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay9.txt"));
        String line;
        int headX = 0;
        int headY = 0;
        int tailX = 0;
        int tailY = 0;
        HashSet<String>positionsVisited = new HashSet<>();
        positionsVisited.add(0+","+0);
        while((line = reader.readLine()) != null) {
            int moveAmt = Integer.parseInt(line.split(" ")[1]);
            switch (line.split(" ")[0]) {
                case "L": {
                    for (int i = 0; i < moveAmt; i++) {
                        headX--;
                        if (Math.sqrt(Math.pow((headX - tailX), 2) + Math.pow(headY - tailY, 2)) > Math.sqrt(2)) {
                            tailX = headX + 1;
                            tailY = headY;
                            positionsVisited.add(tailX + "," + tailY);
                        }

                    }

                    break;
                }

                case "R": {
                    for (int i = 0; i < moveAmt; i++) {
                        headX++;
                        if (Math.sqrt(Math.pow((headX - tailX), 2) + Math.pow(headY - tailY, 2)) > Math.sqrt(2)) {
                            tailX = headX - 1;
                            tailY = headY;
                            positionsVisited.add(tailX + "," + tailY);
                        }

                    }
                    break;
                }

                case "U": {
                    for (int i = 0; i < moveAmt; i++) {
                        headY++;
                        if (Math.sqrt(Math.pow((headX - tailX), 2) + Math.pow(headY - tailY, 2)) > Math.sqrt(2)) {
                            tailY = headY - 1;
                            tailX = headX;
                            positionsVisited.add(tailX + "," + tailY);
                        }

                    }
                    break;
                }

                case "D": {
                    for (int i = 0; i < moveAmt; i++) {
                        headY--;
                        if (Math.sqrt(Math.pow((headX - tailX), 2) + Math.pow(headY - tailY, 2)) > Math.sqrt(2)) {
                            tailY = headY + 1;
                            tailX = headX;
                            positionsVisited.add(tailX + "," + tailY);
                        }

                    }
                    break;
                }

                default: {
                    break;
                }
            }

        }

        System.out.println(positionsVisited.size());
        ArrayList<Knot> knots = new ArrayList<>();
        headX = 0;
        headY = 0;
        for(int i = 0; i<9; i++)
            knots.add(new Knot());
        positionsVisited = new HashSet<>();
        positionsVisited.add(0+","+0);
        reader = new BufferedReader(new FileReader("inputDay9.txt"));

        while((line = reader.readLine()) != null) {
            int moveAmt = Integer.parseInt(line.split(" ")[1]);
            switch (line.split(" ")[0]) {
                case "L": {
                    for (int i = 0; i < moveAmt; i++) {
                        headX--;
                        //if the two knots are on the same y axis, but check to change the x value
                        if(headY == knots.get(0).y && Math.abs(headX - knots.get(0).x) > 1){
                            knots.get(0).x --;
                            //if we ge here then the numbers are diagonal. make a change to x and y if the distance between the two knots is greater than sqrt(2)
                        }else if(Math.sqrt(Math.pow(headX-knots.get(0).x, 2) + Math.pow(headY-knots.get(0).y, 2)) > Math.sqrt(2)){
                            //this means that the x value has pushed the number too far to the left, subtract 1 from x and add or subtract 1 from y based on the locaiton of the knots
                            knots.get(0).x--;
                            if(headY > knots.get(0).y){
                                knots.get(0).y++;
                            }else{
                                knots.get(0).y--;
                            }
                        }

                        for(int x = 1; x<knots.size(); x++){
                            if(Math.abs(knots.get(x-1).x-knots.get(x).x)>1||Math.abs(knots.get(x-1).y-knots.get(x).y)>1) {
                                if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                } else if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x < knots.get(x).x) {
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x > knots.get(x).x) {
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x--;
                                }
                            }

                        }



                        positionsVisited.add(knots.get(knots.size()-1).x+","+knots.get(knots.size()-1).y);

                    }

                    break;
                }

                case "R": {
                    for (int i = 0; i < moveAmt; i++) {
                        headX++;
                        //if the two knots are on the same y axis, but check to change the x value
                        if(headY == knots.get(0).y && Math.abs(headX - knots.get(0).x) > 1){
                            knots.get(0).x ++;
                            //if we ge here then the numbers are diagonal. make a change to x and y if the distance between the two knots is greater than sqrt(2)
                        }else if(Math.sqrt(Math.pow(headX-knots.get(0).x, 2) + Math.pow(headY-knots.get(0).y, 2)) > Math.sqrt(2)){
                            //this means that the x value has pushed the number too far to the left, subtract 1 from x and add or subtract 1 from y based on the locaiton of the knots
                            knots.get(0).x++;
                            if(headY > knots.get(0).y){
                                knots.get(0).y++;
                            }else{
                                knots.get(0).y--;
                            }
                        }

                        for(int x = 1; x<knots.size(); x++){
                            if(Math.abs(knots.get(x-1).x-knots.get(x).x)>1||Math.abs(knots.get(x-1).y-knots.get(x).y)>1) {
                                if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                } else if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x < knots.get(x).x) {
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x > knots.get(x).x) {
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x--;
                                }
                            }
                        }

                        positionsVisited.add(knots.get(knots.size()-1).x+","+knots.get(knots.size()-1).y);


                    }
                    break;
                }

                case "U": {

                    for (int i = 0; i < moveAmt; i++) {
                        headY++;
                        //if the two knots are on the same y axis, but check to change the x value
                        if(headX == knots.get(0).x && Math.abs(headY - knots.get(0).y) > 1){
                            knots.get(0).y++;
                            //if we ge here then the numbers are diagonal. make a change to x and y if the distance between the two knots is greater than sqrt(2)
                        }else if(Math.sqrt(Math.pow(headX-knots.get(0).x, 2) + Math.pow(headY-knots.get(0).y, 2)) > Math.sqrt(2)){
                            //this means that the x value has pushed the number too far to the left, subtract 1 from x and add or subtract 1 from y based on the locaiton of the knots
                            knots.get(0).y++;
                            if(headX > knots.get(0).x){
                                knots.get(0).x++;
                            }else{
                                knots.get(0).x--;
                            }
                        }


                        for(int x = 1; x<knots.size(); x++){
                            if(Math.abs(knots.get(x-1).x-knots.get(x).x)>1||Math.abs(knots.get(x-1).y-knots.get(x).y)>1) {
                                if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                } else if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x < knots.get(x).x) {
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x > knots.get(x).x) {
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x--;
                                }
                            }
                        }

                        positionsVisited.add(knots.get(knots.size()-1).x+","+knots.get(knots.size()-1).y);


                    }
                    break;
                }

                case "D": {
                    for (int i = 0; i < moveAmt; i++) {
                        headY--;
                        //if the two knots are on the same y axis, but check to change the x value
                        if(headX == knots.get(0).x && Math.abs(headY - knots.get(0).y) > 1){
                            knots.get(0).y--;
                            //if we ge here then the numbers are diagonal. make a change to x and y if the distance between the two knots is greater than sqrt(2)
                        }else if(Math.sqrt(Math.pow(headX-knots.get(0).x, 2) + Math.pow(headY-knots.get(0).y, 2)) > Math.sqrt(2)){
                            //this means that the x value has pushed the number too far to the left, subtract 1 from x and add or subtract 1 from y based on the locaiton of the knots
                            knots.get(0).y--;
                            if(headX > knots.get(0).x){
                                knots.get(0).x++;
                            }else{
                                knots.get(0).x--;
                            }
                        }

                        for(int x = 1; x<knots.size(); x++){
                            if(Math.abs(knots.get(x-1).x-knots.get(x).x)>1||Math.abs(knots.get(x-1).y-knots.get(x).y)>1) {
                                if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                } else if (knots.get(x - 1).x == knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x < knots.get(x).x) {
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).y == knots.get(x).y && knots.get(x - 1).x > knots.get(x).x) {
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y > knots.get(x).y) {
                                    knots.get(x).y++;
                                    knots.get(x).x--;
                                } else if (knots.get(x - 1).x > knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x++;
                                } else if (knots.get(x - 1).x < knots.get(x).x && knots.get(x - 1).y < knots.get(x).y) {
                                    knots.get(x).y--;
                                    knots.get(x).x--;
                                }
                            }
                        }

                        positionsVisited.add(knots.get(knots.size()-1).x+","+knots.get(knots.size()-1).y);


                    }
                    break;
                }

                default: {
                    break;
                }
            }

        }
        System.out.println(positionsVisited.size());
    }
}

class Knot{
    int x = 0;
    int y = 0;
}
