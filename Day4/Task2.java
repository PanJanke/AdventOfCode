package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task2 {


    public static int[] getNumbers(String input) {
        return Arrays.stream(input.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int checkCards(int[] winNumbersArray, int[] myNumbersArray) {
        return (int) Arrays.stream(winNumbersArray)
                .filter(winNumber -> Arrays.stream(myNumbersArray).anyMatch(myNumber -> myNumber == winNumber))
                .count();
    }

    public static void main(String[] args) {

        Map<Integer, Integer> numOfCopiesOfEachCard = new HashMap<>();
        String[] rows = null;

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

        for (int i = 0; i < rows.length; i++)
            numOfCopiesOfEachCard.put(i, 1);

        for (int i = 0; i < rows.length; i++) {
            String[] numbers = rows[i].split("\\|");
            String winningNumbers = numbers[0].split("\\:")[1];
            String myNumbers = numbers[1];
            int[] winNumbersArray = getNumbers(winningNumbers);
            int[] myNumbersArray = getNumbers(myNumbers);

            for (int j = 0; j < numOfCopiesOfEachCard.get(i); j++) {
                int tempCount = checkCards(winNumbersArray, myNumbersArray);

                for (int k = 1; k < tempCount + 1; k++) {
                    int baseVaule = numOfCopiesOfEachCard.get(i + k);
                    numOfCopiesOfEachCard.put(i + k, baseVaule + 1);
                }

            }

        }

        int sum = numOfCopiesOfEachCard.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
