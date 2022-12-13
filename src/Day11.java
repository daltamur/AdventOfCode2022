import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Day11 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay11.txt"));
        String line;
        ArrayList<Monkey> monkeys = new ArrayList<>();
        ArrayList<BigInteger>items = null;
        String operator = null;
        String rhs = null;
        int divider = 0;
        int trueMonkey = 0;
        int falseMonkey = 0;
        while((line = reader.readLine())!=null){
            if(line.contains("Starting items")){
                items = new ArrayList<>();
                String[] itemsArr = line.split(": ")[1].split(", ");
                for(String item:itemsArr){
                    items.add(new BigInteger(item));
                }
            } else if (line.contains("Operation:")) {
                rhs = line.split(" = ")[1].split(" ")[2];
                operator = line.split(" = ")[1].split(" ")[1];
            } else if (line.contains("Test: ")) {
                divider = Integer.parseInt(line.split(": ")[1].split(" ")[2]);
            } else if (line.contains("true: ")) {
                trueMonkey = Integer.parseInt(line.split(": ")[1].split(" ")[3]);
            }else if(line.contains("false: ")){
                falseMonkey = Integer.parseInt(line.split(": ")[1].split(" ")[3]);
                monkeys.add(new Monkey(items, operator, rhs, divider, trueMonkey, falseMonkey));
            }
        }

        for(int i = 0; i<10000; i++) {
            for (Monkey curMonkey : monkeys) {
                for (BigInteger item : curMonkey.items) {
                    item = curMonkey.operation(item);
                    curMonkey.inspectionCount++;
                    item = item.mod(new BigInteger("9699690"));
                    monkeys.get(curMonkey.test(item)).addInt(item);
                }
                curMonkey.items.removeAll(curMonkey.items);
            }
            System.out.println(i);
        }

        ArrayList<Integer>inspections = new ArrayList<>();
        for(Monkey monkey:monkeys){
            inspections.add(monkey.inspectionCount);
        }

        BigInteger monkeyBusiness = new BigInteger("1");
        monkeyBusiness = monkeyBusiness.multiply(BigInteger.valueOf(Collections.max(inspections)));
        inspections.remove(Collections.max(inspections));
        monkeyBusiness = monkeyBusiness.multiply(BigInteger.valueOf(Collections.max(inspections)));
        inspections.remove(Collections.max(inspections));
        System.out.println("Level of monkey business is "+monkeyBusiness);





    }
}

class Monkey{
    ArrayList<BigInteger> items = new ArrayList<>();
    int trueMonkey;
    int falseMonkey;

    int divider;

    String operator;

    String rhs;

    int inspectionCount = 0;
    public Monkey(ArrayList<BigInteger>items, String operator, String rhs, int divider, int trueMonkey, int falseMonkey){
        this.items = items;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        this.divider = divider;
        this.rhs = rhs;
        this.operator = operator;
    }

    public void addInt(BigInteger x){
        items.add(x);
    }

    public int test(BigInteger curVal){
        if(curVal.mod(BigInteger.valueOf(divider)).equals(new BigInteger("0"))){
            return trueMonkey;
        }else {
            return falseMonkey;
        }
    }
    public BigInteger operation(BigInteger curVal){
        switch (operator){
            case "*":{
                if(rhs.equals("old"))
                    return curVal.multiply(curVal);
                else {
                    return curVal.multiply(BigInteger.valueOf(Long.parseLong(rhs)));
                }
            }

            case "+":{
                if(rhs.equals("old"))
                    return curVal.add(curVal);
                else {
                    return curVal.add(BigInteger.valueOf(Long.parseLong(rhs)));
                }
            }

            default: break;
        }
        return new BigInteger("0");
    }

}
