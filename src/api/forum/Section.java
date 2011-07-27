package api.forum;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.ThreadsParser;
import api.util.Sextuple;

/**
 * A section holds a list of threads
 * 
 * @author Tim
 */
public class Section {
	private String sectionTitle;
	private LinkedList<Threads> threadsList = new LinkedList<Threads>();

	/**
	 * Create a new Section in a Forum
	 * 
	 * @param sectionTitle
	 *            title of section
	 * @throws IOException
	 */
	public Section(String sectionTitle) throws IOException {
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
		for (Sextuple<String, String, String, String, String, String> t : ThreadsParser.parseThreads(this, page)) {
			threadsList.add(new Threads(t.getA(), new UserInForum(t.getB(), t.getC()), new UserInForum(t.getD(), t.getE()), t.getF()));
		}
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
	public LinkedList<Threads> getThreads() {
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