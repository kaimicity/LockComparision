/**
 * 
 */

/**
 * @author sunhantao
 *
 */
import java.util.concurrent.atomic.*;
public class CAccount_spinlock {
	int balance;
	 AtomicBoolean lock = new AtomicBoolean();
	public long start_time;
	
	CAccount_spinlock(int balance){
		this.balance = balance;
		this.lock.set(false);
		this.start_time = System.currentTimeMillis();
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void deposit(int amount) {
		while(!lock.compareAndSet(false, true)) {
		}
		balance += amount;
		System.out.println("Scuceed! Your current balance: " + balance);
		lock.set(false);
	}
	
	public boolean withdraw(int amount) {
		while(!lock.compareAndSet(false, true)) {
		}
		if(balance < amount) {
			System.out.println("Withdraw failed! Your balance is not enough! Current balance:" + balance);
			lock.set(false);
			return false;
		}
		balance -= amount;
		System.out.println("Scuceed! Your current balance: " + balance);
		lock.set(false);
		return true;
	}
}
