package api.subscriptions;

import java.io.IOException;
import java.util.LinkedList;

import api.forum.Post;
import api.forum.UserInForum;
import api.parser.PostParser;
import api.util.Triple;

/**
 * A Thread in the forum
 * 
 * @author Tim
 * 
 */
public class SubscribedThreads {
	private String threadTitle;
	private String threadUrl;
	private LinkedList<Post> postList = new LinkedList<Post>();

	public SubscribedThreads(String threadTitle, String threadUrl) throws IOException {
		this.threadTitle = threadTitle;
		this.threadUrl = threadUrl;
	}

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
	 * Add posts to a thread
	 * 
	 * @throws IOException
	 */
	public void addPosts(int page) throws IOException {
		for (Triple<String, String, String> p : PostParser.parsePosts(this, page)) {
			postList.add(new Post(new UserInForum(p.getA(), p.getB()), p.getC()));
		}
	}

	public String[] getPostUserArray() {
		String[] s = new String[postList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = postList.get(i).getPostAuthor();
		}
		return s;
	}

	public String[] getPostUserIDArray() {
		String[] s = new String[postList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = postList.get(i).getPostAuthorID();
		}
		return s;
	}

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
	 * String representation of a thread and everything inside it
	 */
	@Override
	public String toString() {
		String toReturn = threadTitle + ", " + "\n" /*
													 * + postList . toString
													 */;
		return toReturn;
	}
}
