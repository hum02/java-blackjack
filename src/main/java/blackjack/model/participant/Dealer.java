package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.Name;
import blackjack.model.card.OwnedCards;
import blackjack.model.state.DrawState;
import blackjack.model.state.InitialState;
import blackjack.model.state.State;

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
        currentState.draw(cardDeck);
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
}
