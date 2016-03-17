import java.util.*;

public class Pair extends PokerHand {

	private int pair;
	private int[] kickers;

	public Pair(ArrayList<Card> hand, int pair, int[] kickers) {
		super(hand);
		this.pair = pair;
		this.kickers = kickers;
	}

	public static Pair makeHand(ArrayList<Card> hand) {
		int[] values = PokerHand.getFrequencies(hand);

		int pair = -1;
		int numKicker = 0;
		int[] kickers = new int[3];
		for(int i = 12; i >= 0 ; --i) {
			if(values[i] == 2) {
				pair = i;
			} else if(values[i] == 1 && numKicker < 3) {
				kickers[numKicker] = i;
				numKicker++;
			}
		}
		if(pair != -1) {
			return new Pair(hand, pair, kickers);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> getRank() {
		ArrayList<Integer> rank = new ArrayList<>();
		rank.add(pair);
		rank.add(kickers[0]);
		rank.add(kickers[1]);
		rank.add(kickers[2]);
		return rank;
	}

	public String toString() {
		return "Pair of " + Card.intToValue(pair) + "s, with kickers " + Card.intToValue(kickers[0]) + " " + Card.intToValue(kickers[1]) + " " + Card.intToValue(kickers[2]);
	}
}