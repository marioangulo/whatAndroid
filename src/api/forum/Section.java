package api.forum;

import java.io.IOException;
import java.util.LinkedList;
import api.util.*;
import api.parser.*;

/**
 * A section holds a list of threads
 * @author Tim
 */
public class Section {
	private String sectionTitle;
	private LinkedList<Threads> threadsList = new LinkedList<Threads>();
	/**
	 * Create a new Section in a Forum
	 * @param sectionTitle title of section
	 * @throws IOException
	 */
	public Section(String sectionTitle) throws IOException {
		this.sectionTitle = sectionTitle;
		//addThreads();
	}
	/**
	 * Add threads to the section using the thread parser
	 * @throws IOException
	 */
	private void addThreads() throws IOException {
		for (Triple<String, String, String> t : ThreadsParser.parseThreads(this)) {
			//threadsList.add(new Threads(t.getA(), t.getB(),t.getC()));
		}
	}
	/**
	 * Get a LinkedList of type Threads
	 * @return
	 */
	public LinkedList<Threads> getThreads() {
		return threadsList;
	}
	/**
	 * Get title of section
	 * @return section title
	 */
	public String getSectionTitle() {
		return sectionTitle;
	}
	/**
	 * String representation of a section and everything in it
	 */
	public String toString() {
		String toReturn = sectionTitle + "\n" /*+ getThreads().toString()*/;
		toReturn = toReturn.replace("[", "");
		toReturn = toReturn.replace("]", "");
		return toReturn;
	}
}
