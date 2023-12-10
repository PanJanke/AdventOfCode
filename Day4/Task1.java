package Day4;

import java.util.Arrays;

import static Utils.ReadFile.getRows;

public class Task1 {

    public static int[] getNumbers(String input) {
        return Arrays.stream(input.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int checkCards(int[] winNumbersArray, int[] myNumbersArray) {
        long count = Arrays.stream(winNumbersArray)
                .filter(winNumber -> Arrays.stream(myNumbersArray).anyMatch(myNumber -> myNumber == winNumber))
                .count();

        return (int) Math.pow(2, count - 1);
    }

    public static void main(String[] args) {

        String[] rows = getRows("Day4.txt");
        int sum = 0;


        for (String row : rows) {
            String[] numbers = row.split("\\|");
            String winningNumbers = numbers[0].split("\\:")[1];
            String myNumbers = numbers[1];
            int[] winNumbersArray = getNumbers(winningNumbers);
            int[] myNumbersArray = getNumbers(myNumbers);
            sum += checkCards(winNumbersArray, myNumbersArray);
        }
        System.out.println(sum);
    }
}
