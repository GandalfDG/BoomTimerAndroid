package com.example.jack.boomtimer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HostageUnitTests {
    @Test
    public void hostages_areCorrect1() throws Exception {
        BoomGame game = new BoomGame(1, 6, 3);
        assertEquals(1, game.getHostages());
    }

    @Test
    public void hostages_areCorrect2() throws Exception {
        BoomGame game = new BoomGame(1, 12, 5);
        assertEquals(2, game.getHostages());
    }

    @Test
    public void hostages_areCorrect3() throws Exception {
        BoomGame game = new BoomGame(1, 15, 5);
        assertEquals(3, game.getHostages());
    }

    @Test
    public void hostages_areCorrect4() throws Exception {
        BoomGame game = new BoomGame(3, 15, 5);
        assertEquals(2, game.getHostages());
    }

    @Test
    public void hostages_areCorrect5() throws Exception {
        BoomGame game = new BoomGame(5, 15, 5);
        assertEquals(1, game.getHostages());
    }
}