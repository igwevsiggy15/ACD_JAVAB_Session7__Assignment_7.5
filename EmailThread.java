package Assignment7_5;

public class EmailThread extends Thread{
	Account acc;
	double amount;
	
	public EmailThread(Account acc, double amount, String name) {
		super(name + " EMAIL");
		this.acc = acc;
		this.amount = amount;
	}
	
	public EmailThread(String string, String name) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println(this.getName() + ": Emailing " + this.acc.email);
		EmailThread lt = new EmailThread(this.getName() + ": Email sent[Account Holder: " + acc.name + "]", this.getName());
		lt.start();
				
	}
}