package api.irc;
import java.util.ArrayList;
/**
 * Parse user input
 * @author tim
 *
 */

public class InputParser {
	private String command;
	private ArrayList<String> params = new ArrayList<String>();

	/**
	 * 
	 * @return command entered by user
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * 
	 * @return parameters entered by user
	 */
	public String getParams() {
		if(params.isEmpty()) {
			return "";
		}
		else {
			String toReturn;
			toReturn= params.toString().replace("[", "");
			toReturn = toReturn.replace("]", "");
			return toReturn;
		}
	}

	/**
	 * Seperates input into command and parameters
	 * @param input
	 */
	public  void parseInput(String input) {
		String[] string = split(input);
		command = string[0];

		if(string.length > 1) {
			for(int i=1; i<string.length; i++) {
				params.add(i-1, string[i]);
			}
		}
	}

	/**
	 * Splits the input
	 * @param input
	 * @return
	 */
	private  String[] split(String input) {
		String splitString[] = input.split(" ");
		return splitString;

	}
}
