package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

public class BustState extends State {
    public BustState(OwnedCards ownedCards) {
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
