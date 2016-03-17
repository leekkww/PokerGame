import java.util.*;

public class ThreeOfAKind extends PokerHand {

	private int tripValue;
	private int kicker1;
	private int kicker2;

	public ThreeOfAKind(ArrayList<Card> hand, int tripValue, int kicker1, int kicker2) {
		super(hand);
		this.tripValue = tripValue;
		this.kicker1 = kicker1;
		this.kicker2 = kicker2;
	}

	public static ThreeOfAKind makeHand(ArrayList<Card> hand) {
		int[] values = PokerHand.getFrequencies(hand);

		int threeCount = -1;
		int numKicker = 0;
		int[] kickers = new int[2];
		for(int i = 12; i >= 0 ; --i) {
			if(values[i] == 3) {
				threeCount = i;
			} else if(values[i] == 1 && numKicker < 2) {
				kickers[numKicker] = i;
				numKicker++;
			}
		}
		if(threeCount != -1) {
			return new ThreeOfAKind(hand, threeCount, kickers[0], kickers[1]);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> rank = new ArrayList<>();
		rank.add(tripValue);
		rank.add(kicker1);
		rank.add(kicker2);
		return rank;
	}

	public String toString() {
		return "Trip " + Card.intToValue(tripValue) + "s, with " + Card.intToValue(kicker1) + " " + Card.intToValue(kicker2) + " kicker";
	}
}