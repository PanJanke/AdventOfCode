package Day1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Task2 {

    private static int convertToDigit(String word) {
        return switch (word.toLowerCase()) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(word);
        };
    }

    public static String replaceSpelledOutNumbers(String input) {
        Pattern pattern = Pattern.compile("one|two|three|four|five|six|seven|eight|nine", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String matched = matcher.group();
            int numericValue = convertToDigit(matched);
            matcher.appendReplacement(result, Integer.toString(numericValue) + matched.charAt(matched.length() - 1));
        }
        matcher.appendTail(result);

        return result.toString();
    }


    /*
    Turns out that twone -> 21 (took me almost 2 hours to find that case)
    To solve that problem replaceSpelledOutNumbers method changes "twone" -> "2one" (number + last char)
    You can also work with the look ahead option in regex*/
    public static int LineCheckerTask2(String inputString) {

        inputString = replaceSpelledOutNumbers(inputString);
        Pattern pattern = Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");
        Matcher matcher = pattern.matcher(inputString);

        Integer firstNumber = null;
        Integer lastNumber = null;

        while (matcher.find()) {
            if (firstNumber == null) {
                firstNumber = convertToDigit(matcher.group());
            }
            lastNumber = convertToDigit(matcher.group());
        }


        if (firstNumber != null && lastNumber != null)
            return (10 * firstNumber) + lastNumber;

        else if (lastNumber != null)
            return (10 * lastNumber) + lastNumber;

        else if (firstNumber != null)
            return (10 * firstNumber) + firstNumber;

        else
            return 0;
    }


}
