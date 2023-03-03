package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardDeckTest {

    @Test
    @DisplayName("카드덱에서 제일 위의 카드가 pick되어야 한다.")
    void pick_card() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.DIAMOND, CardNumber.EIGHT);
        List<Card> inputCard = List.of(card1, card2);
        CardDeck cardDeck = new CardDeck(inputCard);

        // when
        Card pickedCard = cardDeck.pick();

        //then
        Assertions.assertThat(pickedCard).isEqualTo(card2);
    }



}