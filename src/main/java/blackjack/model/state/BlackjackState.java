package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

public class BlackjackState extends State {
    public BlackjackState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        return this;
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
