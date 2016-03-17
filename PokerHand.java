import java.util.*;
import java.lang.reflect.*;

public abstract class PokerHand implements Comparable<PokerHand> {
	
	private ArrayList<Card> hand = new ArrayList<>();
	private static ArrayList<Class<? extends PokerHand>> handType = new ArrayList<>();

	public PokerHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public static PokerHand makePokerHand(ArrayList<Card> hand) {
		Collections.sort(hand);

    	handType.add(StraightFlush.class);
    	handType.add(FourOfAKind.class);
    	handType.add(FullHouse.class);
    	handType.add(Flush.class);
    	handType.add(Straight.class);
    	handType.add(ThreeOfAKind.class);
    	handType.add(TwoPair.class);
    	handType.add(Pair.class);
    	handType.add(HighCard.class);

		for(Class<? extends PokerHand> c : handType) {
			try {
				Class[] cArg = new Class[1];
        		cArg[0] = ArrayList.class;
				Method m = c.getDeclaredMethod("makeHand", cArg);
				PokerHand h = c.cast(m.invoke(null, hand));
				if(h != null)
					return h;
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		return null;
	}

	public ArrayList<Card> hand() {
		return hand;
	}
	@Override
	public int compareTo(PokerHand p) {
		if(p == null) {
			return 1;
		}
		int diff = handType.indexOf(p.getClass()) - handType.indexOf(getClass());
		if (diff != 0) {
			return diff;
		} else {
			for(int i = 0; i < getRank().size(); ++i) {
				int d = getRank().get(i) - p.getRank().get(i);
				if(d > 0) {
					return 1;
				} else if(d < 0) {
					return -1;
				}
			}
			return 0;
		}
	}

	public static int handTypeValue(Class<? extends PokerHand> c) {
		return 8 - handType.indexOf(c);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public static int[] getFrequencies(ArrayList<Card> hand) {
		int[] values = new int[13];
		for(Card c : hand) {
			values[c.value().getInt()]++;
		}
		return values;
	}

	public abstract ArrayList<Integer> getRank();

	@Override
	public String toString() {
		return hand.toString();
	}
}