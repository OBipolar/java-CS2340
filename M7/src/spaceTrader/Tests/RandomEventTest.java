package spaceTrader.Tests;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.RandomEvent;

public class RandomEventTest {

    private RandomEvent r;

    @Before
    public void setUp() throws Exception {
        r = new RandomEvent();
    }

    @Test
    public void test() {
        System.out.println(r.update());
    }

}
