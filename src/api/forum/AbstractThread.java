package api.forum;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.PostParser;
import api.util.Triple;

/**
 * An abstract thread class to be used by forum threads and subscribed threads
 * 
 * @author Tim
 * 
 */
public abstract class AbstractThread {
	private String threadTitle;
	private String threadUrl;
	private String threadLastReadUrl;
	private int threadPage;
	private boolean isRead = false;
	private LinkedList<Post> postList = new LinkedList<Post>();

	public AbstractThread(String threadTitle, String threadUrl, String threadLastReadUrl) throws IOException {
		this.threadTitle = threadTitle;
		this.threadUrl = threadUrl;
		this.threadLastReadUrl = threadLastReadUrl;

		if (threadLastReadUrl != null) {
			isRead = true;
		}
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

	/**
	 * Refresh the posts in the thread for a specific page
	 * 
	 * @throws IOException
	 */
	public void refresh(int threadPage) throws IOException {
		postList.clear();
		addPosts(threadPage);
	}

	/**
	 * Go to the next page
	 * 
	 * @throws IOException
	 */
	public void nextPage() throws IOException {
		threadPage++;
		postList.clear();
		addPosts(threadPage);
	}

	/**
	 * Go to the next page
	 * 
	 * @throws IOException
	 */
	public void previousPage() throws IOException {
		threadPage--;
		postList.clear();
		addPosts(threadPage);
	}

	/**
	 * @return the threadTitle
	 */
	public String getThreadTitle() {
		return threadTitle;
	}

	/**
	 * @return the threadUrl
	 */
	public String getThreadUrl() {
		return threadUrl;
	}

	/**
	 * @return the threadLastReadUrl
	 */
	public String getThreadLastReadUrl() {
		return threadLastReadUrl;
	}

	/**
	 * @return the threadPage
	 */
	public int getThreadPage() {
		return threadPage;
	}

	/**
	 * @param threadPage
	 *            the threadPage to set
	 */
	public void setThreadPage(int threadPage) {
		this.threadPage = threadPage;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead
	 *            the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}