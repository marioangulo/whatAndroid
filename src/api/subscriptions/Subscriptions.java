package api.subscriptions;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.SubscriptionsParser;
import api.util.Triple;

/**
 * A Subscriptions section that hold Subscribed Threads
 * 
 * @author Tim
 */
public class Subscriptions {
	private String sectionTitle;
	private LinkedList<SubscribedThreads> threadsList = new LinkedList<SubscribedThreads>();

	/**
	 * Create a new Subscriptions section
	 * 
	 * @param sectionTitle
	 *            title of section
	 */
	public Subscriptions(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	/**
	 * Populate the section with threads using the thread parser
	 * 
	 * @param page
	 *            the page of the section to get threads from
	 * @throws IOException
	 */
	public void addThreads() throws IOException {
		for (Triple<String, String, String> t : SubscriptionsParser.parseSubscriptions()) {
			threadsList.add(new SubscribedThreads(t.getA(), t.getB(), t.getA()));
		}
	}

	/**
	 * Array of thread titles in Subscriptions
	 * 
	 * @return
	 */
	public String[] getThreadsTitleArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadTitle();
		}
		return s;
	}

	/**
	 * Array of thread urls in Subscriptions
	 * 
	 * @return
	 */
	public String[] getThreadsUrlArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadUrl();
		}
		return s;
	}

	/**
	 * An array of thread last read urls in a section
	 * 
	 * @return
	 */
	public String[] getThreadsLastReadUrlArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadLastReadUrl();
		}
		return s;
	}

	/**
	 * Get a LinkedList of type Threads
	 * 
	 * @return
	 */
	public LinkedList<SubscribedThreads> getThreads() {
		return threadsList;
	}

	/**
	 * Clear the threads list
	 */
	public void clearThreadsList() {
		threadsList.clear();
	}

	/**
	 * Get title of section
	 * 
	 * @return section title
	 */
	public String getSectionTitle() {
		return sectionTitle;
	}
}
