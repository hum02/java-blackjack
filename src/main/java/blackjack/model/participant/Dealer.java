package blackjack.model.participant;

import blackjack.model.CardScore;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.Name;
import blackjack.model.card.OwnedCards;
import blackjack.model.state.*;

import java.util.List;

public class Dealer extends Participant {
    private final Name name;
    private State currentState;

    public Dealer() {
        this.name = new Name("딜러");
        this.currentState = new InitialState(new OwnedCards());
    }

    public Dealer(State state) {
        this.name = new Name("딜러");
        this.currentState = state;
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnDealerDrawState();
        }
    }

    @Override
    public void changeToStand() {
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnStandState();
        }
    }

    public boolean isFinished() {
        return this.currentState.isFinished();
    }

    public boolean isBlackjack() {
        return this.currentState instanceof BlackjackState;
    }

    public boolean isBust() {
        return this.currentState instanceof BustState;
    }

    @Override
    public CardScore cardScore() {
        return currentState.getScore();
    }

    @Override
    public String getName() {
        return this.name.getName();
    }

    @Override
    public List<Card> getCards() {
        return this.currentState.getOwnedCards();
    }
}
