package com.example.jack.boomtimer;

/**
 * Created by Jack on 7/21/2017.
 */

public class BoomGame {
    private int currentRound;
    private int numPlayers;
    private int numRounds;

    private static final int[][] hostages = {{1, 1, 1, 0, 0},
                                             {1, 1, 1, 2, 2},
                                             {1, 1, 2, 2, 3},
                                             {1, 1, 2, 3, 4},
                                             {1, 2, 3, 4, 5}};

    public BoomGame(int numPlayers, int numRounds) {
        this.numPlayers = numPlayers;
        this.numRounds = numRounds;
    }

    public BoomGame(int currentRound, int numPlayers, int numRounds) {
        this.currentRound = currentRound;
        this.numPlayers = numPlayers;
        this.numRounds = numRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    private int get_hostages() {
        int groupCode = -1;
        if(numPlayers <= 10) {
            groupCode = 0;
        }
        else if(numPlayers <= 13) {
            groupCode = 1;
        }
        else if(numPlayers <= 17) {
            groupCode = 2;
        }
        else if(numPlayers <= 21) {
            groupCode = 3;
        }
        else {
            groupCode = 4;
        }

        return hostages[groupCode][numRounds - (currentRound - 1)];
    }
}
