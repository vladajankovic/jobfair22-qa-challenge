package logic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestFindThePrize {
	
	/*
	 * Tested with JUnit 5
	 */

    @Test
    public void testClass1() {
    	try {
		FindThePrize game = FindThePrize.init(-1, -1, -1);
		assertEquals(1, 0);
    	} catch (Exception e) {
		assertEquals(1, 1);
	}	
    }
    @Test
    public void testClass2() {
    	try {
		FindThePrize game = FindThePrize.init(-1, 2, 2);
		assertEquals(1, 0);
    	} catch (Exception e) {
		assertEquals(1, 1);
	}	
    }
    @Test
    public void testClass3() {
    	try {
		FindThePrize game = FindThePrize.init(2, -1, 2);
		assertEquals(1, 0);
    	} catch (Exception e) {
		assertEquals(1, 1);
	}	
    }
    @Test
    public void testClass4() {
    	try {
		FindThePrize game = FindThePrize.init(2, 2, -1);
		assertEquals(1, 0);
    	} catch (Exception e) {
		assertEquals(1, 1);
	}	
    }
    @Test
    public void testClass5() {
    	try {
		FindThePrize game = FindThePrize.init(1, 2, 2);
		assertEquals(1, 0);
    	} catch (Exception e) {
		assertEquals(1, 1);
	}
    }
    @Test
    public void testClass6() {
    	try {
		FindThePrize game = FindThePrize.init(2, 2, 2);
		assertEquals(1, 1);
	} catch (Exception e) {
		assertEquals(1, 0);
	}
    }
    
    @Test
    public void testGetPoints() {
    	FindThePrize game = FindThePrize.init(2 , 2, 2);
    	assertEquals(0, game.getNumberOfPoints());
    }
    
    @Test
    public void testGetOptions() {
    	int n = 100;
    	FindThePrize game = FindThePrize.init(n , 2, 2);
    	assertEquals(n, game.getNumberOfOptions());
    }
    
    @Test
    public void testGuessFunc1() {
    	FindThePrize game = FindThePrize.init(2 , 2, 2);
    	try {
		game.guess(-1);
		assertEquals(1, 0);
	} catch (Exception e) {
		assertEquals(1, 1);
	}
    }
    @Test
    public void testGuessFunc2() {
    	FindThePrize game = FindThePrize.init(2 , 2, 2);
    	try {
                game.guess(0);
                assertEquals(1, 0);
	} catch (Exception e) {
		assertEquals(1, 1);
	}
    }
    @Test
    public void testGuessFunc3() {
    	FindThePrize game = FindThePrize.init(2 , 2, 2);
    	try {
		game.guess(game.getNumberOfOptions()+1);
		assertEquals(1, 0);
	} catch (Exception e) {
		assertEquals(1, 1);
	}
    }
    @Test
    public void testGuessFunc4() {
    	FindThePrize game = FindThePrize.init(2 , 2, 2);
    	try {
		game.guess(game.getNumberOfOptions());
		assertEquals(1, 1);
	} catch (Exception e) {
		assertEquals(1, 0);
	}
    }
    
    @Test
    public void testPointIncrease () {
        FindThePrize game = FindThePrize.init(2 , 2, 2);
        game.addPoint();
        assertEquals(1, game.getNumberOfPoints());
    }
    
    @Test
    public void testRound() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        assertTrue(game.playRound(1));
        assertEquals(1, game.getNumberOfPoints());
    }
    
    @Test
    public void testGame1() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(-1));
        assertEquals(0, game.playGame(t));
    }
    @Test
    public void testGame2() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(0));
        assertEquals(0, game.playGame(t));
    }
    @Test
    public void testGame3() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(game.getNumberOfOptions()));
        assertEquals(1, game.playGame(t));
    }
    @Test
    public void testGame4() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(game.getNumberOfOptions()-1));
        assertEquals(0, game.playGame(t));
    }
    @Test
    public void testGame5() {
        FindThePrize game = FindThePrize.init(1, 1, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(1));
        assertEquals(1, game.playGame(t));
    }
    @Test
    public void testGame6() {
        FindThePrize game = FindThePrize.init(5, 5, 1);
        List<Integer> t = new ArrayList<>(Arrays.asList(game.getNumberOfOptions()-1));
        assertEquals(1, game.playGame(t));
    }
}
