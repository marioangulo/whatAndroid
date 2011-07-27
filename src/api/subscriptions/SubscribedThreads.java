package api.subscriptions;

import java.io.IOException;
import java.util.LinkedList;

import api.forum.Post;
import api.forum.UserInForum;
import api.parser.PostParser;
import api.util.Triple;

/**
 * A Subscribed Thread
 * 
 * @author Tim
 * 
 */
public class SubscribedThreads {
	private String threadTitle;
	private String threadUrl;
	private String threadLastReadUrl;
	private LinkedList<Post> postList = new LinkedList<Post>();

	/**
	 * Create a new subscribed thread
	 * 
	 * @param threadTitle
	 *            the title
	 * @param threadUrl
	 *            the url
	 * @param threadLastReadUrl
	 *            the last read url
	 */
	public SubscribedThreads(String threadTitle, String threadUrl, String threadLastReadUrl) {
		this.threadTitle = threadTitle;
		this.threadLastReadUrl = threadLastReadUrl;
	}

	/**
	 * Gets the title of the thread
	 * 
	 * @return the thread title
	 */
	public String getThreadTitle() {
		return threadTitle;
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
	 * Get the last read url of a the thread
	 * 
	 * @return
	 */
	public String getThreadLastReadUrl() {
		return threadLastReadUrl;
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
	 * Array of users in a thread
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
	 * An array of post bodys in a thread
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

}
