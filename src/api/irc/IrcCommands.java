package api.irc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * IRC commands with corresponding methods
 * @author tim
 *
 */

public class IrcCommands extends Thread {
	Socket socket; 
	BufferedWriter writer;
	BufferedReader reader;

	String channel;

	/**
	 * Come back from being away
	 * @throws IOException 
	 */
	public void away() throws IOException {
		writer.write("AWAY " + "\n");
		writer.flush();
	}

	/**
	 * Become away
	 * @param message
	 * @throws IOException 
	 */
	public void away(String message) throws IOException {
		writer.write("AWAY " + ":" + message + "\n");
		writer.flush();
	}
	/**
	 * send a custom command 
	 * @param command command to send
	 * @throws IOException
	 */
	public void customCommand(String command) throws IOException {
		writer.write(command + "\n");
		writer.flush();
	}
	/**
	 * Returns a string with a message typed by another user
	 * @param line
	 * @return
	 */
	//TODO very bad string manipulations, needs to be fixed
	private String display(String line) {
		StringBuffer buffer = new StringBuffer();
		char[] characters = line.toCharArray();
		for(int i = 0; i<characters.length; i++) {
			if(characters[i] == ':') {
				for(int j=i+1; j<characters.length; j++) {
					buffer.append(characters[j]);
					if(characters[j] == '!') {
						i = j;
						break;
					}
				}

			}
		}
		String string = buffer.toString();
		CharSequence sequenceTarget = "!";
		CharSequence sequenceReplace = " : ";
		string =  string.replace(sequenceTarget, sequenceReplace);
		return string;
	} 

	public void getTime() throws IOException {
		writer.write("TIME " + "\n");
		writer.flush();
	}

	/**
	 * Join a channel, 
	 * @param channel channel to join
	 * @throws IOException 
	 */
	public void join(String channel) throws IOException {
		this.channel = channel;

		writer.write("JOIN " + channel + "\r\n");
		//writer.write("PRIVMSG " + channel + " :Welcome message\n");
		writer.flush();

	}

	/**
	 * List the channels 
	 * @throws IOException 
	 */
	public void listChannels() throws IOException {
		writer.write("LIST "+"\n");
		writer.flush();
	}

	/**
	 * list services
	 * @throws IOException
	 */
	public void listServices() throws IOException {
		writer.write("SERVLIST " + "\n");
		writer.flush();
	}

	/**
	 * Lists all users
	 * @throws IOException
	 */
	public void listUsers() throws IOException {
		writer.write("NAMES "+"\n");
		writer.flush();
	}
	/**
	 * List users on channel
	 * @param channel 
	 * @throws IOException
	 */
	public void listUsers(String channel) throws IOException {
		writer.write("NAMES " + channel +"\n");
		writer.flush();
	}

	/**
	 * Login with parameters
	 * @param server
	 * @param port
	 * @param nick
	 * @param login
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void login(String server, int port, String nick, String login) throws UnknownHostException, IOException {
		socket = new Socket(server,port);
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		writer.write("NICK " + nick + "\r\n");
		writer.write("USER " + login + " 0 * : \r\n");
		writer.flush( );
		String line = null;
		while ((line = reader.readLine( )) != null) {
			if (line.indexOf("004") >= 0) {
				break;
			}
			else if (line.indexOf("433") >= 0) {
				System.err.println("Nickname is already in use.");
				return;
			}
		}

	}
	/**
	 * Keeps you connected to server and displays messages
	 * @throws IOException 
	 */
	private void pingpong() throws IOException {
		String line = null;
		while ((line = reader.readLine( )) != null) {
			if (line.toLowerCase( ).startsWith("PING ")) {
				writer.write("PONG " + line.substring(5) + "\r\n");
				writer.write("PRIVMSG " + channel + " :pinged!\r\n");
				writer.flush( );
			}
			else {
				System.out.println(display(line));
			}
		}

	}
	public void quit() throws IOException {
		writer.write("QUIT " + "\n");
		writer.flush();
	}
	public void run() {
		try {
			pingpong();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Send a message to everyone in channel
	 * @param message message to send everyone
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws IOException {
		writer.write("PRIVMSG " + channel + " :"+message+"\n");
		writer.flush();

	}
	/**
	 * Send a message to everyone in channel
	 * @param message message to send everyone
	 * @throws IOException 
	 */
	public void sendPrivateMessage(String nick, String message) throws IOException {
		writer.write("PRIVMSG " + nick + " :"+message+"\n");
		writer.flush();

	}
	public void whoIs(String nick)  throws IOException {
		writer.write("WHOIS " + nick + "\n");
		writer.flush();
	}

	public void whoWas(String nick)  throws IOException {
		writer.write("WHOWAS " + nick + "\n");
		writer.flush();
	}
}
