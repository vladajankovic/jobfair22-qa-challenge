import logic.FindThePrize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        FindThePrize game = FindThePrize.init(4 , 4, 5);
        List<Integer> guesses = new ArrayList<>(
                Arrays.asList(1, 1, 2, 1, 1));
        int points = game.playGame(guesses);
        System.out.printf("Number of points: %d\n", points);
    }
}