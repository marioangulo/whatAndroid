package api.forum;

/**
 * A Post in the thread
 * 
 * @author Tim
 * 
 */
public class Post {
	private String postBody;
	private UserInForum postAuthor;

	/**
	 * Create a new post
	 * 
	 * @param postAuthor
	 *            the author of the post
	 * @param postBody
	 *            the body of the post
	 */
	public Post(UserInForum postAuthor, String postBody) {
		this.postAuthor = postAuthor;
		this.postBody = postBody;
	}

	/**
	 * Get author of the post
	 * 
	 * @return author of post
	 */
	public String getPostAuthor() {
		return postAuthor.getUserName();
	}

	/**
	 * Get author's id
	 * 
	 * @return
	 */
	public String getPostAuthorID() {
		return postAuthor.getUserID();
	}

	/**
	 * Get post body
	 * 
	 * @return body of post
	 */
	public String getPostBody() {
		return postBody;
	}
}
