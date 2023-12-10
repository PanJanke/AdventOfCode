package Day8;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.ReadFile.getRows;

public class Main {

    private final static String start = "AAA";
    private final static String end = "ZZZ";

    public static String getInstruction(String input) {
        Pattern pattern = Pattern.compile("\\b[A-Z]+\\b");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find())
            return matcher.group();
        else
            return null;

    }

    public static void main(String[] args) {
        String[] rows = getRows("Files/Day8.txt");
        String instructions = rows[0];


        char[] instructionList = instructions.toCharArray();
        Map<String, Direction> directionMap = new HashMap<>();
        for (int i = 2; i < rows.length; i++) {
            String direction = getInstruction(rows[i].split("=")[0]);
            String left = getInstruction(rows[i].split("=")[1].split(",")[0]);
            String right = getInstruction(rows[i].split("=")[1].split(",")[1]);
            directionMap.put(direction, new Direction(left, right));
        }

        String currentCode = start;
        BigInteger counter = BigInteger.ZERO;
        int moduloVal = instructionList.length;
        while (!currentCode.equals(end)) {
            char instr = instructionList[counter.intValue() % moduloVal];
            if (instr == 'L') {
                currentCode = directionMap.get(currentCode).getLeft();
            } else {
                currentCode = directionMap.get(currentCode).getRight();
            }
            counter = counter.add(BigInteger.ONE);
        }
        System.out.println(counter);
    }
}
