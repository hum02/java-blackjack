package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
            return instance;
        }
        return instance;
    }

    public void printDistributionMessage(List<String> players){
        String names = String.join(",", players);
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printNameCardsBundle(Map<String, List<String>> playerOwnedCards){
        for(Map.Entry<String, List<String>> entry:playerOwnedCards.entrySet()){
            String name = entry.getKey();
            String cards = String.join(",", entry.getValue());
            System.out.println(name + ": " + cards);
        }
    }

}
