package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiceThrowParser {
    private static final int redAmount = 12;
    private static final int greenAmount = 13;
    private static final int blueAmount = 14;

    private static boolean isPossible(String color, int count) {
        return switch (color) {
            case "red" -> count <= redAmount;
            case "green" -> count <= greenAmount;
            case "blue" -> count <= blueAmount;
            default -> false;
        };
    }

    private static boolean checkLine(String input) {

        String[] getRidOfGameNum = input.split(":");

        String[] throwsArray = getRidOfGameNum[1].split(";");

        for (String diceThrow : throwsArray) {
            String[] diceColors = diceThrow.trim().split(",");

            for (String diceColor : diceColors) {
                String[] parts = diceColor.trim().split(" ");

                if (parts.length == 2) {
                    int count = Integer.parseInt(parts[0]);
                    String color = parts[1].toLowerCase();

                    if (!isPossible(color, count)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        int gameCounter = 0;
        Scanner scanner = new Scanner(new File("Day2.txt"));

        while (scanner.hasNextLine()) {
            gameCounter++;
            if (checkLine(scanner.nextLine()))
                sum += gameCounter;
        }
        scanner.close();

        System.out.println(sum);
    }
}
