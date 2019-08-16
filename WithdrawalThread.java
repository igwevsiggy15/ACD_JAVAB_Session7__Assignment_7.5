package Assignment7_5;

public class WithdrawalThread extends Thread {
	Account acc;
	double amount;
	
	public WithdrawalThread(Account acc, double amount, String name) {
		super(name + " WT");
		this.acc = acc;
		this.amount = amount;
	}
	
	@Override
	public void run() {
			if (this.acc.balance >= this.amount) {
				System.out.println(this.getName() + ": Withdrawing amount: " + amount);
				this.acc.balance -= amount;
				System.out.println(this.getName() + ": New balance: " + this.acc.balance);
				
				EmailThread em = new EmailThread(acc, amount, this.getName());
				SMSThread tx = new SMSThread(acc, amount, this.getName());
				
				em.start();
				tx.start();
				
			} else {
				System.out.println(this.getName() + ": Insufficient Funds.");
			}
	}
}