package Day1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Task1 {

    public static int LineCheckerTask1(String inputString) {
        Pattern pattern = Pattern.compile("\\d");
        StringBuilder reverseString = new StringBuilder(inputString);
        reverseString.reverse();
        Matcher matcher = pattern.matcher(inputString);
        Matcher matcherRev = pattern.matcher(reverseString);

        if (matcher.find() && matcherRev.find()) {
            int firstNumber = Integer.parseInt(matcher.group());
            int lastNumber = Integer.parseInt(matcherRev.group());
            return (10 * firstNumber) + lastNumber;
        } else
            return 0;

    }

}
