package api.profile;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.UserProfileParser;

public class Stats {
	String joined;
	String lastSeen;
	String uploaded;
	String downloaded;
	String ratio;
	String requiredRatio;
	String forumPosts;
	String torrentComments;
	String collagesStarted;
	String collagesContributedTo;
	String requestsFilled;
	String requestsVoted;
	String uploadedNumber;
	String perfectFlacs;
	String seeding;
	String leeching;
	String snatched;
	String invited;
	String profileText;
	String userName;
	String userID;
	String userClass;
	LinkedList<String> recentSnatches = new LinkedList<String>();
	LinkedList<String> recentUploads = new LinkedList<String>();
	LinkedList<String> basicStats = new LinkedList<String>();
	LinkedList<String> extraStats = new LinkedList<String>();

	public Stats(String userName, String userID) throws IOException {
		this.userName = userName;
		this.userID = userID;
		UserProfileParser.init(userID);
	}

	/**
	 * Add the basic statistics
	 */
	public void addBasicStats() {
		joined = UserProfileParser.parseJoinDate();
		basicStats.add(joined);
		lastSeen = UserProfileParser.parseLastSeen();
		basicStats.add(lastSeen);
		userClass = UserProfileParser.parseUserClass();
		basicStats.add(userClass);
		uploaded = UserProfileParser.parseUploaded();
		basicStats.add(uploaded);
		downloaded = UserProfileParser.parseDownloaded();
		basicStats.add(downloaded);
		ratio = UserProfileParser.parseRatio();
		basicStats.add(ratio);
		requiredRatio = UserProfileParser.parseRequiredRatio();
		basicStats.add(requiredRatio);
		forumPosts = UserProfileParser.parseForumPosts();
		basicStats.add(forumPosts);
		// clean up the stats
		for (int i = 0; i < basicStats.size(); i++) {
			if (basicStats.get(i).contains("Hidden")) {
				basicStats.remove(i);
			}
		}
	}

	/**
	 * Adds more in depth statistics
	 * 
	 * @throws IOException
	 */
	public void addExtraStats() throws IOException {
		profileText = UserProfileParser.parseUserProfileText();
		for (String s : UserProfileParser.parseUserProfileRecentSnatches()) {
			recentSnatches.add(s);
		}
		for (String s : UserProfileParser.parseUserProfileRecentUploads()) {
			recentUploads.add(s);
		}
	}

	/**
	 * List representation of the basic stats
	 * 
	 * @return list containing the basic stats
	 */
	public LinkedList<String> getBasicStats() {
		return basicStats;
	}

	public LinkedList<String> getRecentSnatches() {
		return recentSnatches;
	}

	public LinkedList<String> getRecentUploads() {
		return recentUploads;
	}

	public String getProfileText() {
		return profileText;
	}

	public String getJoined() {
		return joined;
	}

	public String getLastSeen() {
		return lastSeen;
	}

	public String getUploaded() {
		return uploaded;
	}

	public String getDownloaded() {
		return downloaded;
	}

	public String getRatio() {
		return ratio;
	}

	public String getRequiredRatio() {
		return requiredRatio;
	}

	public String getForumPosts() {
		return forumPosts;
	}

	public String getTorrentComments() {
		return torrentComments;
	}

	public String getCollagesStarted() {
		return collagesStarted;
	}

	public String getCollagesContributedTo() {
		return collagesContributedTo;
	}

	public String getRequestsFilled() {
		return requestsFilled;
	}

	public String getRequestsVoted() {
		return requestsVoted;
	}

	public String getUploadedNumber() {
		return uploadedNumber;
	}

	public String getPerfectFlacs() {
		return perfectFlacs;
	}

	public String getSeeding() {
		return seeding;
	}

	public String getLeeching() {
		return leeching;
	}

	public String getSnatched() {
		return snatched;
	}

	public String getInvited() {
		return invited;
	}

	@Override
	public String toString() {
		return basicStats.toString();
	}

}
