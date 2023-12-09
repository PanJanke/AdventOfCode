package Day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.ReadFile.getRows;

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

        String[] rows = getRows("Day3.txt");
        int sum = 0;

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
