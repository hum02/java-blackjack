package blackjack.model.participant;

import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.card.HandCard;
import blackjack.model.state.InitialState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(name -> new Player(new Name(name), new InitialState()))
                .collect(Collectors.toList());
    }

    public void distributeFirstCards(CardDeck cardDeck) {
        if (players.stream().anyMatch(Player::isFinished)) {
            throw new IllegalStateException("첫 분배를 시작할 수 없는 상태입니다.");
        }
        for (Player player : players) {
            player.draw(cardDeck);
        }
    }

    public Map<String, List<Card>> firstDistributedCards() {
        Map<String, List<Card>> distributedCards = new HashMap<>();

        for (Player player : players) {
            distributedCards.put(player.getName(), player.firstDistributedCard());
        }
        return distributedCards;
    }

    public void hit(CardDeck cardDeck, int playerId) {
        Player player = getPlayerById(playerId);
        player.draw(cardDeck);
    }

    public void changeToStand(int playerId) {
        Player player = getPlayerById(playerId);
        if (!player.isFinished()) {
            player.changeToStand();
        }
    }

    public boolean isBlackjack(int playerId) {
        return getPlayerById(playerId).isBlackjack();
    }

    public boolean isPlayerFinished(int playerId) {
        Player player = getPlayerById(playerId);
        return player.isFinished();
    }

    public Map<String, List<Card>> getHandCardsById(int playerId) {
        return getPlayerById(playerId).handCards();
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public String getNameById(int playerId) {
        return getPlayerById(playerId).getName();
    }

    private Player getPlayerById(int playerId) {
        return players.stream().filter(p -> p.isEqualId(playerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
    }

    public WinningResult getWinningResultById(int playerId, CardScore cardScore) {
        return getPlayerById(playerId)
                .winningResult(cardScore);
    }

    public int getScoreById(int playerId) {
        Player player = getPlayerById(playerId);
        return player.cardScore().getScore();
    }
}
