package org.leanpoker.player;

import java.util.Collections;
import java.util.List;

class TestGameStates {

    public static GameState buildGameStateWithDefaults() {
        return new GameState("550d1d68cd7bd10003000003", "550da1cb2d909006e90004b1",
                0, 0, 10, 320, 400, 240, 1, 7, 1,
                List.of(
                        new PlayerState(0, "Albert", PlayerStatus.ACTIVE, "Default random player", 1010, 320,
                                Collections.emptyList()),
                        new PlayerState(1, "Bob", PlayerStatus.ACTIVE, "Default random player", 1590, 80,
                                List.of(
                                        new Card("6", Suit.HEARTS),
                                        new Card("K", Suit.SPADES)
                                )),
                        new PlayerState(2, "Chuck", PlayerStatus.OUT, "Default random player", 0, 0,
                                Collections.emptyList()))
                ,
                List.of(
                        new Card("4", Suit.SPADES),
                        new Card("A", Suit.HEARTS),
                        new Card("6", Suit.CLUBS),
                        new Card("A", Suit.SPADES),
                        new Card("6", Suit.DIAMONDS)));
    }


    public static GameState buildGameStateWithCommunityCards(List<Card> communityCards) {
        return new GameState("550d1d68cd7bd10003000003", "550da1cb2d909006e90004b1",
                0, 0, 10, 320, 400, 240, 1, 7, 1,
                List.of(
                        new PlayerState(0, "Albert", PlayerStatus.ACTIVE, "Default random player", 1010, 320,
                                Collections.emptyList()),
                        new PlayerState(1, "Bob", PlayerStatus.ACTIVE, "Default random player", 1590, 80,
                                List.of(
                                        new Card("6", Suit.HEARTS),
                                        new Card("K", Suit.SPADES)
                                )
                        )
                        ,
                        new PlayerState(2, "Chuck", PlayerStatus.OUT, "Default random player", 0, 0,
                                Collections.emptyList())),
                communityCards);
    }

    public static GameState buildGameStateWithBettingPosition(int dealer, int inAction) {
        return new GameState("550d1d68cd7bd10003000003", "550da1cb2d909006e90004b1",
                0, 0, 10, 320, 400, 240, dealer, 7, inAction,
                List.of(
                        new PlayerState(0, "Albert", PlayerStatus.ACTIVE, "Default random player", 1010, 320,
                                Collections.emptyList()),
                        new PlayerState(1, "Bob", PlayerStatus.ACTIVE, "Default random player", 1590, 80,
                                List.of(
                                        new Card("6", Suit.HEARTS),
                                        new Card("K", Suit.SPADES)
                                )
                        ),
                        new PlayerState(2, "Chuck", PlayerStatus.OUT, "Default random player", 0, 0,
                                Collections.emptyList())
                ),
                Collections.emptyList());
    }

    public static GameState buildGameStateWithPlayerStatus(PlayerStatus status, PlayerStatus status2, PlayerStatus
            status3) {
        return new GameState("550d1d68cd7bd10003000003", "550da1cb2d909006e90004b1",
                0, 0, 10, 320, 400, 240, 1, 7, 1,
                List.of(
                        new PlayerState(0, "Albert", status, "Default random player", 1010, 320,
                                Collections.emptyList()),
                        new PlayerState(1, "Bob", status2, "Default random player", 1590, 80,
                                List.of(
                                        new Card("6", Suit.HEARTS),
                                        new Card("K", Suit.SPADES)
                                )
                        ),
                        new PlayerState(2, "Chuck", status3, "Default random player", 0, 0, Collections.emptyList())),
                List.of(
                        new Card("4", Suit.SPADES),
                        new Card("A", Suit.HEARTS),
                        new Card("6", Suit.CLUBS),
                        new Card("A", Suit.SPADES),
                        new Card("6", Suit.DIAMONDS)));
    }
}

