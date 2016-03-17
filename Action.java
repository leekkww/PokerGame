public class Action {

	private Player player;
	private int amount;
	private Type type;

	public enum Type {
		FOLD, RAISE, CALL;
	}

	public Action(Player player, Action.Type type, int amount) {
		this.player = player;
		this.amount = amount;
		this.type = type;
	}
	
	public Action(Player player, Action.Type type) {
		this(player, type,0);
	}

	public Type getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public String toString() {
		return player + " " + type + " " + (type == Type.FOLD ? "" : amount);
	}
}