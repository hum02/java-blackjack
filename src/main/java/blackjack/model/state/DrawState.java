package blackjack.model.state;

import blackjack.model.CardScore;
import blackjack.model.Cards;
import blackjack.model.OwnedCards;

public class DrawState extends State{

    public DrawState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(Cards cardDeck) {
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
    public CardScore getScore() {
        return super.getScore();
    }
}
