import java.util.ArrayList;
import java.util.Collections;

/**
 * Class of a Card, for Poker. No Jokers.
 */
public final class Card implements Comparable {
    
    private Value value;
    private Suit suit;

    public enum Value {

        TWO(0), THREE(1), FOUR(2), FIVE(3), SIX(4), SEVEN(5), 
        EIGHT(6), NINE(7), TEN(8), JACK(9), QUEEN(10), KING(11), ACE(12);

        int value;
        
        Value(int value) {
            this.value = value;
        }

        public int getInt() {
            return value;
        }
        public String toString() {
            return this.name();
        }
    }

    public enum Suit {
        CLUBS(0), DIAMONDS(1), HEARTS(2), SPADES(3);

        int value;

        Suit(int value) {
            this.value = value;
        }

        public int getInt() {
            return value;
        }
        public String toString() {
            return this.name();
        }
    }
    
    //Always use this
    public Card(Value v, Suit s) {
        value = v;
        suit = s;
    }
    
    public Suit suit() {return suit;}
    public Value value() {return value;}
    
    public boolean equals(Card c) {
        return value == c.value && suit == c.suit;
    }
    
    //returns what card is higher, e.g. which is going to win in a head to head
    //compareTo == 0 does not mean the cards are equal
    public int compareTo(Object o) {
        Card c = (Card)o;
        return value.getInt() - c.value.getInt();
    }

    /**
     * @return  the string version of the trick
     */
    public String toString() {
        return value.toString() + " of " + suit.toString();
    }

    public static Suit intToSuit(int i) {
        return suits()[i];
    }

    public static Value intToValue(int i) {
        return values()[i];
    }
    
    public static Suit[] suits() {
        Suit[] suits = new Suit[4];
        suits[0] = Suit.DIAMONDS;
        suits[1] = Suit.CLUBS;
        suits[2] = Suit.HEARTS;
        suits[3] = Suit.SPADES;
        return suits;
    }
    
    public static Value[] values() {
        Value[] value = new Value[13];
        value[0] = Value.TWO;
        value[1] = Value.THREE;
        value[2] = Value.FOUR;
        value[3] = Value.FIVE;
        value[4] = Value.SIX;
        value[5] = Value.SEVEN;
        value[6] = Value.EIGHT;
        value[7] = Value.NINE;
        value[8] = Value.TEN;
        value[9] = Value.JACK;
        value[10] = Value.QUEEN;
        value[11] = Value.KING;
        value[12] = Value.ACE;
        return value;
    }

    public static ArrayList<Card> shuffledDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        for(Card.Value v : Card.values())
        {
            for(Card.Suit s : Card.suits())
            {
                deck.add(new Card(v,s));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

}