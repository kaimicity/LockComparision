/**
 * 
 */

/**
 * @author sunhantao
 *
 */
public class Main {

	public static void main(String[] args) {
//		CAccount_spinlock myAccount;
//		myAccount = new CAccount_spinlock(0);
//		CAccount_synchronized myAccount;
//		myAccount = new CAccount_synchronized(0);
		CAccount_Lockfree myAccount;
		myAccount = new CAccount_Lockfree(0);
		
		for(int i = 0; i < 1000; i++) {
			Thread w = new Thread(new Runnable() {

				@Override
				public void run() {
					myAccount.deposit(10);
					myAccount.withdraw(5);
					myAccount.withdraw(5);
					System.out.println(myAccount.getBalance());
					System.out.println(System.currentTimeMillis() - myAccount.start_time);
				}
				
			});
			w.start();
		}
	}
	
	
}
