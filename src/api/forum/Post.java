package api.forum;


/**
 * A post contains all the posts inside a thread
 * @author Tim
 *
 */
public class Post {
	String postAuthor;
	String postBody;
	UserInForum userInForum;
	public Post(UserInForum userInForum, String postBody) {
		this.postAuthor = userInForum.getUserName();
		this.postBody = postBody;
		this.userInForum = userInForum;
	}
	/**
	 * Get author of the post
	 * @return author of post
	 */
	public String getPostAuthor() {
		return postAuthor;
	}
	public UserInForum getUserInForum() {
		return userInForum;
	}
	/**
	 * Get post body
	 * @return body of post
	 */
	public String getPostBody() {
		return postBody;
	}
	/**
	 * Return string representation of a post
	 */
	public String toString() {
		String toReturn = postAuthor +": " + postBody;
		return toReturn;
	}
}
