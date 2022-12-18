import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServerThread implements Runnable {
	Database base;
	public ServerThread(Socket connection, Database base) {
		try {
			this.connection = connection;
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			this.base = base;
		} catch(Exception e) { 
			e.printStackTrace();
		};
	}
	
	public void run() {
		Boolean flag = true;
		Object o;
		while(flag) {
			try {
				o = input.readObject();
				if(o instanceof String[]) {
					recieve((String[]) o);
				}
				else if(o instanceof Integer) {
					recieve((Integer) o);
				}
				else if(o instanceof Person) {
					recieve((Person) o);
				}
				else if(o instanceof Integer[]){
					recieve((Integer[]) o);
				}	
				else if(o instanceof Boolean) {
					input.close();
					output.close();
					connection.close();
					flag = false;
				}
			} catch(Exception e) {
				System.out.println(e.getStackTrace());
			 };
		} 
	}

	public void recieve(String[] args) throws SQLException{
		Person res=base.login(args);
		
		try{
			output.writeObject(res);
		}catch(IOException ioException) { };
	}
	
	public void recieve(int n) throws IOException {
		if(n > 0) {
			person.getAccount().deposit(n);
			sendPerson(person);
		}
		else if(n < 0) {
			person.getAccount().withdraw(n*-1);
			sendPerson(person);
		}
		else { }
		
	}


	//Recieve a person object to create a new account.
	public void recieve(Person p) throws IOException{
		base.registerAccount(p);
	}

	//Recieve integer array to parse a transfer.
	public void recieve(Integer[] arr){
		base.transfer(arr[0],arr[1]);
	}
	

	//Send a person object to the client
	private void sendPerson(Person p) {
		try {
			output.writeObject(p);
			output.flush();
		} catch(IOException ioException) { };
	}
	
	//close down
	private void closeDown() {
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Person person;
}
