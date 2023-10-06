package org.leanpoker.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.leanpoker.player.TestPlayerStates.buildPlayerWithOffSuitCards;
import static org.leanpoker.player.TestPlayerStates.buildPlayerWithSuitedCards;

class PlayerStateTest {

    @Test
    void has_pocket_pair() {
        assertTrue(buildPlayerWithOffSuitCards("4", "4").hasPocketPair());
        assertTrue(buildPlayerWithSuitedCards("A", "A").hasPocketPair());
    }

    @Test
    void has_no_pocket_pair() {
        assertFalse(buildPlayerWithOffSuitCards("A", "9").hasPocketPair());
        assertFalse(buildPlayerWithSuitedCards("3", "7").hasPocketPair());
    }

    @Test
    void has_suited_pair() {
        assertTrue(buildPlayerWithSuitedCards("A", "9").hasPocketSuited());
    }

    @Test
    void has_unsuited_pair() {
        assertFalse(buildPlayerWithOffSuitCards("A", "A").hasPocketSuited());
    }

    @Test
    void detect_highest_value_card() {
        assertEquals(14, buildPlayerWithOffSuitCards("A", "9").highestPocketValue());
        assertEquals(13, buildPlayerWithOffSuitCards("9", "K").highestPocketValue());
        assertEquals(9, buildPlayerWithOffSuitCards("3", "9").highestPocketValue());
        assertEquals(7, buildPlayerWithOffSuitCards("7", "2").highestPocketValue());
        assertEquals(14, buildPlayerWithOffSuitCards("A", "K").highestPocketValue());
        assertEquals(12, buildPlayerWithOffSuitCards("J", "Q").highestPocketValue());
        assertEquals(11, buildPlayerWithOffSuitCards("J", "J").highestPocketValue());
        assertEquals(4, buildPlayerWithOffSuitCards("4", "4").highestPocketValue());
    }

    @Nested
    class Scoring {
        @Test
        void calculates_score_as_score_of_high_card_for_connectors() {
            assertEquals(10, buildPlayerWithOffSuitCards("A", "K").score());
            assertEquals(8, buildPlayerWithOffSuitCards("K", "Q").score());
            assertEquals(8, buildPlayerWithOffSuitCards("Q", "K").score());
        }

        @Test
        void double_score_for_pairs() {
            assertEquals(16, buildPlayerWithOffSuitCards("K", "K").score());
            assertEquals(5, buildPlayerWithOffSuitCards("2", "2").score());
        }

        @Test
        void when_suited_adds_two_to_score() {
            assertEquals(12, buildPlayerWithSuitedCards("A", "K").score());
            assertEquals(10, buildPlayerWithSuitedCards("K", "Q").score());
            assertEquals(10, buildPlayerWithSuitedCards("Q", "K").score());
        }

        @Test
        void subtracts_when_gap() {
            assertEquals(9, buildPlayerWithOffSuitCards("Q", "A").score());
            assertEquals(8, buildPlayerWithOffSuitCards("A", "J").score());
            assertEquals(6, buildPlayerWithOffSuitCards("A", "10").score());
            assertEquals(5, buildPlayerWithOffSuitCards("A", "9").score());
            assertEquals(5, buildPlayerWithOffSuitCards("A", "8").score());
        }

        @Test
        void add_correction_for_gaps_for_low_cards_in_score() {
            assertEquals(7, buildPlayerWithOffSuitCards("J", "10").score());
            assertEquals(6, buildPlayerWithOffSuitCards("J", "9").score());
        }

        @Test
        void round_half_points_up_for_score() {
            assertEquals(5, buildPlayerWithOffSuitCards("7", "9").score());
        }
    }
}
