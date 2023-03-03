package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.Name;
import blackjack.model.card.OwnedCards;
import blackjack.model.state.*;

public class Player extends Participant {

    private final Name name;
    private State currentState;

    public Player(Name name) {
        this.name = name;
        this.currentState = new InitialState(new OwnedCards());
    }

    public Player(Name name, State state) {
        this.name = name;
        this.currentState = state;
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    @Override
    public void changeToStand() {
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnStandState();
        }
    }
}
