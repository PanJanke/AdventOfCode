package Day8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Day8.LCM.lcm_of_array_elements;
import static Utils.ReadFile.getRows;

public class Main {

    private final static String START = "AAA";
    private final static String END = "ZZZ";
    private final static String REGEX = "\\b[A-Z]+\\b";


    public static String getInstruction(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find())
            return matcher.group();
        else
            return null;

    }


    public static Map<String, Direction> prepareData(String[] rows) {
        Map<String, Direction> directionMap = new HashMap<>();
        for (int i = 2; i < rows.length; i++) {
            String direction = getInstruction(rows[i].split("=")[0], REGEX);
            String left = getInstruction(rows[i].split("=")[1].split(",")[0], REGEX);
            String right = getInstruction(rows[i].split("=")[1].split(",")[1], REGEX);
            directionMap.put(direction, new Direction(left, right));
        }
        return directionMap;

    }

    public static int getCount(String currentCode, char[] instructionList, Map<String, Direction> directionMap) {
        BigInteger counter = BigInteger.ZERO;
        int moduloVal = instructionList.length;
        while (!currentCode.equals(END)) {
            char instr = instructionList[counter.intValue() % moduloVal];
            if (instr == 'L') {
                currentCode = directionMap.get(currentCode).left();
            } else {
                currentCode = directionMap.get(currentCode).right();
            }
            counter = counter.add(BigInteger.ONE);
        }
        return counter.intValue();
    }

    public static int getCountForTask2(String currentCode, char[] instructionList, Map<String, Direction> directionMap) {
        BigInteger counter = BigInteger.ZERO;
        int moduloVal = instructionList.length;
        while (currentCode.charAt(2) != END.charAt(2)) {
            char instr = instructionList[counter.intValue() % moduloVal];
            if (instr == 'L') {
                currentCode = directionMap.get(currentCode).left();
            } else {
                currentCode = directionMap.get(currentCode).right();
            }
            counter = counter.add(BigInteger.ONE);
        }
        return counter.intValue();
    }

    public static void task1() {
        String[] rows = getRows("Files/Day8.txt");
        String instructions = rows[0];
        Map<String, Direction> directionMap = prepareData(rows);
        char[] instructionList = instructions.toCharArray();
        System.out.println(getCount(START, instructionList, directionMap));
    }

    public static void task2() {
        String[] rows = getRows("Files/Day8.txt");
        String instructions = rows[0];
        Map<String, Direction> directionMap = prepareData(rows);
        char[] instructionList = instructions.toCharArray();

        List<String> currentCodesList = new ArrayList<>();
        List<String> codesList = new ArrayList<>(directionMap.keySet());

        for (String s : codesList) {
            if (s.charAt(2) == START.charAt(2)) {
                currentCodesList.add(s);
            }
        }
        List<Integer> counterCollector = new ArrayList<>();
        for (String s : currentCodesList) {
            counterCollector.add(getCountForTask2(s, instructionList, directionMap));
        }
        int[] intArray = counterCollector.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(lcm_of_array_elements(intArray));

    }

    public static void main(String[] args) {
        task1();
        task2();

    }

}
