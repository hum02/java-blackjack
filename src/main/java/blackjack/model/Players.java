package blackjack.model;

import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void play(Cards cardDeck) {
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

}
