package api.forum;

public class UserInForum {
	private String userName;
	private String userID;

	public UserInForum(String userName, String userID) {
		this.userName = userName;
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		String toReturn = userName + "," + userID;
		return toReturn;
	}
}
