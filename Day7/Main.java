package Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Utils.ReadFile.getRows;

public class Main {

    private static final String CARD_ORDER = "AKQJT98765432";
    private static final String CARD_ORDER_JOKERS = "AKQT98765432J";


    public static Map<String, Integer> getHandToBet(String filepath) {
        Map<String, Integer> handToBet = new HashMap<>();

        String[] rows = getRows(filepath);
        for (String row : rows) {
            String hand = row.split(" ")[0];
            Integer bet = Integer.parseInt(row.split(" ")[1]);
            handToBet.put(hand, bet);
        }
        return handToBet;
    }

    private static int getSum(int sum, Map<String, Integer> handToBet, CardOrganizer cardOrganizer, String cardOrderJokers) {
        cardOrganizer.OrderByStrength(cardOrderJokers);
        List<String> listOfSortedHands = cardOrganizer.getSortedHands();

        for (int i = 0; i < listOfSortedHands.size(); i++) {
            sum += (i + 1) * handToBet.get(listOfSortedHands.get(i));
        }

        return sum;
    }

    public static int task2() {

        int sum = 0;
        Map<String, Integer> handToBet = getHandToBet("Day7.txt");

        CardOrganizer cardOrganizer = new CardOrganizer();
        List<String> hands = new ArrayList<>(handToBet.keySet());
        cardOrganizer.OrderByTypeWithJokers(hands);
        return getSum(sum, handToBet, cardOrganizer, CARD_ORDER_JOKERS);
    }



    public static int task1() {
        int sum = 0;
        Map<String, Integer> handToBet = getHandToBet("Day7.txt");
        CardOrganizer cardOrganizer = new CardOrganizer();

        List<String> hands = new ArrayList<>(handToBet.keySet());
        cardOrganizer.OrderByType(hands);
        return getSum(sum, handToBet, cardOrganizer, CARD_ORDER);
    }


    public static void main(String[] args) {
        System.out.println(task1());
        System.out.println(task2());
    }
}