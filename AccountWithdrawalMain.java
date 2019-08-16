package Assignment7_5;

class Account{
	int accID;
	String name;
	String email;
	int phone;
	double balance;
	
	public Account(int accID, String name, String email, int phone, double balance) {
		super();
		this.accID = accID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.balance = balance;
	}
	
	
}

public class AccountWithdrawalMain {
	public static void main(String[] args) {
		Account acc = new Account(1, "Iggy", "Iggy@email.com", 1508240073, 150000);
		WithdrawalThread wt = new WithdrawalThread(acc, 150, "Transaction 1");
		WithdrawalThread wt2 = new WithdrawalThread(acc, 1500, "Transaction 2");
		wt.start();
		wt2.start();
	}
}