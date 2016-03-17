import java.util.*;

public class Flush extends PokerHand {

	private ArrayList<Integer> values;

	public Flush(ArrayList<Card> hand, ArrayList<Integer> values) {
		super(hand);
		this.values = values;
	}

	public static Flush makeHand(ArrayList<Card> hand) {
		ArrayList<Integer> values = new ArrayList<>();
		Card.Suit s = hand.get(0).suit();
		for(Card c : hand) {
			if(s != c.suit()) {
				return null;
			}
			values.add(c.value().getInt());
		}
		Collections.reverse(values);
		return new Flush(hand, values);
	}

	@Override
	public ArrayList<Integer> getRank() {
		return values;
	}

	public String toString() {
		String response = "Flush, ";
		for(Integer i : values) {
			response += Card.intToValue(i) + " ";
		}
		return response;
	}
}