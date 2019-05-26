
public class CAccount_synchronized {
	int balance;
	Object mutex = new Object();
	public long start_time;

	CAccount_synchronized(int balance){
		this.balance = balance;
		this.start_time = System.currentTimeMillis();
	}

	public int getBalance() {
		return this.balance;
	}

	public void deposit(int amount) {
		synchronized(mutex) {
			balance += amount;
			System.out.println("Scuceed! Your current balance: " + balance);
		}
	}

	public boolean withdraw(int amount) {
		synchronized(mutex) {
			if(balance < amount) {
				System.out.println("Withdraw failed! Your balance is not enough! Current balance:" + balance);
				return false;
			}
			balance -= amount;
			System.out.println("Scuceed! Your current balance: " + balance);
			return true;
		}
	}

}
