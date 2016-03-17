import java.util.*;

public class Pot {
	
	private ArrayList<Player> players;
	private int pot;

	public Pot(int pot, ArrayList<Player> players) {
		this.pot = pot;
		this.players = players;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int amount() {
		return pot;
	}

	public void addToPot(int addition) {
		pot += addition;
	}

	@Override
	public String toString() {
		return pot + "";
	}
}