package blackjack.model;

import blackjack.model.state.*;

import java.util.List;

public class Player {

    private final Name name;
    private State currentState;

    public Player(Name name) {
        this.name = name;
        this.currentState = new InitialState(new OwnedCards());
    }

    public void play(Cards cards) {
        this.currentState = currentState.draw(cards);
    }

    public boolean isBlackjack() {
        return currentState instanceof BlackjackState;
    }

    public void changeToStand() {
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnStandState();
        }
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getOwnedCards() {
        return currentState.getOwnedCards();
    }

    public boolean isFinished() {
        return !(this.currentState instanceof DrawState);
    }
}
