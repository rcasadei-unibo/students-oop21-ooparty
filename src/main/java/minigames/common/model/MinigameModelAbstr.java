package minigames.common.model;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import utils.exceptions.PlayerNotFoundException;
import game.common.model.GameModelAbstr;
import game.dice.model.DiceModel;
import game.player.Player;

/**
 * Implementation of {@link MinigameModel}.
 */
public abstract class MinigameModelAbstr extends GameModelAbstr implements MinigameModel {

    private final Map<Player, Integer> playersClassification;
    private final DiceModel dice;
    private List<Player> gameResults;

    /**
     * Builds a new {@link MinigameModelAbstr}.
     * 
     * @param players the list of players
     * @param dice    the dice model
     */
    public MinigameModelAbstr(final List<Player> players, final DiceModel dice) {
        super(players);
        this.playersClassification = new LinkedHashMap<>();
        this.dice = dice;
    }

    /**
     * Builds a new {@link MinigameModelAbstr} with no {@link StageManager}.
     * 
     * @param players the list of players
     */
    public MinigameModelAbstr(final List<Player> players) {
        this(players, null);
    }

    @Override
    public final Map<Player, Integer> getPlayersClassification() {
        return this.playersClassification;
    }

    @Override
    public abstract boolean runGame();

    @Override
    public final List<Player> getGameResults() {
        return this.gameResults;
    }

    @Override
    public final void scoreMapper(final Player player, final Integer score) {
        this.playersClassification.put(player, score);
    }

    @Override
    public final int getScore() {
        return this.playersClassification.getOrDefault(this.getCurrPlayer(), 0);
    }

    /**
     * This method manages the draws at the end of the minigame.
     * 
     * @param scoreGroups the players grouped by score
     * @return a list of players ordered by their classification in the minigame and
     *         draws already managed
     */
    private List<Player> playoff(final Map<Integer, List<Player>> scoreGroups) {
        scoreGroups.entrySet().forEach(element -> {
            List<Player> players = element.getValue();
            if (players.size() > 1) {
                final Map<Player, Integer> sorted = new LinkedHashMap<>();
                players.forEach(player -> {
                    this.dice.rollDice();
                    /*if (s != null) {
                        if (s.getGui().mainStagePresence()) {
                            this.dice.start(player);
                        }
                    }*/
                    sorted.put(player, (Integer) this.dice.getLastResult().get());
                });
                players = sorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .map(Entry::getKey).collect(Collectors.toList());
                element.setValue(players);
            }
        });
        return scoreGroups.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * This method makes groups of players with the same score (higher to lower).
     * 
     * @return a map having a score as key and a list of players as value
     */
    private Map<Integer, List<Player>> groupPlayersByScore() {
        return this.playersClassification.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.groupingBy(Map.Entry::getValue, LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    }

    /**
     * Setter for gameResults.
     */
    protected void setGameResults() {
        this.gameResults = this.playoff(this.groupPlayersByScore());
    }

    /**
     * Setter for score.
     * 
     * @param score the score of the player
     * @throws PlayerNotFoundException if the current player is not set
     */
    protected void setScore(final int score) throws PlayerNotFoundException {
        if (this.hasCurrPlayer()) {
            this.scoreMapper(this.getCurrPlayer(), score);
        } else {
            throw new PlayerNotFoundException("The current player is not set.");
        }
    }

}
