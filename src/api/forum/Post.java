package api.forum;

/*import user.UserInForum;
*/
/**
 * A post contains all the posts inside a thread
 * @author Tim
 *
 */
public class Post {
	String postAuthor;
	String postBody;
//	UserInForum userInForum;
	/*public Post(UserInForum userInForum, String postBody) {
		this.postAuthor = userInForum.getUserName();
		this.postBody = postBody;
		this.userInForum = userInForum;
	}*/
	/**
	 * Create a new post with an author and body
	 * @param postAuthor
	 * @param postBody
	 */
	public Post(String postAuthor, String postAuthorID, String postBody) {
		this.postAuthor = postAuthor;
		this.postBody = postBody;
	}
	/**
	 * Get author of the post
	 * @return author of post
	 */
	public String getPostAuthor() {
		return postAuthor;
	}
	/*public UserInForum getUserInForum() {
		return userInForum;
	}*/
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
