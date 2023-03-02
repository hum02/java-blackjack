package blackjack.model;

import java.security.SecureRandom;

public class RandomCardPicker implements CardPicker{

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public Card pick() {

        CardSuit[] suits = CardSuit.values();
        CardNumber[] numbers = CardNumber.values();
        int suitIndex = SECURE_RANDOM.nextInt(suits.length);
        int numberIndex = SECURE_RANDOM.nextInt(numbers.length);

        return Card.of(suits[suitIndex], numbers[numberIndex]);
    }
}
