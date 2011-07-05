package api.profile;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.UserProfileParser;

public class UserProfile {
	String userName;
	String userClass = "";
	String userID;
	String profileText;
	String avatarURL;
	LinkedList<String> recentSnatches = new LinkedList<String>();
	LinkedList<String> recentUploads = new LinkedList<String>();
	Stats stats;

	public UserProfile(String userName, String userID) throws IOException {
		this.userName = userName;
		this.userID = userID;
	}

	// profile text (parse images somehow), avatar url, userclass, recent d/u
	public void addProfile() throws IOException {
		avatarURL = UserProfileParser.parseUserProfileAvatar(userID);
		profileText = UserProfileParser.parseUserProfileText(userID);
		for (String s : UserProfileParser.parseUserProfileRecentSnatches(userID)) {
			recentSnatches.add(s);
		}
		for (String s : UserProfileParser.parseUserProfileRecentUploads(userID)) {
			recentUploads.add(s);
		}

	}

	private void addStats() {
		stats = new Stats();
	}

	public LinkedList<String> getRecentSnatches() {
		return recentSnatches;
	}

	public LinkedList<String> getRecentUploads() {
		return recentUploads;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserClass() {
		return userClass;
	}

	public String getUserID() {
		return userID;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public String getProfileText() {
		return profileText;
	}

	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return userName + "," + userClass + "," + userID + "," + recentSnatches + "," + recentUploads;
	}

}
