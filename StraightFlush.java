import java.util.*;
public class StraightFlush extends PokerHand {

	private int maxValue;

	public StraightFlush(ArrayList<Card> hand, int maxValue) {
		super(hand);
		this.maxValue = maxValue;
	}

	public static StraightFlush makeHand(ArrayList<Card> hand) {
		if(Flush.makeHand(hand) != null && Straight.makeHand(hand) != null) {
			int maxValue = hand.get(4).value().getInt();
			return new StraightFlush(hand, maxValue);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> values = new ArrayList<>();
		values.add(maxValue);
		return values;
	}

	public String toString() {
		return "Straight flush, " + Card.values()[maxValue] + " high";
	}

	
}