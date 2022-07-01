package menu.gamecreationmenu.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import utils.IntSpinnerValueFactory;
import utils.enums.PlayerColor;
import utils.graphics.StageManager;

/**
 * Implementation of {@link GameCreationMenuModel}.
 */
public class GameCreationMenuModelImpl<S> implements GameCreationMenuModel<S> {

	public static final int nMinPlayers = 2;
	public static final int nMaxPlayers = 4;
	public static final int nMinTurns = 1;
	public static final int nMaxTurns = 20;
	private StageManager<S> stageManager;
	private int actualNPlayers;
	private final Map<Integer, List<PlayerColor>> unavailableColors;
	
	public GameCreationMenuModelImpl(final StageManager<S> s) {
		super();
		this.stageManager = s;
		this.actualNPlayers = GameCreationMenuModelImpl.nMinPlayers;
		this.unavailableColors = IntStream
			      .range(0, GameCreationMenuModelImpl.nMaxPlayers)
			      .boxed()
			      .collect(Collectors.toMap(Function.identity(), i -> {
			    	  return Stream.of(PlayerColor.values())
			    			  .limit(GameCreationMenuModelImpl.nMaxPlayers)
			    			  .filter(color -> color.ordinal() != i)
			    			  .collect(Collectors.toList());
			      }));
		System.out.println(this.unavailableColors);
	}

	@Override
	public void returnToMainMenu() {
		this.stageManager.popScene();
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillColorsBoxes(final List<ComboBox<PlayerColor>> playerColors) {
		playerColors.forEach(color -> {
			final List<PlayerColor> availableColors;
			color.setItems(FXCollections.observableArrayList(PlayerColor.values()));
 			color.getSelectionModel().selectFirst();
		});
	}
	
	@Override
	public void setNumberOfPlayersSpinner(final Spinner<Integer> numberOfPlayers) {
		this.setSpinnerControls(numberOfPlayers, GameCreationMenuModelImpl.nMinPlayers, GameCreationMenuModelImpl.nMaxPlayers);
	}
	
	@Override
	public void setTurnsNumberSpinner(Spinner<Integer> turnsNumber) {
		this.setSpinnerControls(turnsNumber, GameCreationMenuModelImpl.nMinTurns, GameCreationMenuModelImpl.nMaxTurns);
	}

	@Override
	public void showForms(final List<VBox> playersForms, Integer nPlayers) {
		for (int i = GameCreationMenuModelImpl.nMinPlayers; i < GameCreationMenuModelImpl.nMaxPlayers; i++) {
			var form = playersForms.get(i);
			if (i >= nPlayers) {
				this.hideForm(form);
			} else {
				this.showForm(form);
			}
		}
		this.actualNPlayers = nPlayers;
	}
	
	/**
	 * This method shows a player form.
	 * @param form the player form
	 */
	private void showForm(final VBox form) {
		form.setVisible(true);
		form.setManaged(true);
	}
	
	/**
	 * This method hides a player form.
	 * @param form the player form
	 */
	private void hideForm(final VBox form) {
		form.setVisible(false);
		form.setManaged(false);
	}
	
	/**
	 * This method sets the value factory for a generic number spinner.
	 * @param spinner the spinner to be set
	 * @param min the min value
	 * @param max the max value
	 */
	private void setSpinnerControls(final Spinner<Integer> spinner, final int min, final int max) {
		spinner.setValueFactory(new IntSpinnerValueFactory(min, max, min));
	}

}
