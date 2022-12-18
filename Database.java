import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.crypto.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database implements java.io.Serializable{

	private String url = "jdbc:mysql://database-1.cafh5rnoy91y.us-east-2.rds.amazonaws.com:3306/java-db";
	private String username = "admin";
	private String password = "prakriti";
	private Connection conn;

	public Database() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established successfully!");
		} catch (SQLException e) {
			throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
		}
		
	}

	public Person login(String[] args) {
		String password = "";
		Person p = null;
		try (

				Statement statement = conn.createStatement();) {
			String strSelect = "select * from Person where userName = '" + args[0] + "' LIMIT 1";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			ResultSet res = statement.executeQuery(strSelect);
			
			if (res == null) {
				return null;
			}
			res.next();
 			password = res.getString("pwd");
			if (password.equals(args[1])) {
			p = new Person(res.getString("firstName"), res.getString("lastName"), res.getInt("accountNumber"),
					res.getString("accountName"), res.getString("userName"), res.getString("pwd"));
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;

	}

	public void registerAccount(Person p) {

		String queryString = "insert into Person values ('"+p.getFirst()+"','"+p.getLast()+"','"+p.getAccNum()+"','"+p.getAccount().getName()+"','"+p.getUser()+"','"+p.getPass()+"')";
		String queryStringAccount = "insert into Account2 values ('"+p.getAccNum()+"','"+p.getAccount().getName()+"',"+"0)";
		
		System.out.println("The SQL statement is: " + queryString + "\n"); // Echo For debugging
		System.out.println("The SQL statement is: " + queryStringAccount + "\n");
		try
			(Statement statement = conn.createStatement();){
				statement.executeUpdate(queryString);
				statement.executeUpdate(queryStringAccount);
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}

	

	public void addPerson(Person p) {
		hlist.put(p.getUser() + p.getPass(), p);
		alist.add(p);
	}

	public Boolean hasPerson(String userpass) {
		if (hlist.containsKey(userpass)) {
			return true;
		} else {
			return false;
		}
	}

	public Person getPerson(String[] args) throws SQLException {
		ResultSet res = null;
		try (
			Statement statement = conn.createStatement();) {
			String strSelect = "select TOP 1 * from TABLE where username = '" + args[0] + "'";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

			res = statement.executeQuery(strSelect);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		res.next();
		Person p = new Person(res.getString("firstName"), res.getString("lastName"), res.getInt("accountNumber"),
				res.getString("accountName"), res.getString("userName"), res.getString("pwd"));
		return p;

	}

	public Person getPerson(int i) {
		return null;
	}

	public Person find(int accNum) {
		ResultSet res=null;
		try (

			Statement statement = conn.createStatement();) {
			String strSelect = "select * from Person where accountNumber = "+accNum;
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			res = statement.executeQuery(strSelect);
			res.next();
			Person p = new Person(res.getString("firstName"), res.getString("lastName"),res.getInt("accountNumber"), res.getString("accountName"), res.getString("userName"), res.getString("pwd"));
			return p;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void update(Person p) {
		hlist.remove(p.getHash());

		for (int i = 0; i < alist.size(); i++) {
			if (alist.get(i).getHash() == p.getHash()) {
				alist.remove(i);
				break;
			}
		}

		addPerson(p);
	}

	public ArrayList<Integer> getAccountList() {
		ArrayList<Integer> accList= new ArrayList<Integer>();
		ResultSet res = null;
		try (
			Statement statement = conn.createStatement();) {
			String strSelect = "select accountNum from Account2";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

			res = statement.executeQuery(strSelect);
			while(res.next()){
				accList.add(res.getInt("accountNum"));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		this.grandTotal=accList.size();
		return accList;
	}

	public int getSize() {
		return alist.size();
	}

	public int getNum() {
		return inplay.getNew();
	}

	public ArrayList<String> getList() {

		ArrayList<String> names = new ArrayList<String>();

		for (int i = 0; i < alist.size(); i++) {
			names.add(alist.get(i).getUser());
		}

		return names;
	}

	public void sumUp() {

	}

	Hashtable<String, Person> hlist = new Hashtable<String, Person>();
	ArrayList<Person> alist = new ArrayList();
	ArrayList<Integer> accList = new ArrayList<>();
	RandomNums inplay = new RandomNums();
	double grandTotal;

	public void updateBalance(double balance, int accNum, String accName) {
		try (

			Statement statement = conn.createStatement();) {
			String strSelect = "update Account2 set balance = "+balance+" where accountNum = '" + accNum + "' and accType = '"+accName+"'";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			statement.executeUpdate(strSelect);
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	private void refresh(){
		this.accList= getAccountList();
		this.grandTotal=this.accList.size();
	}
	public String getTotal() {
		refresh();
		return String.valueOf(this.accList.size());
	}

	public String getGrandTotal() {
		ResultSet res=null;
		try (

			Statement statement = conn.createStatement();) {
			String strSelect = "select sum(Balance) from Account2";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			res = statement.executeQuery(strSelect);
			res.next();
			return res.getString("sum(Balance)");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";

		
	}

	public double getBalance(int accNum) {
		ResultSet res=null;
		try (

			Statement statement = conn.createStatement();) {
			String strSelect = "select (Balance) from Account2 where accountNum = "+accNum;
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			res = statement.executeQuery(strSelect);
			res.next();
			return res.getDouble("Balance");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0.0;
	}

}
