package game.map;

import java.util.HashSet;
import java.util.Set;

import game.player.Player;

public class GameMapBoxImpl implements GameMapBox {
    private Set<Player> players;

    /**
     * It's the maximum number of coins that can be found on a coin box.
     */
    public static final int MAX_COINS = 10;

    /**
     * It's the maximum number of damage that can be taken from a damage box.
     */
    public static final int MAX_DAMAGE = 50;

    public GameMapBoxImpl() {
        this.players = new HashSet<>();
    }

    /**
     *
     */
    @Override
    public final Set<Player> getPlayers() {
        return Set.copyOf(this.players);
    }

    @Override
    public final void addPlayer(final Player p) {
        this.players.add(p);
    }

    @Override
    public final void removePlayer(final Player p) {
        this.players.remove(p);
    }

    @Override
    public int getCoinsNumber() {
        return 0;
    }

    @Override
    public void receiveCoins(final Player p) {
        //TODO lanciare un'eccezione?
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void receiveDamage(final Player p) {
        //TODO lanciare un'eccezione?
    }
}
