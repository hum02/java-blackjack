package blackjack;

import blackjack.model.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        List<Player> players = initializePlayers(inputView);
        Dealer dealer = new Dealer();
        Cards cardDeck = new Cards();

        //카드 나누기
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
        OutputView outputView = OutputView.getInstance();
        outputView.printCardDistribution(players.stream().map(Player::getName).collect(Collectors.toList()));

        //딜러카드 하나만 출력
        Map<String, List<String>> dealerOwnedCards = new HashMap<>();
        String dealerName = dealer.getName();
        Card dealerCard = dealer.getOwnedCards().get(0);
        String dealerCards = cardUnit(dealerCard.getCardNumber(), dealerCard.getSuit());
        dealerOwnedCards.put(dealerName, List.of(dealerCards));
        outputView.printPlayerOwnedCards(dealerOwnedCards);
        outputView.printPlayerOwnedCards(playerOwnedCards(players));

        //HitOrStand
        for (Player player : players) {
            while (!player.isFinished()) {
                boolean isHit = inputView.readHitOrStand(player.getName());
                if (isHit) {
                    player.play(cardDeck);
                }
                if (!isHit) {
                    player.changeToStand();
                }
                outputView.printPlayerOwnedCards(playerOwnedCards(List.of(player)));
            }
        }

        //딜러 HitOrStand

        // 보유 카드. 숫자합 결과 출력

        // 최종승패 출력
    }

    private static List<Player> initializePlayers(InputView inputView) {
        List<String> playerNames = inputView.readNames();

        return playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> playerOwnedCards(List<Player> players) {
        HashMap<String, List<String>> playerCards = new HashMap<>();
        for (Player player : players) {
            String name = player.getName();
            List<String> ownedCards = player.getOwnedCards()
                    .stream()
                    .map(card -> cardUnit(card.getCardNumber(), card.getSuit()))
                    .collect(Collectors.toList());
            playerCards.put(name, ownedCards);
        }
        return playerCards;
    }

    private static String cardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }
}
