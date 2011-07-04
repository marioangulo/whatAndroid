package api.forum;

/**
 * A post contains all the posts inside a thread
 * 
 * @author Tim
 * 
 */
public class Post {
	private String postBody;
	private UserInForum postAuthor;

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

	/**
	 * Return string representation of a post
	 */
	@Override
	public String toString() {
		String toReturn = postAuthor + ": " + postBody;
		return toReturn;
	}
}
