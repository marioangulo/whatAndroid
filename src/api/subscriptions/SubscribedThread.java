package api.subscriptions;

import java.io.IOException;

import api.forum.AbstractThread;

/**
 * A Subscribed Thread
 * 
 * @author Tim
 * 
 */
public class SubscribedThread extends AbstractThread {

	/**
	 * Create a new subscribed thread
	 * 
	 * @param threadTitle
	 *            the title
	 * @param threadUrl
	 *            the url
	 * @param threadLastReadUrl
	 *            the last read url
	 * @throws IOException
	 */
	public SubscribedThread(String threadTitle, String threadUrl, String threadLastReadUrl) throws IOException {
		super(threadTitle, threadUrl, threadLastReadUrl);
	}

	public void unsubscribe() {
		// TODO Auto-generated method stub
	}

	public void markAsRead() {
		// TODO Auto-generated method stub
	}

}
