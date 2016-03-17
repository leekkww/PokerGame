import java.util.*;
public class Straight extends PokerHand {

	private int maxValue;

	public Straight(ArrayList<Card> hand, int maxValue) {
		super(hand);
		this.maxValue = maxValue;
	}

	public static Straight makeHand(ArrayList<Card> hand) {
		int count = -1;
		for(int i = 0; i < 4; ++i) {
			if (!(hand.get(i+1).value().getInt() - hand.get(i).value().getInt() == 1)) {
				return null;
			}
		}
		int maxValue = hand.get(4).value().getInt();
		return new Straight(hand, maxValue);
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> values = new ArrayList<>();
		values.add(maxValue);
		return values;
	}

	public String toString() {
		return "Straight, " + Card.values()[maxValue] + " high";
	}
}