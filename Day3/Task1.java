package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    public static boolean checkSubstring(String substring) {
        Pattern pattern = Pattern.compile("[^\\d.]");
        Matcher matcher = pattern.matcher(substring);
        return matcher.find();
    }

    public static boolean checkSurroundings(String[] rows, int rowNum, int start, int end) {

        if (start > 0)
            start--;
        if (end + 1 < rows.length)
            end++;


        String checkMiddle = rows[rowNum].substring(start, end);
        if (checkSubstring(checkMiddle))
            return true;

        if (rowNum - 1 >= 0) {
            String checkUp = rows[rowNum - 1].substring(start, end);
            if (checkSubstring(checkUp))
                return true;
        }

        if (rowNum + 1 < rows.length) {
            String checkDown = rows[rowNum + 1].substring(start, end);
            return checkSubstring(checkDown);
        }

        return false;
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
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(rows[i]);

            while (matcher.find()) {
                String match = matcher.group();
                int number = Integer.parseInt(match);
                int indexStart = matcher.start();
                int indexEnd = matcher.end();
                boolean flag = checkSurroundings(rows, i, indexStart, indexEnd);
                if (flag)
                    sum += number;
            }
        }
        System.out.println(sum);
    }
}
