package logic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestFindThePrize {

    //Unit test example for addPoint function
    @Test
    public void testPointIncrease () {
        FindThePrize game = FindThePrize.init(1 , 2, 2);
        game.addPoint();
        assertEquals(1, game.getNumberOfPoints());
    }
}
