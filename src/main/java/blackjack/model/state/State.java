package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.CardScore;
import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

import java.util.List;

public abstract class State {
    protected static final int BLACKJACK_NUMBER = 21;

    protected final OwnedCards ownedCards;

    public State(OwnedCards ownedCards) {
        this.ownedCards = ownedCards;
    }

    public abstract State draw(CardDeck cardDeck);

    public abstract boolean isFinished();

     public CardScore getScore() {
       return ownedCards.score();
    }

    public List<Card> getOwnedCards() {
         return ownedCards.getCards();
    }
}
