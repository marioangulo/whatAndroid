package api.forum;

import java.io.IOException;
import java.util.LinkedList;

/*import user.UserInForum;
import backend.Parser;*/

/**
 * A Thread in the forum
 * @author Tim
 *
 */
public class Threads {
	/*private String threadTitle;
	private String threadAuthor;
	private String threadLastPoster;
	private LinkedList<Post> postList = new LinkedList<Post>();
	//TODO add userinforum 
	public Threads(String threadTitle,UserInForum userInForum) throws IOException {
		this.threadTitle = threadTitle;
		this.threadAuthor = userInForum.getUserName();
		this.threadLastPoster = threadLastPoster;
		addPost();
	}
	*//**
	 * Create a new thread
	 * @param threadTitle
	 * @param threadAuthor
	 * @param threadLastPoster
	 * @throws IOException 
	 *//*
	public Threads(String threadTitle, String threadAuthor, String threadLastPoster) throws IOException {
		this.threadTitle = threadTitle;
		this.threadAuthor = threadAuthor;
		this.threadLastPoster = threadLastPoster;
		addPost();
	}
	*//**
	 * Get title of the thread
	 * @return thread title
	 *//*
	public String getThreadTitle() {
		return threadTitle;
	}
	*//**
	 * Get author of the thread
	 * @return thread author
	 *//*
	public String getThreadAuthor() {
		return threadAuthor;
	}
	*//**
	 * Get last poster in the thread
	 * @return last poster
	 *//*
	public String getThreadLastPoster() {
		return threadLastPoster;
	}
	*//**
	 * Add posts to a thread
	 * @throws IOException 
	 *//*
	private void addPost() throws IOException {
		for (util.Triple<String, String, String> p : Parser.parsePosts(this)) {
			postList.add(new Post(new UserInForum(p.getA(),p.getB()),p.getC()));
		}
	}
	*//**
	 * Get the original post in a thread
	 * @return original post
	 *//*
	public Post getOriginalPost() {
		return postList.getFirst();
	}
	*//**
	 * Get the last post in a thread
	 * @return last post
	 *//*
	public Post getLastPost() {
		return postList.getLast();
	}
	*//**
	 * Return list of posts
	 * @return postList
	 *//*
	public LinkedList<Post> getPost() {
		return postList;
	}
	*//**
	 * String representation of a thread and everything inside it
	 *//*
	public String toString() {
		String toReturn = threadTitle + ", " + threadAuthor + ", " + threadLastPoster + "\n" + postList.toString();
		return toReturn;
	}*/
}
