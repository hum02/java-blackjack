package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.Name;
import blackjack.model.state.State;

import java.util.List;

public abstract class Participant {
    protected Name name;
    protected State currentState;

    abstract public void play(CardDeck cardDeck);

    abstract public void changeToStand();

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return currentState.getOwnedCards();
    }
}
