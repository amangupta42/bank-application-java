import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientUI extends JFrame {


	// set up frame details
	public ClientUI() {
		super("Client");

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                flag = false;
				closeDown();
            }
        });

		panel = new JPanel();
		add(panel);

		launch();
	}

	// init frame with components
	public void launch() {

		panel.removeAll();

		panel.setLayout(new FlowLayout());

		// background picture
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getResource("java2.jpg")));
		background.setLayout(new BorderLayout());

		// transparant panel
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setOpaque(true);

		final JLabel user = new JLabel("Username:");
		top.add(user);

		final JTextField username = new JTextField();
		username.setColumns(7);
		top.add(username);

		final JLabel pass = new JLabel("Password:");
		top.add(pass);

		final JPasswordField password = new JPasswordField();
		password.setColumns(7);
		top.add(password);

		background.add(top, BorderLayout.NORTH);

		JPanel middle = new JPanel();
		middle.setLayout(new FlowLayout());
		middle.setOpaque(false);


		middle.add(new JButton(new AbstractAction("Login") {
			public void actionPerformed(ActionEvent e) {
				sendUserPass(new String[]{username.getText() , password.getText()});
				System.out.println(username.getText() + password.getText().toString());
	
			}
		}));

		middle.add(new JButton(new AbstractAction("Register") {
			public void actionPerformed(ActionEvent e) {
				registerScreen();
			}
		}));

		background.add(middle, BorderLayout.CENTER);

		panel.add(background, BorderLayout.NORTH);

		panel.updateUI();

	}

	private void loginScreen() {

		panel.removeAll();

		panel.setLayout(new BorderLayout());

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getResource("java2.jpg")));
		background.setLayout(new BorderLayout());

		final JPanel content = new JPanel();
		content.setOpaque(false);
		content.setLayout(new FlowLayout(0, 10, 170));
		content.add(new JLabel("Current Balance:", SwingConstants.CENTER));
		content.add(new JLabel("$" + person.getAccount().getBalance()));
		background.add(content, BorderLayout.CENTER);

		JPanel tabs = new JPanel();
		GridLayout grid = new GridLayout(1, 5);
		grid.setHgap(15);
		tabs.setLayout(grid);
		tabs.setOpaque(false);
		tabs.add(new JButton(new AbstractAction("Balance") {
			public void actionPerformed(ActionEvent ae) {
				content.removeAll();

				content.add(new JLabel("Current Balance:",SwingConstants.CENTER));
				content.add(new JLabel("$" + person.getAccount().getBalance() + ""));

				content.updateUI();
			}
		}));
		tabs.add(new JButton(new AbstractAction("Deposit") {
			public void actionPerformed(ActionEvent ae) {
				content.removeAll();

				content.add(new JLabel("Amount:"));
				final JTextField money = new JTextField(10);
				content.add(money);
				content.add(new JButton(new AbstractAction("Deposit") {
					public void actionPerformed(ActionEvent ae) {
						int input = JOptionPane.showConfirmDialog(panel, "Do you want to proceed?", "Select an Option...",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(input==0){
						try {
							int amount = Integer.parseInt(money.getText());
							output.writeObject(amount);
							person.getAccount().deposit(amount);
							JOptionPane.showMessageDialog(panel, "Deposit success");
							money.setText("");
						} catch (IOException ioException) { };
					}
				
				else{
					money.setText("");
				}
				}
			}));
				
				content.updateUI();
			}
		}));
		tabs.add(new JButton(new AbstractAction("Withdraw") {
			public void actionPerformed(ActionEvent ae) {
				content.removeAll();

				content.add(new JLabel("Amount:"));
				final JTextField money = new JTextField(10);
				content.add(money);
				content.add(new JButton(new AbstractAction("Withdraw") {
					public void actionPerformed(ActionEvent ae) {
						int input = JOptionPane.showConfirmDialog(panel, "Do you want to proceed?", "Select an Option...",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(input==0){
						try {
							int amount = Integer.parseInt(money.getText());
							double res = person.getAccount().canWithdraw(amount);
							
							
							if(res==0){
								JOptionPane.showMessageDialog(panel, "Withdraw amount exceeds current balance");
							}
							
							else{
							output.writeObject(amount*-1);
							JOptionPane.showMessageDialog(panel, "Withdraw success");
							}
							money.setText("");
						} catch (IOException ioException) { };
					}
					else{
						money.setText("");
					}}
				}));

				content.updateUI();
			}
		}));
		tabs.add(new JButton(new AbstractAction("Transfer") {
			public void actionPerformed(ActionEvent ae) {
				content.removeAll();

				content.add(new JLabel("Amount:"));
				final JTextField money = new JTextField(10);
				content.add(money);
				content.add(new JLabel("Recipient Acc/N:"));
				final JTextField acc = new JTextField(10);
				content.add(acc);
				content.add(new JButton(new AbstractAction("Transfer") {
					public void actionPerformed(ActionEvent ae) {
						int input = JOptionPane.showConfirmDialog(panel, "Do you want to proceed?", "Select an Option...",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(input==0){
						try {
							
							int amount = Integer.parseInt(money.getText());
							int accNum = Integer.parseInt(acc.getText());
							double res = person.getAccount().canWithdraw(amount);
							if(res==0){
								JOptionPane.showMessageDialog(panel, "Withdraw amount exceeds current balance");
							}
							else{
								output.writeObject(new Integer[]{amount,accNum});
								person.getAccount().withdraw(amount);
								JOptionPane.showMessageDialog(panel, "Transfer success");
							}
							money.setText("");
							acc.setText("");
						} catch (IOException ioException) { };
					}
					else{
						money.setText("");
						acc.setText("");
					}}
				}));

				content.updateUI();
			}
		}));

		tabs.add(new JButton(new AbstractAction("Logout") {
			public void actionPerformed(ActionEvent ae) {
				content.removeAll();

				content.add(new JButton(new AbstractAction("Logout") {
					public void actionPerformed(ActionEvent ae) {
						person = null;
						JOptionPane.showMessageDialog(panel, "Logout success");
						launch();
					}
				}));

				content.updateUI();
				
			}
		}));
		
		background.add(tabs, BorderLayout.NORTH);

		panel.add(background, BorderLayout.CENTER);
		panel.updateUI();
	}

	private void registerScreen() {

		panel.removeAll();

		panel.setLayout(new BorderLayout());

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getResource("java2.jpg")));
		background.setVisible(false);

		// Information form components

		background.setVisible(false);
		GridLayout grid = new GridLayout(0, 2, 1, 1);
		grid.setHgap(10);
		grid.setVgap(40);
		background.setLayout(grid);

		background.add(new JLabel("First Name:"));
		final TextField fn = new TextField();
		background.add(fn);

		background.add(new JLabel("Last Name:"));
		final TextField ln = new TextField();
		background.add(ln);

		background.add(new JLabel("Account Name:"));
		final JComboBox<String> an = new JComboBox<String>();
		an.addItem("Checking");
		an.addItem("Savings");
		background.add(an);

		background.add(new JLabel("Username:"));
		final TextField un = new TextField();
		background.add(un);

		background.add(new JLabel("Password"));
		final JPasswordField pwd1 = new JPasswordField();
		background.add(pwd1);

		background.add(new JLabel("Verify Password"));
		final JPasswordField pwd2 = new JPasswordField();
		background.add(pwd2);

		final JButton status = new JButton("Back");
		status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				launch();
			}
		});
		background.add(status);

		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(fn.getText().isEmpty() || ln.getText().isEmpty() || un.getText().isEmpty() || pwd1.getText().isEmpty() || pwd2.getText().isEmpty()){
					JOptionPane.showMessageDialog(panel, "Check all fields have values and retry");
				}
				else{
				 if (pwd1.getText().toString().equals(pwd2.getText().toString())) {
				
						Person p = new Person(fn.getText(),ln.getText(),rNums.getNew(),an.getSelectedItem().toString(),un.getText(),pwd1.getText());
	
						sendInfo(p);
						JOptionPane.showMessageDialog(panel, "Account created: Login using credentials");
						launch();
				}
				else{
					JOptionPane.showMessageDialog(panel, "Passwords do not match, please re-enter");
					pwd1.setText("");
					pwd2.setText("");
				}
			}
				

				
			}
		});
		background.add(create);

		background.setVisible(true);

		panel.add(background);
		panel.updateUI();
	}

	// connect to server
	public void startRunning() {
		
		
		serverIP = "127.0.0.1";
		
		flag = true;

		try {
			connectToServer();
			setupStreams();
			whileConnected();
		} catch (EOFException eofException) {
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeDown();
		}
	}

	// connecting to a server
	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(serverIP), 1337);
	}

	// Setting up the stream
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	// while chatting with server
	private void whileConnected() throws IOException {
		Object o;
		do {
			try {
				o = input.readObject();
				if(o instanceof Person && o != null) {
					person = (Person) o;
					loginScreen();
				}
				else{
					JOptionPane.showMessageDialog(panel, "Invalid credentials");
					launch();
				}
			} catch (ClassNotFoundException classNotFoundException) { };
		} while(flag);
	}
	

	//Send a person object to the server
	private void sendInfo(Person p) {
		try {
			output.writeObject(p);
			output.flush();
		} catch(IOException ioException) {
			
		}
	}
	
	//Function that sends a string array with elements as user and password
	private void sendUserPass(String[] userpass) {
		try {
			output.writeObject(userpass);
			output.flush();
		} catch(IOException ioException) { }
	}

	// close Stream and Socket
	private void closeDown() {
		try {
			System.out.println("Client closed program!");
			output.writeObject(false);
			input.close();
			output.close();
			connection.close();
			System.exit(0);
		} catch (IOException ioException) { };
	}

	
	
	// Frame variables
	private JPanel panel;
	private Person person = null;
	private Boolean flag;
	
	// Server variables
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String serverIP;
	private RandomNums rNums= new RandomNums();;
	

}
