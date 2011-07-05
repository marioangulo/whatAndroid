package api.profile;

import java.io.IOException;

import api.parser.UserProfileParser;

public class UserProfile {
	String userName;
	String userID;
	String avatarURL;
	Stats stats;

	public UserProfile(String userName, String userID) throws IOException {
		this.userName = userName;
		this.userID = userID;
		stats = new Stats(userName, userID);
		UserProfileParser.init(userID);
	}

	public void addAvatar() {
		try {
			avatarURL = UserProfileParser.parseUserProfileAvatar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getUserID() {
		return userID;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return userName + "," + userID + stats.toString();
	}

}
