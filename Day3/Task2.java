package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static List<Integer> numFinder(String substring, int pos) {
        List<Integer> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(substring);
        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd = matcher.end();
            if ((indexEnd == pos || indexEnd == pos + 1) || (indexStart == pos - 1 || indexStart == pos || indexStart == pos + 1)) {
                int number = Integer.parseInt(matcher.group());
                result.add(number);
            }
        }
        return result;
    }

    public static boolean checkSubstring(String substring) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(substring);
        return matcher.find();
    }

    public static int checkSurroundings(String[] rows, int rowNum, int start, int end) {

        int starPos = start;
        if (start > 0) start--;
        if (end + 1 < rows.length) end++;

        List<Integer> listOfNumbers = new ArrayList<>();
        String checkMiddle = rows[rowNum].substring(start, end);

        if (checkSubstring(checkMiddle)) listOfNumbers.addAll(numFinder(rows[rowNum], starPos));
        String checkUp = null;
        String checkDown = null;

        if (rowNum - 1 >= 0) {
            checkUp = rows[rowNum - 1].substring(start, end);
            if (checkSubstring(checkUp)) listOfNumbers.addAll(numFinder(rows[rowNum - 1], starPos));
        }

        if (rowNum + 1 < rows.length) {
            checkDown = rows[rowNum + 1].substring(start, end);
            if (checkSubstring(checkDown)) listOfNumbers.addAll(numFinder(rows[rowNum + 1], starPos));
        }

        if (listOfNumbers.size() == 2)
            return listOfNumbers.get(0) * listOfNumbers.get(1);
        else
            return 0;


    }

    public static void main(String[] args) {

        String[] rows = null;
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Day3.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String input = stringBuilder.toString();
            rows = input.split("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < rows.length; i++) {
            Pattern pattern = Pattern.compile("\\*");
            Matcher matcher = pattern.matcher(rows[i]);

            while (matcher.find()) {
                int indexStart = matcher.start();
                int indexEnd = matcher.end();
                sum += checkSurroundings(rows, i, indexStart, indexEnd);

            }
        }
        System.out.println(sum);
    }
}
