package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiceThrowParserTask2 {
    private static  int redAmount = 0;
    private static  int greenAmount = 0;
    private static  int blueAmount = 0;

    private static void compareToMax(String color, int count) {
        switch (color) {
            case "red" -> {
                if (count > redAmount)
                    redAmount = count;
            }
            case "green" -> {
                if (count > greenAmount)
                    greenAmount = count;
            }
            case "blue" -> {
                if (count > blueAmount)
                    blueAmount = count;
            }
        }
    }

    private static void checkLine(String input) {

            redAmount=0;
            blueAmount=0;
            greenAmount=0;

        String[] getRidOfGameNum = input.split(":");

        String[] throwsArray = getRidOfGameNum[1].split(";");

        for (String diceThrow : throwsArray) {
            String[] diceColors = diceThrow.trim().split(",");

            for (String diceColor : diceColors) {
                String[] parts = diceColor.trim().split(" ");

                if (parts.length == 2) {
                    int count = Integer.parseInt(parts[0]);
                    String color = parts[1].toLowerCase();

                    compareToMax(color, count);

                }
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        Scanner scanner = new Scanner(new File("Day2.txt"));

        while (scanner.hasNextLine()) {
            checkLine(scanner.nextLine());
            sum+=redAmount*greenAmount*blueAmount;
        }
        scanner.close();

        System.out.println(sum);


    }

}
