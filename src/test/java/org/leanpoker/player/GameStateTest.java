package org.leanpoker.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.leanpoker.player.TestGameStates.buildGameStateWithBettingPosition;
import static org.leanpoker.player.TestGameStates.buildGameStateWithCommunityCards;
import static org.leanpoker.player.TestGameStates.buildGameStateWithDefaults;
import static org.leanpoker.player.TestGameStates.buildGameStateWithPlayerStatus;

class GameStateTest {


    @Test
    void our_player() {
        assertEquals("Bob", buildGameStateWithDefaults().me().name());
    }

    @Test
    void minimum_call_value() {
        assertEquals(240, buildGameStateWithDefaults().toCall());
    }

    @Test
    void minimum_raise_value() {
        assertEquals(480, buildGameStateWithDefaults().toRaise());
    }

    @Test
    void minimum_raise_value_by_blinds() {
        assertEquals(520, buildGameStateWithDefaults().toRaiseByBlinds(2));
    }

    @Test
    void card_count_by_suit() {
        Map<Suit, CardCount> suitCounts = buildGameStateWithDefaults().suitCounts();

        assertEquals(1, suitCounts.get(Suit.CLUBS).community());
        assertEquals(0, suitCounts.get(Suit.CLUBS).hole());
        assertEquals(1, suitCounts.get(Suit.CLUBS).total());

        assertEquals(1, suitCounts.get(Suit.DIAMONDS).community());
        assertEquals(0, suitCounts.get(Suit.DIAMONDS).hole());
        assertEquals(1, suitCounts.get(Suit.DIAMONDS).total());

        assertEquals(1, suitCounts.get(Suit.HEARTS).community());
        assertEquals(1, suitCounts.get(Suit.HEARTS).hole());
        assertEquals(2, suitCounts.get(Suit.HEARTS).total());

        assertEquals(2, suitCounts.get(Suit.SPADES).community());
        assertEquals(1, suitCounts.get(Suit.SPADES).hole());
        assertEquals(3, suitCounts.get(Suit.SPADES).total());
    }

    @Test
    void card_counts_by_rank() {
        Map<String, CardCount> rankCounts = buildGameStateWithDefaults().rankCounts();

        assertEquals(2, rankCounts.get("6").community());
        assertEquals(1, rankCounts.get("6").hole());
        assertEquals(3, rankCounts.get("6").total());

        assertEquals(2, rankCounts.get("A").community());
        assertEquals(0, rankCounts.get("A").hole());
        assertEquals(2, rankCounts.get("A").total());

        assertEquals(1, rankCounts.get("4").community());
        assertEquals(0, rankCounts.get("4").hole());
        assertEquals(1, rankCounts.get("4").total());

        assertEquals(0, rankCounts.get("2").community());
        assertEquals(0, rankCounts.get("2").hole());
        assertEquals(0, rankCounts.get("2").total());

        assertEquals(0, rankCounts.get("K").community());
        assertEquals(1, rankCounts.get("K").hole());
        assertEquals(1, rankCounts.get("K").total());
    }

    @Test
    void betting_position() {
        assertEquals(1, buildGameStateWithBettingPosition(0, 1).bettingPosition());
        assertEquals(3, buildGameStateWithBettingPosition(1, 1).bettingPosition());
        assertEquals(2, buildGameStateWithBettingPosition(2, 1).bettingPosition());

        assertEquals(2, buildGameStateWithBettingPosition(0, 2).bettingPosition());
        assertEquals(1, buildGameStateWithBettingPosition(1, 2).bettingPosition());
        assertEquals(3, buildGameStateWithBettingPosition(2, 2).bettingPosition());
    }

    @Test
    void active_players_in_game() {
        assertEquals(2, buildGameStateWithDefaults().activePlayersInGame());
    }

    @Test
    void activePlayersInHand() {
        assertEquals(2, buildGameStateWithDefaults().activePlayersInHand());
        assertEquals(2, buildGameStateWithPlayerStatus(PlayerStatus.ACTIVE, PlayerStatus.ACTIVE, PlayerStatus.FOLDED).activePlayersInHand());
        assertEquals(1, buildGameStateWithPlayerStatus(PlayerStatus.FOLDED, PlayerStatus.ACTIVE, PlayerStatus.FOLDED).activePlayersInHand());
    }

    @Nested
    class BettingRounds {
        @Test
        void pre_flop() {
            assertEquals("pre flop", buildGameStateWithCommunityCards(Collections.emptyList()).bettingRound());
        }

        @Test
        void flop() {
            assertEquals("flop", buildGameStateWithCommunityCards(List.of(
                    new Card("4", Suit.SPADES),
                    new Card("A", Suit.HEARTS),
                    new Card("6", Suit.CLUBS))).bettingRound());
        }

        @Test
        void turn() {
            assertEquals("turn", buildGameStateWithCommunityCards(List.of(
                    new Card("4", Suit.SPADES),
                    new Card("A", Suit.HEARTS),
                    new Card("6", Suit.CLUBS),
                    new Card("A", Suit.SPADES))).bettingRound());
        }

        @Test
        void river() {
            assertEquals("river", buildGameStateWithCommunityCards(List.of(
                    new Card("4", Suit.SPADES),
                    new Card("A", Suit.HEARTS),
                    new Card("6", Suit.CLUBS),
                    new Card("A", Suit.SPADES),
                    new Card("6", Suit.DIAMONDS))).bettingRound());
        }
    }

}
