package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.HandCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.controller.GameController.DEALER_NAME;

public class Dealer extends Participant {

    public static final int DEALER_HIT_NUMBER = 16;
    public static final int FIRST_CARD = 0;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public Dealer(HandCard handCard) {
        super(new Name(DEALER_NAME), handCard, 0);
    }

    @Override
    public Map<String, List<Card>> firstDistributedCard() {
        if (handcard.isEmpty()) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        Map<String, List<Card>> firstCardUnits = new HashMap<>();
        firstCardUnits.put(getName(), List.of(handcard.getCards().get(FIRST_CARD)));
        return firstCardUnits;
    }

    @Override
    public boolean isFinished() {
        return (isBlackjack() || isBust() || isStand());
    }

    public Map<String, WinningResult> participantWinningResults(Players players) {
        Map<String, WinningResult> totalResults = new HashMap<>();
        WinningResult totalBettingProfit = new WinningResult();

        for (int playerId : players.getPlayerIds()) {
            WinningResult playerResult = players.getWinningResultById(playerId, this.cardScore());
            totalResults.put(players.getNameById(playerId), playerResult);
            totalBettingProfit = totalBettingProfit.merge(playerResult);
        }
        totalResults.put(this.getName(), new WinningResult(totalBettingProfit.getLose(), totalBettingProfit.getDraw(), totalBettingProfit.getWin(), -totalBettingProfit.getBetting()));
        return totalResults;
    }

    @Override
    protected ResultState resultState() {
        if (!isStand()) {
            throw new IllegalStateException("아직 카드를 더 뽑아야 하는 상태입니다. 결과를 계산할 수 없습니다.");
        }
        if (this.isBlackjack()) {
            return ResultState.BLACKJACK;
        }
        if (this.isBust()) {
            return ResultState.DEALER_BUST;
        }
        return ResultState.STAND;
    }

    private boolean isStand() {
        return handcard.isScoreOver(DEALER_HIT_NUMBER);
    }
}
