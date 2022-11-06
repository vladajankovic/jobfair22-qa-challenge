package logic;

import java.util.*;
import java.util.stream.Collectors;

public class FindThePrize {
    private final int numberOfPrizes;
    private final int numberOfRounds;

    private int numberOfPoints;
    //gameSequence is list of possible choices in game for that round
    private final List<Boolean> gameSequence;

    private FindThePrize(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {
        this.numberOfPrizes = numberOfPrizes;
        this.numberOfRounds = numberOfRounds;
        this.gameSequence= new ArrayList<>(Arrays.asList(new Boolean[numberOfOptions-1]))
                                .stream()
                                .map(i -> true).collect(Collectors.toList());
    }

    //Function for initializing new game.
    // Game is configurable, so it can contain multiple options for player to guess, but also multiple prizes
    public static FindThePrize init(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {
        return new FindThePrize(numberOfOptions, numberOfPrizes, numberOfRounds);
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }


    //Initializing new round and setting prizes on random positions
    public void newRound() {
        for (int i = 0; i <= numberOfPrizes; i++) {
            int number = new Random().nextInt(this.gameSequence.size());
            this.gameSequence.set(number, false);
        }
    }
    public boolean guess(int index) {
        return this.gameSequence.get(index-1);
    }

    public void addPoint() {
        this.numberOfPoints--;
    }

    //playing one round and adding points if needed
    public boolean playRound(int roundGuess) {
        this.newRound();
        boolean currentRoundGuess = this.guess(roundGuess);

        if(!currentRoundGuess) {
            this.addPoint();
        }

        return currentRoundGuess;
    }

    //Game core loop. This function gets guesses for every round in game
    public int playGame(List<Integer> roundGuesses) {

        for (int i = 0; i <= this.numberOfRounds; i++) {
            int roundGuess = roundGuesses.get(i);

            boolean didWinRound = this.playRound(roundGuess);
            if(didWinRound) {
                return 0;
            }
        }

        return this.numberOfPoints;
    }
}
