package blackjack;

import blackjack.model.*;
import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.state.InitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        List<Player> players = initializedPlayers(inputView);
        Dealer dealer = new Dealer(new InitialState(new OwnedCards()));
        CardDeck cardDeck = new CardDeck();

        //카드 나누기
        playCardDistribution(players, dealer, cardDeck);
        List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
        outputView.printDistributionMessage(names);

        //딜러카드, 플레이어 카드 출력
        String dealerName = dealer.getName();
        Card dealerCard = dealer.getCards().get(0);
        String dealerCards = cardUnit(dealerCard.getCardNumber(), dealerCard.getSuit());
        Map<String, List<String>> DealerCardsBundle = new HashMap<>();
        DealerCardsBundle.put(dealerName, List.of(dealerCards));

        outputView.printNameCardsBundle(DealerCardsBundle);
        outputView.printNameCardsBundle(playerCardsBundle(players));

        //HitOrStand
        for (Player player : players) {
            playHitOrStand(inputView, outputView, cardDeck, player);
        }

        //딜러 HitOrStand
        dealerHitOrStand(inputView, outputView, cardDeck, dealer);

        // 보유 카드. 숫자합 결과 출력

        // 최종승패 출력
    }

    private static void playCardDistribution(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

    private static void playHitOrStand(InputView inputView, OutputView outputView, CardDeck cardDeck, Player player) {
        while (!player.isFinished()) {
            boolean isHit = inputView.readHitOrStand(player.getName());
            if (isHit) {
                player.play(cardDeck);
            }
            if (!isHit) {
                player.changeToStand();
            }
            outputView.printNameCardsBundle(playerCardsBundle(List.of(player)));
        }
    }

    private static void dealerHitOrStand(InputView inputView, OutputView outputView, CardDeck cardDeck, Dealer dealer) {
        while (!dealer.isFinished()) {
            dealer.play(cardDeck);
            System.out.println("딜러가 1장을 더 받았습니다");
        }
    }

    private static List<Player> initializedPlayers(InputView inputView) {
        List<String> playerNames = inputView.readNames();

        return playerNames.stream()
                .map(name -> new Player(new Name(name), new InitialState(new OwnedCards())))
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> playerCardsBundle(List<Player> participants) {
        HashMap<String, List<String>> playerCards = new HashMap<>();
        for (Participant participant : participants) {
            String name = participant.getName();
            List<String> ownedCards = participantsCards(participant);
            playerCards.put(name, ownedCards);
        }
        return playerCards;
    }

    private static List<String> participantsCards(Participant participant) {
        List<String> ownedCards = participant.getCards()
                .stream()
                .map(card -> cardUnit(card.getCardNumber(), card.getSuit()))
                .collect(Collectors.toList());
        return ownedCards;
    }

    private static String cardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }
}
