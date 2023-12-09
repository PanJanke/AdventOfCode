package Day6;

import java.util.Arrays;

import static Utils.ReadFile.getRows;

public class Task1 {

    public static int PossibilityOfWinningCounter(int time, int distance) {
        int prevDistance = -1;
        int possibilityCounter = 0;
        for (int incOfSpeed = 0; incOfSpeed < time; incOfSpeed++) {
            int newDistance = incOfSpeed * (time - incOfSpeed);

            if (newDistance < prevDistance && newDistance < distance)
                break;

            prevDistance = newDistance;

            if (newDistance > distance)
                possibilityCounter++;
        }
        return possibilityCounter;
    }

    public static int[] getNumbers(String input) {
        return Arrays.stream(input.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) {
        String[] rows = getRows("Day6.txt");
        System.out.println(rows[0]);
        System.out.println(rows[1]);

        int[] Time = getNumbers(rows[0].split("\\:")[1]);
        int[] Distance = getNumbers(rows[1].split("\\:")[1]);

        int result = 1;
        for (int i = 0; i < Time.length; i++) {
            System.out.println(PossibilityOfWinningCounter(Time[i], Distance[i]));
            result *= PossibilityOfWinningCounter(Time[i], Distance[i]);
        }
        System.out.println(result);

    }
}
