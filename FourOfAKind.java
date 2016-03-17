import java.util.*;
public class FourOfAKind extends PokerHand {

	private int quadValue;
	private int kicker;

	public FourOfAKind(ArrayList<Card> hand, int quadValue, int kicker) {
		super(hand);
		this.quadValue = quadValue;
		this.kicker = kicker;
	}

	public static FourOfAKind makeHand(ArrayList<Card> hand) {
		int[] values = PokerHand.getFrequencies(hand);

		int fourCount = -1;
		int oneCount = -1;
		for(int i = 12; i >= 0 ; --i) {
			if(values[i] == 4) {
				fourCount = i;
			} else if(values[i] == 1 && oneCount == -1) {
				oneCount = i;
			}
		}
		if(fourCount != -1 && oneCount != -1) {
			return new FourOfAKind(hand, fourCount, oneCount);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> rank = new ArrayList<>();
		rank.add(quadValue);
		rank.add(kicker);
		return rank;
	}

	public String toString() {
		return "Quad " + Card.values()[quadValue] + "s with "+ Card.values()[kicker] + " kicker";
	}
}