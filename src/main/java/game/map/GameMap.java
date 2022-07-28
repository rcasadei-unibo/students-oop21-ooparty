package game.map;

import java.util.List;

import game.player.Player;

public interface GameMap {

    /**
     * 
     * @return the list of the map's squares
     */
    List<GameMapSquare> getSquares();

    /**
     * 
     * @param p the player you want to know the position
     * @return the player p's position
     */
    GameMapSquare getPlayerPosition(Player p);
}
