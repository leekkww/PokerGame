import java.util.*;

public class HighCard extends PokerHand {

	private ArrayList<Integer> values;

	public HighCard(ArrayList<Card> hand, ArrayList<Integer> values) {
		super(hand);
		this.values = values;
	}

	public static HighCard makeHand(ArrayList<Card> hand) {
		ArrayList<Integer> values = new ArrayList<>();
		for(Card c : hand) {
			values.add(c.value().getInt());
		}
		Collections.reverse(values);
		return new HighCard(hand, values);
	}

	@Override
	public ArrayList<Integer> getRank() {
		return values;
	}

	public String toString() {
		String response = "High card, ";
		for(Integer i : values) {
			response += Card.intToValue(i) + " ";
		}
		return response;
	}
}