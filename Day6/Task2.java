package Day6;

import java.math.BigInteger;

import static Utils.ReadFile.getRows;

public class Task2 {

    public static int PossibilityOfWinningCounter(BigInteger time, BigInteger distance) {
        BigInteger prevDistance = BigInteger.valueOf(-1);
        int possibilityCounter = 0;

        for (BigInteger incOfSpeed = BigInteger.ZERO; incOfSpeed.compareTo(time) < 0; incOfSpeed = incOfSpeed.add(BigInteger.ONE)) {
            BigInteger newDistance = incOfSpeed.multiply(time.subtract(incOfSpeed));

            if (newDistance.compareTo(prevDistance) < 0 && newDistance.compareTo(distance) < 0)
                break;

            prevDistance = newDistance;

            if (newDistance.compareTo(distance) > 0)
                possibilityCounter++;
        }

        return possibilityCounter;
    }

    public static BigInteger getNumber(String input) {
        String mergedString = input.replaceAll("\\s+", "");
        return new BigInteger(mergedString);

    }

    public static void main(String[] args) {
        String[] rows = getRows("Day6.txt");
        System.out.println(rows[0]);
        System.out.println(rows[1]);

        BigInteger Time = getNumber(rows[0].split("\\:")[1]);
        BigInteger Distance = getNumber(rows[1].split("\\:")[1]);

        System.out.println(PossibilityOfWinningCounter(Time, Distance));
    }


}
