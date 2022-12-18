	
public class Account implements java.io.Serializable {
	

	
	public Account(String name, int accNum) {
		this.name = name;
		this.accNum = accNum;
		Database db = new Database();
		this.balance = db.getBalance(accNum);
	}
	
	public void deposit(double cash) {
		balance += cash;
		Database db = new Database();
		db.updateBalance(balance,accNum,this.name);
	}
	
	public double withdraw(double cash) {

		if(balance >= cash) {
			balance -= cash;
			Database db = new Database();
			db.updateBalance(balance,accNum,this.name);
			return cash;
		}
		else {
			return 0;
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}

	int accNum;
	double balance;
	String name;
}