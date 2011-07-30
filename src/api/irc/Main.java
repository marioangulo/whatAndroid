package api.irc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Main class
 * 
 * @author tim
 * 
 */
public class Main {
	IrcCommands irc;
	Thread myThread;
	InputParser inputParser;

	String server = "irc.what-network.net";
	String nick = "irc test";
	String login = "";
	String channel = "whatAndroidIRCTest";
	int port = 6667;

	public Main() throws IOException {
		init();
		inputListener();
	}

	/**
	 * Listen for text input from user
	 * 
	 * @throws IOException
	 */
	private void inputListener() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String input;
		while (true) {
			input = scanner.nextLine();
			if (input.startsWith("/")) {
				getCommand(input);
			} else {
				irc.sendMessage(input);
			}
		}
	}

	/**
	 * Get and run corresponding command
	 * 
	 * @param input
	 * @throws IOException
	 */
	private void getCommand(String input) throws IOException {
		inputParser = new InputParser();
		input = input.replace("/", "");
		input = input.toUpperCase();

		inputParser.parseInput(input);
		System.err.println("Commands: " + inputParser.getCommand());
		System.err.println("Parameters: " + inputParser.getParams().toString());

		switch (ListOfCommands.valueOf(inputParser.getCommand())) {
		case AWAY:
			if (legalParams()) {
				irc.away();
			} else {
				irc.away(inputParser.getParams());
			}
			break;
		case JOIN:
			if (legalParams()) {
				System.err.println("Specify channel to join");
			} else {
				irc.join(inputParser.getParams());
			}
			break;
		case KILL:
			System.exit(0);
			break;
		case LIST:
			irc.listChannels();
			break;
		case LISTSERVICES:
			irc.listServices();
			break;
		case NAMES:
			irc.listUsers();
			break;
		case QUIT:
			irc.quit();
			break;
		case WHOIS:
			if (legalParams()) {
				irc.whoIs(inputParser.getParams());
			}
			break;
		case WHOWAS:
			if (legalParams()) {
				irc.whoWas(inputParser.getParams());
			}
			break;
		default:
			System.err.println("Command doesn't exsist");
			break;
		}
	}

	/**
	 * Checks if parameters are allowed
	 * 
	 * @return
	 */
	private boolean legalParams() {
		if (inputParser.getParams().equals(""))
			return false;
		else
			return true;
	}

	private void init() throws UnknownHostException, IOException {
		irc = new IrcCommands();
		irc.login(server, port, nick, login);
		irc.join(channel);

		myThread = new Thread(irc);
		myThread.start();

		// sleep for 1 second to allow client to connect
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}
}
