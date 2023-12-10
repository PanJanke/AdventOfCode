package Day7;

import java.util.Comparator;


public class HandComparator implements Comparator<String> {

    private static String CARD_ORDER;

    public HandComparator(String cardOrder) {
        CARD_ORDER = cardOrder;
    }

    private int compareCards(String card1, String card2) {
        return Integer.compare(CARD_ORDER.indexOf(card2), CARD_ORDER.indexOf(card1));
    }

    @Override
    public int compare(String hand1, String hand2) {

        for (int i = 0; i < hand1.length(); i++) {
            String card1 = Character.toString(hand1.charAt(i));
            String card2 = Character.toString(hand2.charAt(i));

            int compareResult = compareCards(card1, card2);
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return 0;
    }

}
