package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

public class DealerDrawState extends State {

    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        ownedCards.add(cardDeck.pick());

        if (ownedCards.score().smallScore() > BLACKJACK_NUMBER && ownedCards.score().bigScore() > BLACKJACK_NUMBER) {
            return new BustState(ownedCards);
        }
        if (!isFinished()) {
            return this;
        }
        return new StandState(ownedCards);
    }

    @Override
    public boolean isFinished() {
        return ownedCards.score().bigScore() > 16;
    }
}
