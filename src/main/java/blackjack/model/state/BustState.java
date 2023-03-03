package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class BustState extends State {

    public BustState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        throw new IllegalStateException("버스트 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isStand() {
        return false;
    }
}
