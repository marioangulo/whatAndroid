package api.forum;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.PostParser;
import api.util.Triple;

/**
 * A Thread in the section
 * 
 * @author Tim
 * 
 */
public class Threads {
	private String threadTitle;
	private UserInForum threadAuthor;
	private UserInForum threadLastPoster;
	private String threadUrl;
	private LinkedList<Post> postList = new LinkedList<Post>();

	/**
	 * Create a new thread
	 * 
	 * @param threadTitle
	 *            title of thread
	 * @param threadAuthor
	 *            author
	 * @param threadLastPoster
	 *            last poster
	 * @param threadUrl
	 *            url of thread
	 * @throws IOException
	 */
	public Threads(String threadTitle, UserInForum threadAuthor, UserInForum threadLastPoster, String threadUrl) throws IOException {
		this.threadTitle = threadTitle;
		this.threadAuthor = threadAuthor;
		this.threadLastPoster = threadLastPoster;
		this.threadUrl = threadUrl;
	}

	/**
	 * Get the title of the thread
	 * 
	 * @return the thread title
	 */
	public String getThreadTitle() {
		return threadTitle;
	}

	/**
	 * Get author of the thread
	 * 
	 * @return thread author
	 */
	public String getThreadAuthor() {
		return threadAuthor.getUserName();
	}

	/**
	 * Get user id of author
	 * 
	 * @return
	 */
	public String getThreadAuthorID() {
		return threadAuthor.getUserID();
	}

	/**
	 * Get last poster in the thread
	 * 
	 * @return last poster
	 */
	public String getThreadLastPoster() {
		return threadLastPoster.getUserName();
	}

	/**
	 * Get last poster user id
	 * 
	 * @return user id of last poster
	 */
	public String getThreadLastPosterID() {
		return threadLastPoster.getUserID();
	}

	/**
	 * Get the url of a thread
	 * 
	 * @return thread url
	 */
	public String getThreadUrl() {
		return threadUrl;
	}

	/**
	 * Add posts to a thread
	 * 
	 * @throws IOException
	 */
	public void addPosts(int page) throws IOException {
		for (Triple<String, String, String> p : PostParser.parsePosts(this, page)) {
			postList.add(new Post(new UserInForum(p.getA(), p.getB()), p.getC()));
		}
	}

	/**
	 * An array of users in a thread
	 * 
	 * @return
	 */
	public String[] getPostUserArray() {
		String[] s = new String[postList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = postList.get(i).getPostAuthor();
		}
		return s;
	}

	/**
	 * An array of user ids in a thread
	 * 
	 * @return
	 */
	public String[] getPostUserIDArray() {
		String[] s = new String[postList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = postList.get(i).getPostAuthorID();
		}
		return s;
	}

	/**
	 * An array of post bodys in the thread
	 * 
	 * @return
	 */
	public String[] getPostBodyArray() {
		String[] s = new String[postList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = postList.get(i).getPostBody();
		}
		return s;
	}

	/**
	 * Get the original post in a thread
	 * 
	 * @return original post
	 */
	public Post getOriginalPost() {
		return postList.getFirst();
	}

	/**
	 * Get the last post in a thread
	 * 
	 * @return last post
	 */
	public Post getLastPost() {
		return postList.getLast();
	}

	/**
	 * Return list of posts
	 * 
	 * @return postList
	 */
	public LinkedList<Post> getPost() {
		return postList;
	}

	/**
	 * Clears the posts in the thread
	 */
	public void clearPosts() {
		postList.clear();
	}
}
