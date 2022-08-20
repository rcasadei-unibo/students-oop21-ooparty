package utils.factories;

import java.util.List;
import java.util.Random;

import game.player.Player;
import minigames.common.controller.MinigameController;
import minigames.mastermind.controller.MastermindControllerImpl;
import minigames.whoriskswins.controller.WhoRisksWinsControllerImpl;
import utils.graphics.controller.StageManager;

public class MinigameControllerFactoryImpl<S> {
    private static final int MINIGAMES_NUMBER = 2;

    private final StageManager<S> stageManager;
    private final List<Player> players;

    public MinigameControllerFactoryImpl(final List<Player> players, final StageManager<S> s) {
        this.players = players;
        this.stageManager = s;
    }

    public final MinigameController createRandomMinigameController() {
        Random rand = new Random();
        switch (rand.nextInt(MinigameControllerFactoryImpl.MINIGAMES_NUMBER)) {
        case 0:
            return this.stageManager.getControllerFactory().createMastermindController(this.players);
        case 1:
            return this.stageManager.getControllerFactory().createWhoRisksWinsController(this.players);
        default:
            return null;
        }
    }

    /*public final MinigameController createMastermindController() {
        return new MastermindControllerImpl(this.stageManager, this.players);
    }

    public final MinigameController createWhoRisksWinsController() {
        return new WhoRisksWinsControllerImpl(this.stageManager, this.players);
    }*/
}
