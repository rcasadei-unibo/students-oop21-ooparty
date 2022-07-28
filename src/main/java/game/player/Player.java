package game.player;

import game.map.GameMap;
import game.map.GameMapSquare;
import javafx.scene.paint.Color;

/**
 * This interface models one player.
 */
public interface Player {

    /**
     * @return the name of the player
     */
    String getNickname();

    /**
     * @return the color of the player
     */
    Color getColor();

    /**
     * Moves the player forward.
     * 
     * @param n number of steps
     * @param gameMap the map of the game
     */
    void moveForward(int n, GameMap gameMap);

    /**
     * Moves the player to a certain position.
     * @param gameMap the map of the game
     * @param newGameMapSquare the new player's position square
     */
    void goTo(GameMap gameMap, GameMapSquare newGameMapSquare);

    /**
     * 
     * @param gameMap the map of the game
     * @return the player's position (the square where he is located)
     */
    GameMapSquare getPosition(GameMap gameMap);

    /**
     * Increments the player's coins.
     * 
     * @param n coins to add
     */
    void earnCoins(int n);

    /**
     * Decrements the player's coins.
     * 
     * @param n coins to subtract
     */
    void loseCoins(int n);

    /**
     * Updates the player's coins.
     * 
     * @param n new amount of coins
     */
    void updateCoins(int n);

    /**
     * @return amount of player's coins
     */
    int getCoinsCount();

    /**
     * Make player earn a star.
     */
    void earnStar();

    /**
     * Make player lose a star.
     */
    void loseStar();

    /**
     * @return number of player'stars
     */
    int getStarsCount();

    /**
     * 
     * @return the amount of life points
     */
    int getLifePoints();

    /**
     * Adds amount life points to the current life points.
     * @param amount the amount of life points to get
     */
    void addLifePoints(int amount);

    /**
     * Takes Away life from the player.
     * @param damage the amount of life to be taken away
     */
    void loseLifePoints(int damage);
}
