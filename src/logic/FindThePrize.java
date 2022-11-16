package logic;

import java.util.*;
import java.util.stream.Collectors;

public class FindThePrize {
    private final int numberOfPrizes;
    private final int numberOfRounds;

    private int numberOfPoints;
    private int numberOfPrizesLeft;
    //gameSequence is list of possible choices in game for that round
    private final List<Boolean> gameSequence;

    private FindThePrize(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {
        this.numberOfPrizes = numberOfPrizes;
        this.numberOfRounds = numberOfRounds;
        this.numberOfPrizesLeft = numberOfPrizes;
        
        // ERROR FIX: Remove -1 from numberOfOptions-1 to correct array size
        // PERSONAL PREFERENCE: Initial values are all false
        this.gameSequence = new ArrayList<>(Arrays.asList(new Boolean[numberOfOptions]))
                                .stream()
                                .map(i -> false).collect(Collectors.toList());
    }

    //Function for initializing new game.
    // Game is configurable, so it can contain multiple options for player to guess, but also multiple prizes
    public static FindThePrize init(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {
    	
    	// ERROR FIX: Check if function arguments are positive integers
    	//				Check if there are more options than prizes
    	if(numberOfOptions <= 0 || numberOfPrizes <= 0 || numberOfRounds <= 0 || numberOfOptions < numberOfPrizes) {
    		//System.err.println("Invalid arguments!");
			throw new IllegalArgumentException();
    	}
    	
    	return new FindThePrize(numberOfOptions, numberOfPrizes, numberOfRounds);
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
    
    // For testing purposes
    public int getNumberOfOptions() {
    	return this.gameSequence.size();
    }


    //Initializing new round and setting prizes on random positions
    public void newRound() {
    	
    	// ERROR FIX: Reset gameSequence to initial state
    	for(int i = 0; i < this.gameSequence.size(); i++) {
    		this.gameSequence.set(i, false);
    	}
    	
    	// ERROR FIX: i = {0,1,2, ..., n-1} -> numberOfPrizes - 1
        for (int i = 0; i <= numberOfPrizesLeft - 1; i++) {
            int number = new Random().nextInt(this.gameSequence.size());
            
            // ERROR FIX: If a prize is already set in that index, try again
            while(this.gameSequence.get(number)) {
            	number = new Random().nextInt(this.gameSequence.size());
            }
            
            // PERSONAL PREFERENCE: Prizes are on indexes whose values are set to true
            this.gameSequence.set(number, true);
        }
    }
    public boolean guess(int index) {
        return this.gameSequence.get(index-1);
    }

    public void addPoint() {
    	
    	// ERROR FIX: Incrementing instead of decrementing
        this.numberOfPoints++;
    }

    //playing one round and adding points if needed
    public boolean playRound(int roundGuess) {
        this.newRound();
        boolean currentRoundGuess = this.guess(roundGuess);

        // PERSONAL PREFERENCE: Prize on true value
        if(currentRoundGuess) {
            this.addPoint();
            this.numberOfPrizesLeft--;
        }

        return currentRoundGuess;
    }

    //Game core loop. This function gets guesses for every round in game
    public int playGame(List<Integer> roundGuesses) {
    	
    	// ERROR FIX: if number of rounds is greater than number of guesses
    	int rounds = roundGuesses.size() < this.numberOfRounds ? roundGuesses.size() : this.numberOfRounds;
    	this.numberOfPrizesLeft = this.numberOfPrizes;
    	
    	// ERROR FIX: i = {0,1,2, ..., n-1} -> numberOfRounds - 1
    	// 				Game ends if all prizes are collected
        for (int i = 0; i <= rounds - 1 && this.numberOfPrizesLeft > 0; i++) {
            int roundGuess = roundGuesses.get(i);
            
            // ERROR FIX: End game if invalid guess was made
            if(roundGuess <= 0 || roundGuess > this.gameSequence.size()){
        		//System.err.println("Invalid guess!");
    			break;
        	}
            
            boolean didWinRound = this.playRound(roundGuess);
            
            // ERROR FIX: If a wrong guess is made, exit the loop and
            //				return the current amount of prizes won
            // PERSONAL PREFERENCE: False value is loss
            if(!didWinRound) {
                break;
            }
        }

        return this.numberOfPoints;
    }
}
