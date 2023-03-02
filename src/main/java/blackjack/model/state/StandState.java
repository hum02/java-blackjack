package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.Cards;
import blackjack.model.OwnedCards;

public class StandState extends State{
    public StandState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(Cards cardDeck) {
        return this;
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }
}
