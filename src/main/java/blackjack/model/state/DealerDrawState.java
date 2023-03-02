package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.Cards;
import blackjack.model.OwnedCards;

public class DealerDrawState extends State {

    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(Cards cardDeck) {
        ownedCards.add(cardDeck.pick());

        if (ownedCards.score().smallScore() > BLACKJACK_NUMBER && ownedCards.score().bigScore() > BLACKJACK_NUMBER) {
            return new BustState(ownedCards);
        }
        if (ownedCards.score().bigScore() <= 16) {
            return this;
        }
        return new StandState(ownedCards);
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }
}
