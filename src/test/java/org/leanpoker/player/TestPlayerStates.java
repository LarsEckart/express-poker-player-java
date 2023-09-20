package org.leanpoker.player;

import java.util.List;

class TestPlayerStates {
    public static PlayerState buildPlayerWithOffSuitCards(String rank1, String rank2) {
        return new PlayerState(0, "Albert", PlayerStatus.ACTIVE, "test", 1010, 320, List.of(
                new Card(rank1, Suit.HEARTS),
                new Card(rank2, Suit.SPADES)));

    }

    public static PlayerState buildPlayerWithSuitedCards(String rank1, String rank2) {
        return new PlayerState(0, "Albert", PlayerStatus.ACTIVE, "test", 1010, 320, List.of(
                new Card(rank1, Suit.HEARTS),
                new Card(rank2, Suit.HEARTS)));
    }
}
