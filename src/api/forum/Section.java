package api.forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import api.parser.ThreadsParser;
import api.util.Septuple;

/**
 * A section holds a list of threads
 * 
 * @author Tim
 */
public class Section {
	private String sectionTitle;
	private String sectionUrl;
	private LinkedList<ThreadInForum> threadsList = new LinkedList<ThreadInForum>();
	private ArrayList<ThreadInForum> masterThreadsList = new ArrayList<ThreadInForum>();
	private boolean listening;
	private boolean newThreads;

	/**
	 * Create a new Section in a Forum
	 * 
	 * @param sectionTitle
	 *            title of section
	 * @throws IOException
	 */
	public Section(String sectionTitle, String sectionUrl) throws IOException {
		this.sectionTitle = sectionTitle;
		this.sectionUrl = sectionUrl;
	}

	/**
	 * Populate the section with threads using the thread parser
	 * 
	 * @param page
	 *            the page of the section to get threads from
	 * @throws IOException
	 */
	public void addThreads(int page) throws IOException {
		for (Septuple<String, String, String, String, String, String, String> t : ThreadsParser.parseThreads(this, page)) {
			threadsList
					.add(new ThreadInForum(t.getA(), t.getF(), t.getG(), new UserInForum(t.getB(), t.getC()), new UserInForum(t.getD(), t.getE())));
		}
		if (!isListening()) {
			appendToMasterList();
		}
	}

	/**
	 * Appends elements to the masterList but only if they don't already exists should also be called for
	 * 
	 */
	private void appendToMasterList() {
		for (int i = 0; i < threadsList.size(); i++) {
			if (!masterThreadsList.contains(threadsList.get(i))) {
				masterThreadsList.add(threadsList.get(i));
			}
		}
	}

	/**
	 * Mark all the threads as "read"; just appends everything to the masterlist
	 */
	public void markAllAsRead() throws IOException {
		appendToMasterList();
	}

	/**
	 * An array of all the titles of threads in a section
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
	 * An array of the all the last posters of threads in a section
	 * 
	 * @return
	 */
	public String[] getThreadsLastPosterArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadLastPoster();
		}
		return s;
	}

	/**
	 * An array of the all the authors of threads in a section
	 * 
	 * @return
	 */
	public String[] getThreadsAuthorArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadAuthor();
		}
		return s;
	}

	/**
	 * An array of all the thread urls in a section
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
	 * An array of the last poster ids in threads in a section
	 * 
	 * @return
	 */
	public String[] getThreadsLastPosterIDArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadLastPosterID();
		}
		return s;
	}

	/**
	 * An array of the author ids in threads in a section
	 * 
	 * @return
	 */
	public String[] getThreadsAuthorIDArray() {
		String[] s = new String[threadsList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = threadsList.get(i).getThreadAuthorID();
		}
		return s;
	}

	/**
	 * Get a LinkedList of type Threads
	 * 
	 * @return
	 */
	public LinkedList<ThreadInForum> getThreads() {
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
	 * Get the url of the section
	 * 
	 * @return section url
	 */
	public String getSectionUrl() {
		return sectionUrl;
	}

	/**
	 * Checks if Section is listening for new threads
	 * 
	 * @return true if listening
	 */
	public boolean isListening() {
		return listening;
	}

	/**
	 * Checks if there are new threads
	 * 
	 * @return true if new threads
	 */
	public boolean isNewThreads() {
		return newThreads;
	}

	/**
	 * Enable/Disable listening for new threads in as section
	 * 
	 * @param listening
	 */
	public void setListening(boolean listening) {
		this.listening = listening;
	}

	/**
	 * Gets a LinkedList of new threads of a section if listening is enabled
	 * 
	 * @return LinkedList of new threads
	 * @throws IOException
	 */
	public LinkedList<ThreadInForum> getNewThreads() throws IOException {
		if (isListening()) {
			clearThreadsList();
			addThreads(1);
			LinkedList<ThreadInForum> list = new LinkedList<ThreadInForum>(subtract(masterThreadsList, threadsList));
			if (!list.isEmpty()) {
				newThreads = true;
				appendToMasterList();
				return list;
			}
		}
		return null;
	}

	/**
	 * Subtract two collections
	 * 
	 * @param coll1
	 *            collection to subtract from
	 * @param coll2
	 *            collection to subtract
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Collection<ThreadInForum> subtract(Collection coll1, Collection coll2) {
		Collection result = new ArrayList(coll2);
		result.removeAll(coll1);
		return result;
	}

}