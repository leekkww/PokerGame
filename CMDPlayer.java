import java.io.*;
import java.util.*;

public class CMDPlayer extends Player {

	public CMDPlayer(String name, int balance) {
		super(name,balance);
	}
	
	@Override
	public Action makeMove() {
		Scanner scan = new Scanner(System.in);
		System.out.println("To play: " + this);
		while(scan.hasNext()) {
			System.out.print("> ");
			String c = scan.next();
			switch(c) {
				case "fold": return new Action(this, Action.Type.FOLD);
				case "bet": case "raise":
					int increase = scan.nextInt();
					while(increase <= getDeal().amountToPay()) {
						System.out.println("Raise amount must be greater than " + getDeal().amountToPay());
						System.out.println("Please enter new raise amount");
						increase = scan.nextInt();
					}
					return new Action(this, Action.Type.RAISE, increase);
				case "call": case "check":
					return new Action(this, Action.Type.CALL);
				default: return new Action(this, Action.Type.FOLD);
			}
		}
		return new Action(this, Action.Type.FOLD);
	}
}