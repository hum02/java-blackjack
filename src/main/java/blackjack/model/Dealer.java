package blackjack.model;

import blackjack.model.state.DrawState;
import blackjack.model.state.InitialState;
import blackjack.model.state.State;

import java.util.List;

public class Dealer {
    private final Name name;
    private State currentState;

    public Dealer() {
        this.name = new Name("딜러");
        this.currentState = new InitialState(new OwnedCards());
    }

    public void play(Cards cardDeck) {
        currentState.draw(cardDeck);
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnDealerDrawState();
        }
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getOwnedCards() {
        return currentState.getOwnedCards();
    }
}
