package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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

        String[] rows = null;
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Day4.txt"))) {
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
            String[] numbers = rows[i].split("\\|");
            String winningNumbers = numbers[0].split("\\:")[1];
            String myNumbers = numbers[1];
            int[] winNumbersArray = getNumbers(winningNumbers);
            int[] myNumbersArray = getNumbers(myNumbers);
            sum += checkCards(winNumbersArray, myNumbersArray);
        }
        System.out.println(sum);
    }
}
