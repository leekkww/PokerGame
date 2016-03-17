import java.util.*;
public class FullHouse extends PokerHand {

	private int tripValue;
	private int pairValue;

	public FullHouse(ArrayList<Card> hand, int tripValue, int pairValue) {
		super(hand);
		this.tripValue = tripValue;
		this.pairValue = pairValue;
	}

	public static FullHouse makeHand(ArrayList<Card> hand) {
		int[] values = PokerHand.getFrequencies(hand);

		int threeCount = -1;
		int twoCount = -1;
		for(int i = 12; i >= 0 ; --i) {
			if(values[i] == 3) {
				threeCount = i;
			} else if(values[i] == 2 && twoCount == -1) {
				twoCount = i;
			}
		}
		if(threeCount != -1 && twoCount != -1) {
			return new FullHouse(hand, threeCount, twoCount);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> rank = new ArrayList<>();
		rank.add(tripValue);
		rank.add(pairValue);
		return rank;
	}

	public String toString() {
		return "Full House, " + Card.values()[tripValue] + "s full of " + Card.values()[pairValue] + "s";
	}
}