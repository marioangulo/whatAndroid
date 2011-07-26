package api.subscriptions;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.SubscriptionsParser;
import api.util.Triple;

/**
 * A section holds a list of threads
 * 
 * @author Tim
 */
public class Subscriptions {
	private String sectionTitle;
	private LinkedList<SubscribedThreads> threadsList = new LinkedList<SubscribedThreads>();

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
	public void addThreads(int page) throws IOException {
		for (Triple<String, String, String> t : SubscriptionsParser.parseSubscriptions()) {
			threadsList.add(new SubscribedThreads(t.getA(), t.getB(), t.getA()));
		}
	}

	public String[] getThreadsTitleArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadTitle();
		}
		return s;
	}

	public String[] getThreadsUrlArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadUrl();
		}
		return s;
	}

	public String[] getThreadsLastReadUrlArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadLastRead();
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

	/**
	 * String representation of a section and everything in it
	 */
	@Override
	public String toString() {
		String toReturn = sectionTitle + "\n" + getThreads().toString();
		toReturn = toReturn.replace("[", "");
		toReturn = toReturn.replace("]", "");
		return toReturn;
	}

}
