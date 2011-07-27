package api.forum;

/**
 * A user in the forum, contains the user's name and id
 * 
 * @author Tim
 * 
 */
public class UserInForum {
	private String userName;
	private String userID;

	/**
	 * Create a new user in forum
	 * 
	 * @param userName
	 *            the user's name
	 * @param userID
	 *            the user's id
	 */
	public UserInForum(String userName, String userID) {
		this.userName = userName;
		this.userID = userID;
	}

	/**
	 * Get the user's name
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Get the user's id
	 * 
	 * @return the user id
	 */
	public String getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		String toReturn = userName + "," + userID;
		return toReturn;
	}
}
