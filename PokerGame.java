import java.util.*;

public class PokerGame {

	private ArrayList<Player> players;
	private ArrayList<ArrayList<Action>> handHistory;

	//deal with increasing blinds and stuff here

	public PokerGame(ArrayList<Player> players) {
		this.players = players;
		int count = 0;
		while(players.size() > 1) {
			Deal d = new Deal(players, 10, count);
			d.play();
			handHistory.add(d.history);

			//TODO remove players who have 0
			count++;
			count %= players.size();
		}
	}
}