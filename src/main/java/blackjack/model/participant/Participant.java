package blackjack.model.participant;

import blackjack.model.CardScore;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.Name;
import blackjack.model.state.BlackjackState;
import blackjack.model.state.BustState;
import blackjack.model.state.State;

import java.util.List;

public abstract class Participant {
    protected Name name;
    protected State currentState;

    abstract public void play(CardDeck cardDeck);

    abstract public void changeToStand();

    abstract public CardScore cardScore();

    abstract public String getName();

    abstract public List<Card> getCards();
}
