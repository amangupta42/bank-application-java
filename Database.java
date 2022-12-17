import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.crypto.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

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
		Person p = new Person();
		try (

				Statement statement = conn.createStatement();) {
			// Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
			// The query result is returned in a 'ResultSet' object called 'rset'.
			String strSelect = "select * from Person where userName = '" + args[0] + "' LIMIT 1";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			
			ResultSet res = statement.executeQuery(strSelect);
			
			if (res == null) {
				return null;
			}
			password = res.getString("pwd");
			if (password == args[1]) {
			p = new Person(res.getString("firstName"), res.getString("lastName"), res.getInt("accountNumber"),
					res.getString("accountName"), res.getString("userName"), res.getString("pwd"));
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;

	}
	// Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK
	// 7)

	public void registerAccount(Person p) {

		String queryString = "insert into Person values ('"+p.getFirst()+"','"+p.getLast()+"','"+p.getAccNum()+"','"+p.getAccount().getName()+"','"+p.getUser()+"','"+p.getPass()+"')";
		System.out.println("The SQL statement is: " + queryString + "\n"); // Echo For debugging
		try
			(Statement statement = conn.createStatement();){
				statement.executeUpdate(queryString);
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}

	public void addPerson(String first, String last, String accname, String user, String pass) {
		Person p = new Person(first, last, inplay.getNew(), accname, user, pass);
		hlist.put(p.getUser() + p.getPass(), p);
		alist.add(p);

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
				// Step 1: Construct a database 'Connection' object called 'conn'

				// The format is: "jdbc:mysql://hostname:port/databaseName", "username",
				// "password"
				// Step 2: Construct a 'Statement' object called 'stmt' inside the Connection
				// created

				Statement statement = conn.createStatement();) {
			// Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
			// The query result is returned in a 'ResultSet' object called 'rset'.
			String strSelect = "select TOP 1 * from TABLE where username = '" + args[0] + "'";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

			res = statement.executeQuery(strSelect);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		Person p = new Person(res.getString("firstName"), res.getString("lastName"), res.getInt("accountNumber"),
				res.getString("accountName"), res.getString("userName"), res.getString("pwd"));
		return p;

	}

	public Person getPerson(int i) {
		return null;
	}

	public Person find(String s) {
		for (int i = 0; i < alist.size(); i++) {
			if (alist.get(i).getUser() == s) {
				return alist.get(i);
			}
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

	public double getTotal() {
		grandTotal = 0;
		for (int i = 0; i < alist.size(); i++) {
			grandTotal += alist.get(i).getAccount().getBalance();
		}
		return grandTotal;
	}

	public int getSize() {
		return alist.size();
	}

	public int getNum() {
		return inplay.getNew();
	}

	public ArrayList getList() {

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
	RandomNums inplay = new RandomNums();
	double grandTotal;

}
