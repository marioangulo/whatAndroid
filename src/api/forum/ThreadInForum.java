package api.forum;

import java.io.IOException;

/**
 * Thread in the forum
 * 
 * @author Tim
 * 
 */
public class ThreadInForum extends AbstractThread {
	private UserInForum threadAuthor;
	private UserInForum threadLastPoster;

	public ThreadInForum(String threadTitle, String threadUrl, String threadLastReadUrl, UserInForum threadAuthor, UserInForum threadLastPoster)
			throws IOException {
		super(threadTitle, threadUrl, threadLastReadUrl);
		this.threadAuthor = threadAuthor;
		this.threadLastPoster = threadLastPoster;

		if (threadLastReadUrl != null) {
			setRead(true);
		}
	}

	/**
	 * @return the threadAuthor
	 */
	public String getThreadAuthor() {
		return threadAuthor.getUserName();
	}

	/**
	 * @return the threadAuthor's ID
	 */
	public String getThreadAuthorID() {
		return threadAuthor.getUserID();
	}

	/**
	 * @return the threadLastPoster
	 */
	public String getThreadLastPoster() {
		return threadLastPoster.getUserName();
	}

	/**
	 * @return the threadLastPoster's ID
	 */
	public String getThreadLastPosterID() {
		return threadLastPoster.getUserID();
	}
}
