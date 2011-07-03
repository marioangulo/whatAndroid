package api.forum;

public class UserInForum {
	String userName;
	String userID;
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
	public String toString() {
		String toReturn = userName + "," + userID;
		return toReturn;
	}
}
