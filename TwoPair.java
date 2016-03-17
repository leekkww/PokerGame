import java.util.*;

public class TwoPair extends PokerHand {

	private int topPair;
	private int secondPair;
	private int kicker;

	public TwoPair(ArrayList<Card> hand, int topPair, int secondPair, int kicker) {
		super(hand);
		this.topPair = topPair;
		this.secondPair = secondPair;
		this.kicker = kicker;
	}

	public static TwoPair makeHand(ArrayList<Card> hand) {
		int[] values = PokerHand.getFrequencies(hand);

		int numPairs = 0;
		int[] pairs = new int[2];
		int kicker = -1;
		for(int i = 12; i >= 0 ; --i) {
			if(values[i] == 2) {
				pairs[numPairs] = i;
				numPairs++;
			} else if(values[i] == 1 && kicker == -1) {
				kicker = i;
			}
		}
		if(numPairs == 2) {
			return new TwoPair(hand, pairs[0], pairs[1], kicker);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> rank = new ArrayList<>();
		rank.add(topPair);
		rank.add(secondPair);
		rank.add(kicker);
		return rank;
	}

	public String toString() {
		return "Two pair, " + Card.intToValue(topPair) + "s and " + Card.intToValue(secondPair) + "s with " + Card.intToValue(kicker) + " kicker";
	}
}