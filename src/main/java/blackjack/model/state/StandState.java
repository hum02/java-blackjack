package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

public class StandState extends State{
    public StandState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
