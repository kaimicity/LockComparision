/**
 * 
 */

/**
 * @author sunhantao
 *
 */

import java.util.concurrent.atomic.*;
public class CAccount_Lockfree {
	AtomicInteger balance;
	public long start_time;

	CAccount_Lockfree(int balance){
		this.balance = new AtomicInteger(balance);
		this.start_time = System.currentTimeMillis();
	}

	public int getBalance() {
		return balance.get();
	}

	public void deposit(int amount) {
		int myBalance = balance.get();
		while(!balance.compareAndSet(myBalance, myBalance + amount)) {
			myBalance = balance.get();
		}
		System.out.println("Scuceed! Your current balance: " + (myBalance + amount));
	}

	public boolean withdraw(int amount) {
		int myBalance = balance.get();
		if(myBalance < amount) {
			System.out.println("Withdraw failed! Your balance is not enough! Current balance:" + balance);
			return false;
		} else {
			while(!balance.compareAndSet(myBalance, myBalance - amount)) {
				myBalance = balance.get();
				if(myBalance < amount) {
					System.out.println("Withdraw failed! Your balance is not enough! Current balance:" + balance);
					return false;
				}
			}
			System.out.println("Scuceed! Your current balance: " + (myBalance - amount));
			return true;
		}
	}
}
