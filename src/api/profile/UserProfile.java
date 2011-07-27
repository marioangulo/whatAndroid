package api.profile;

import java.io.IOException;

import api.parser.UserProfileParser;

/**
 * A User's profile
 * 
 * @author Tim
 * 
 */
public class UserProfile {
	String userName;
	String userID;
	String avatarURL;
	Stats stats;

	/**
	 * Create a new user profile
	 * 
	 * @param userName
	 *            the user's name
	 * @param userID
	 *            the user's id
	 * @throws IOException
	 */
	public UserProfile(String userName, String userID) throws IOException {
		this.userName = userName;
		this.userID = userID;
		stats = new Stats(userName, userID);
		UserProfileParser.init(userID);
	}

	/**
	 * Add an avatar for the user
	 */
	public void addAvatar() {
		try {
			avatarURL = UserProfileParser.parseUserProfileAvatar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the user's name
	 * 
	 * @return the username
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

	/**
	 * Get the url of the avatar
	 * 
	 * @return
	 */
	public String getAvatarURL() {
		return avatarURL;
	}

	/**
	 * Get the Stats of a user
	 * 
	 * @return
	 */
	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return userName + "," + userID + stats.toString();
	}

}
