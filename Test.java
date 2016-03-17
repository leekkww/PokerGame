import java.util.*;

public class Test {
	
	public static void main(String[] args) {

		//initialize players
		ArrayList<Player> players = new ArrayList<>();
		players.add(new CMDPlayer("jolee", 2000));
		players.add(new CMDPlayer("leek", 3000));
		players.add(new CMDPlayer("leech", 1000));

		//initialize game
		//PokerGame game = new PokerGame(players);
	}

	public static void frequencyTest() {
		int[] freq = new int[9];
		for(int i = 0; i < 1000000; i++) {
			ArrayList<Card> hand = new ArrayList<>();
			ArrayList<Card> deck = Card.shuffledDeck();
			for(int j = 0; j < 5; j++) {
				hand.add(deck.get(j));
			}
			freq[PokerHand.handTypeValue((PokerHand.makePokerHand(hand)).getClass())]++;
		}

		for(int i = 0; i < 9; i++) {
			System.out.println(freq[i]);
		}
	}
}