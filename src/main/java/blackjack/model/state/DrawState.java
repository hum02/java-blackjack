package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.card.OwnedCards;

public class DrawState extends State{

    public DrawState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        ownedCards.add(cardDeck.pick());
        if(ownedCards.score().smallScore() > BLACKJACK_NUMBER && ownedCards.score().bigScore() > BLACKJACK_NUMBER){
            return new BustState(ownedCards);
        }
        return this;
    }

    public State turnDealerDrawState(){
        return new DealerDrawState(ownedCards);
    }

    public State turnStandState(){
        return new StandState(ownedCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
