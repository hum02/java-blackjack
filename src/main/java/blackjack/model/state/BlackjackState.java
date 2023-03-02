package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.Cards;
import blackjack.model.OwnedCards;

public class BlackjackState extends State{
    public BlackjackState(OwnedCards ownedCards) {
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
