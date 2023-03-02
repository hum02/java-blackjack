package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.Cards;
import blackjack.model.OwnedCards;

public class BustState extends State {
    public BustState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }

    @Override
    public State draw(Cards cardDeck) {
        return this;
    }
}
