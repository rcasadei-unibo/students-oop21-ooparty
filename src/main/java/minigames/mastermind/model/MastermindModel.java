package minigames.mastermind.model;

import minigames.common.model.MinigameModel;

/**
 * This interface models the mastermind model.
 * 
 * @param <S> the scenes of the stage
 * @param <U> the {@link game.player.Player}
 */
public interface MastermindModel<S, U> extends MinigameModel<S, U> {

    /**
     * Getter for win.
     * 
     * @return true if the current player has guessed the 4-digit number
     */
    boolean getWin();

    /**
     * Getter for lose.
     * 
     * @return true if the current player hasn't guessed the 4-digit number and the
     *         attempts are ended
     */
    boolean getLose();

    /**
     * Getter for the number of attempts.
     * 
     * @return the number of attempts
     */
    int getNAttempts();

    /**
     * Getter for the score.
     * 
     * @return the score of a player
     */
    int getScore();

    /**
     * Getter for the solution.
     * 
     * @return the 4-digit number to guess
     */
    String getSolution();

    /**
     * Setter for the max number of attempts.
     */
    void setMaxAttempts(int maxAttempts);

    /**
     * This method controls the attempt of the player.
     * 
     * @param attempt the player attempt
     * @return the attempt string if the attempt is valid, null otherwise
     */
    String doAttempt(String attempt);

}
