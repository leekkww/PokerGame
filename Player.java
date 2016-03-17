import java.util.*;

public abstract class Player {
	
	private String name;
	private int balance = 0;
	private int amountOnTable = 0;
	private Card[] dealtCards = new Card[2];
	private boolean allIn = false;

	private Deal currentRound;

	public Player(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	public Deal getDeal() {
		return currentRound;
	}

	public int getBalance() {
		return balance;
	}

	public void addHand(Deal newRound) {
		this.currentRound = newRound;
	}

	public void dealCards(Card c1, Card c2) {
		dealtCards[0] = c1;
		dealtCards[1] = c2;
	}

	public ArrayList<Card> usableCards() {
		ArrayList<Card> cards = new ArrayList<>(currentRound.table());
		cards.add(dealtCards[0]);
		cards.add(dealtCards[1]);
		return cards;
	}

	public int amountOnTable() {
		return amountOnTable;
	}

	public void takeAmountOnTable(int amount) {
		amountOnTable -= amount;
	}

	public void takePot(int winnings) {
		balance += winnings;
	}

	public void bet(int amount) {
		if(balance > amount) {
			balance -= amount;
			amountOnTable += amount;
		} else { //assume player goes all in
			goAllIn();
		}
	}

	public void goAllIn() {
		amountOnTable += balance;
		balance = 0;
		allIn = true;
	}

	public boolean isAllIn() {
		return allIn;
	}

	public String toString() {
		return name + " with " + dealtCards[0] + " and " + dealtCards[1];
	}

	/*
	 * Should include betting (raising) or calling.
	 * Assumes that the moves are valid (no betting over balance, no betting under balance)
	*/
	public abstract Action makeMove();
}