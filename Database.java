import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.crypto.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	Connection conn;
	Statement stmt;
	ResultSet res;

	
   public Integer login(String[] args) {
	String password="";
      try (
         // Step 1: Construct a database 'Connection' object called 'conn'
        Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL only
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
         // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
		 
       Statement statement= conn.createStatement();
	  ){
         // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
         //  The query result is returned in a 'ResultSet' object called 'rset'.
         String strSelect = "select TOP 1 * from TABLE where username = '"+args[0]+"'";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
        res = stmt.executeQuery(strSelect);
		if(res==null){
			return 0;
		 }
		password = res.getString("password");
	  } catch(SQLException ex) {
		ex.printStackTrace();
	  }
		 
		 if(args[1]==password){
			return 1;
		 }
		 else{
			return 2;
		 } 
 
      }
       // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
	
	 

   public void registerAccount(Person p){
		String strSelect = "select title, price, qty from books";
   }

	
	
	public void addPerson(String first, String last,String accname, String user, String pass) {
		Person p = new Person(first, last, inplay.getNew(), accname, user, pass);
		hlist.put(p.getUser() + p.getPass(), p);
		alist.add(p);
		
	}
	
	public void addPerson(Person p) {
		hlist.put(p.getUser()+p.getPass(), p);
		alist.add(p);
	}
	
	public Boolean hasPerson(String userpass) {
		if(hlist.containsKey(userpass)) {
			return true;
		}
		else {
			return false;
		}
	}	
	
	public Person getPerson(String[] userpassword) {
		try (
         // Step 1: Construct a database 'Connection' object called 'conn'
        Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL only
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
         // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
		 
       Statement statement= conn.createStatement();
	  ){
         // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
         //  The query result is returned in a 'ResultSet' object called 'rset'.
         String strSelect = "select TOP 1 * from TABLE where username = '"+args[0]+"'";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
        res = stmt.executeQuery(strSelect);
	  } catch(SQLException ex) {
		ex.printStackTrace();
	  }
		 Person p=new Person(res.getString("firstName"),res.getString("lastName"),res.getInt("accountNumber"),res.getString("accountName"),res.getString("userName"),_);
		 return p;
		 
 
      }
	
	
	public Person getPerson(int i) {
		return null;
	}
	
	
	public Person find(String s) {
		for(int i = 0; i < alist.size(); i++) {
			if(alist.get(i).getUser() == s) {
				return alist.get(i);
			}
		}
		
		return null;
	}
	
	public void update(Person p) {
		hlist.remove(p.getHash());
		
		for(int i = 0; i < alist.size(); i++) {
			if(alist.get(i).getHash() == p.getHash()) {
				alist.remove(i);
				break;
			}
		}
		
		addPerson(p);
	}
	
	
	public double getTotal() {
		grandTotal = 0;
		for(int i = 0; i < alist.size(); i++) {
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
		
		for(int i = 0; i < alist.size(); i++) {
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
