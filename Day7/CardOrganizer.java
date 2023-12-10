package Day7;

import java.util.*;

public class CardOrganizer {


    private final List<String> FiveOfaKind;
    private final List<String> FourOfaKind;
    private final List<String> FullHouse;
    private final List<String> ThreeOfaKind;
    private final List<String> TwoPair;
    private final List<String> OnePair;
    private final List<String> HighCard;


    public CardOrganizer() {
        FiveOfaKind = new ArrayList<>();
        FourOfaKind = new ArrayList<>();
        FullHouse = new ArrayList<>();
        ThreeOfaKind = new ArrayList<>();
        TwoPair = new ArrayList<>();
        OnePair = new ArrayList<>();
        HighCard = new ArrayList<>();
    }


    public void OrderByStrength(String cardOrder) {
        HandComparator handComparator = new HandComparator(cardOrder);
        FiveOfaKind.sort(handComparator);
        FourOfaKind.sort(handComparator);
        FullHouse.sort(handComparator);
        ThreeOfaKind.sort(handComparator);
        TwoPair.sort(handComparator);
        OnePair.sort(handComparator);
        HighCard.sort(handComparator);
    }

    public static String switchCharacters(String input, char target, char replacement) {
        return input.chars()
                .mapToObj(c -> (char) (c == target ? replacement : c))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public String SwitchJokers(String input, char joker) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }


        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(charCountMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        if (charCountMap.containsKey(joker)) {
            for (Map.Entry<Character, Integer> it : entryList) {
                if (it.getKey() != joker) {
                    return switchCharacters(input, it.getKey(), joker);
                }
            }
        }
        return input;
    }

    public void OrderByTypeWithJokers(List<String> input) {

        for (String hand : input) {
                String handSwitched=SwitchJokers(hand,'J');

            putInCorrectList(hand, handSwitched);
        }

    }

    private void putInCorrectList(String hand, String handSwitched) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : handSwitched.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        List<Integer> valuesList = new ArrayList<>(charCountMap.values());
        valuesList.sort(Collections.reverseOrder());

        if (valuesList.size() == 1)
            FiveOfaKind.add(hand);

        else if (valuesList.size() == 2) {
            if (valuesList.get(0) == 4)
                FourOfaKind.add(hand);
            else
                FullHouse.add(hand);
        } else if (valuesList.size() == 3) {
            if (valuesList.get(0) == 3)
                ThreeOfaKind.add(hand);
            else
                TwoPair.add(hand);
        } else if (valuesList.size() == 4)
            OnePair.add(hand);

        else
            HighCard.add(hand);
    }

    public void OrderByType(List<String> input) {

        for (String hand : input) {
            putInCorrectList(hand, hand);
        }

    }

    public List<String> getSortedHands() {
        List<String> listOfSortedHands = new ArrayList<>();
        listOfSortedHands.addAll(HighCard);
        listOfSortedHands.addAll(OnePair);
        listOfSortedHands.addAll(TwoPair);
        listOfSortedHands.addAll(ThreeOfaKind);
        listOfSortedHands.addAll(FullHouse);
        listOfSortedHands.addAll(FourOfaKind);
        listOfSortedHands.addAll(FiveOfaKind);
        return listOfSortedHands;
    }


}
