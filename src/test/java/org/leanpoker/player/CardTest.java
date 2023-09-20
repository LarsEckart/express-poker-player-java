package org.leanpoker.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void value_from_high_to_low() {
        assertEquals(14, new Card("A", Suit.SPADES).value());
        assertEquals(13, new Card("K", Suit.SPADES).value());
        assertEquals(12, new Card("Q", Suit.SPADES).value());
        assertEquals(11, new Card("J", Suit.SPADES).value());
        assertEquals(10, new Card("10", Suit.SPADES).value());
        assertEquals(9, new Card("9", Suit.SPADES).value());
        assertEquals(8, new Card("8", Suit.SPADES).value());
        assertEquals(7, new Card("7", Suit.SPADES).value());
        assertEquals(6, new Card("6", Suit.SPADES).value());
        assertEquals(5, new Card("5", Suit.SPADES).value());
        assertEquals(4, new Card("4", Suit.SPADES).value());
        assertEquals(3, new Card("3", Suit.SPADES).value());
        assertEquals(2, new Card("2", Suit.SPADES).value());
    }

    @Test
    void score_according_to_chen_formula() {
        assertEquals(10, new Card("A", Suit.SPADES).score());
        assertEquals(8, new Card("K", Suit.SPADES).score());
        assertEquals(7, new Card("Q", Suit.SPADES).score());
        assertEquals(6, new Card("J", Suit.SPADES).score());
        assertEquals(5, new Card("10", Suit.SPADES).score());
        assertEquals(4.5, new Card("9", Suit.SPADES).score());
        assertEquals(1.5, new Card("3", Suit.SPADES).score());
    }

}
