import java.util.*;

public class Deal {

	private ArrayList<Player> players = new ArrayList<>();
	private int smallBlind;

	private ArrayList<Card> deck = Card.shuffledDeck();
	private int index = 0;

	private ArrayList<Card> table = new ArrayList<>();
	private int pot = 0;
	private int toPay = 0;

	private ArrayList<Action> history = new ArrayList<>();
	private ArrayList<Pot> pots = new ArrayList<>();

	private int activePlayers;

	public Deal(ArrayList<Player> players, int smallBlind, int firstPlayer) {
		for(int i = 0; i < players.size(); ++i) {
			Player p = players.get((i + firstPlayer) % players.size());
			this.players.add(p);
			p.addHand(this);
		}
		activePlayers = players.size();
		this.smallBlind = smallBlind;
	}

	public void play() {
		preflop();
		if(checkEndOfRound())
			return;
		flop();
		if(checkEndOfRound())
			return;
		turn();
		if(checkEndOfRound())
			return;
		river();
		if(checkEndOfRound())
			return;
		showdown();
	}

	public void preflop() {
		for(Player p : players) {
			p.dealCards(nextCard(), nextCard());
			System.out.println(p);
		}

		toPay = 0;

		ArrayList<Player> toPlay = new ArrayList<Player>(players);

		execute(new Action(players.get(0), Action.Type.RAISE, smallBlind), toPlay);
		execute(new Action(players.get(1), Action.Type.RAISE, smallBlind * 2), toPlay);

		toPlay.add(players.get(1)); //check option

		while(!toPlay.isEmpty()) {
			Player next = toPlay.get(0);
			Action a = next.makeMove();
			execute(a, toPlay);
		}

		//collect money from table

		ArrayList<Player> toPay = new ArrayList<Player>(players);
		for(int i = 0; i < players.size() - activePlayers; ++i) {
			//make a pot, because this many people went in
			//int lowestAmount
			for(Player p : players) {
				remove lowest amount
			}
		}
		for(Player p : players) {
			if(p.isAllIn()) {

			}
			//find lowest amount, subtract lowest from everyone, and make pot
			//continue until everyone is equal to 0
		}

		//make pots here, if necessary
		
	}

	public void flop() {
		table.add(nextCard());
		table.add(nextCard());
		table.add(nextCard());
		System.out.println("flop:" + table);

		toPay = 0;

		ArrayList<Player> toPlay = new ArrayList<Player>(players);
		while(!toPlay.isEmpty()) {
			Player next = toPlay.get(0);
			Action a = next.makeMove();
			execute(a, toPlay);
		}
	}

	public void turn() {
		table.add(nextCard());
		System.out.println("turn: " + table);

		toPay = 0;

		ArrayList<Player> toPlay = new ArrayList<Player>(players);
		while(!toPlay.isEmpty()) {
			Player next = toPlay.get(0);
			Action a = next.makeMove();
			execute(a, toPlay);
		}
	}

	public void river() {
		table.add(nextCard());
		System.out.println("river: " + table);

		toPay = 0;

		ArrayList<Player> toPlay = new ArrayList<Player>(players);
		while(!toPlay.isEmpty()) {
			Player next = toPlay.get(0);
			Action a = next.makeMove();
			execute(a, toPlay);
		}
	}

	public void showdown() {
		if(pot != 0) {
			pots.add(new Pot(pot, ));
		}

		for(Pot pot : pots) {
			ArrayList<PokerHand> pokerHands = new ArrayList<>();
			PokerHand winningHand = null;
			Player winner = null;
			for(Player p : pot.getPlayers()) {
				ArrayList<Card> usableCards = p.usableCards();
				PokerHand bestHand = null;
				for(int i = 6; i >= 0; --i) {
					for(int j = i - 1; j >= 0; --j) {
						Card c1 = usableCards.remove(i);
						Card c2 = usableCards.remove(j);

						ArrayList<Card> hand = new ArrayList<>(usableCards);
						PokerHand currentHand = PokerHand.makePokerHand(hand);
						if(currentHand.compareTo(bestHand) > 0) {
							bestHand = currentHand;
						}

						usableCards.add(j,c2);
						usableCards.add(i,c1);
					}
				}
				if(bestHand.compareTo(winningHand) > 0) {
					winningHand = bestHand;
					winner = p;
				}
			}
			System.out.println();
			System.out.println(winningHand);
			System.out.println(winner + " takes " + pot);
			winner.takePot(pot.amount());
		}
	}

	public boolean checkEndOfRound() {
		if(players.size() == 1) {
			Player winner = players.get(0);
			winner.takePot(pot);
			System.out.println(winner + " takes " + pot);
		}
		return false;
	}

	public ArrayList<Card> table() {
		return table;
	}

	//assumes that action is valid
	//maybe we should have a checker?
	public void execute(Action a, ArrayList<Player> toPlay) {
		history.add(a);
		System.out.println("executing " + a);

		Player p = a.getPlayer();
		switch(a.getType()) {
			case FOLD:
				players.remove(a.getPlayer());
			case RAISE:
				int amount = a.getAmount();
				p.bet(amount);
				toPay = amount;
				updatePlayers(toPlay);
			case CALL:
				pot += toPay;
				p.bet(toPay);
		}
		if(p.isAllIn()) {
			activePlayers--;
		}
		toPlay.remove(0);
	}

	public void updatePlayers(ArrayList<Player> toPlay) {
		int currentQueueLength = toPlay.size();
		int indexInPlayers = players.indexOf(toPlay.get(currentQueueLength - 1)) + 1;
		while(currentQueueLength < activePlayers) {
			Player p = players.get(indexInPlayers % players.size());
			if(!p.isAllIn()) {
				toPlay.add(p);
				currentQueueLength++;
			}
			indexInPlayers++;
		}
	}

	public Card nextCard() {
		Card c = deck.get(index);
		index++;
		return c;
	}

	public int amountToPay() {
		return toPay;
	}

	public ArrayList<Action> history() {
		return history;
	}
}