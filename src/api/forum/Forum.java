package api.forum;

import java.io.IOException;
import java.util.LinkedList;

import api.parser.SectionParser;

/**
 * A forum holds a list of Sections; each Section holds a list of Threads; each Thread holds a list of Posts.
 * 
 * 
 * @author Tim
 * 
 */
public class Forum {
	private String forumTitle;
	private LinkedList<Section> sectionList = new LinkedList<Section>();

	/**
	 * Create a new forum
	 * 
	 * @param forumTitle
	 *            title of the forum
	 * @throws IOException
	 */
	public Forum(String forumTitle) throws IOException {
		this.forumTitle = forumTitle;
		addSections();
	}

	/**
	 * Add sections to the forum
	 * 
	 * @throws IOException
	 */
	private void addSections() throws IOException {
		// gets string array from parser and adds it to the sectionList
		for (String s : SectionParser.getMasterList()) {
			sectionList.add(new Section(s));
		}
	}

	/**
	 * list all section titles in the forum
	 */
	public void listSections() {
		System.out.println("*Sections*");
		for (Section s : sectionList) {
			System.out.println(s.getSectionTitle());
		}
	}

	/**
	 * Get section by name
	 * 
	 * @param sectionName
	 * @return
	 */
	public Section getSectionByName(String sectionName) {
		for (Section s : sectionList) {
			if (s.getSectionTitle().equalsIgnoreCase(sectionName))
				return s;
		}
		return null;
	}

	/**
	 * Returns a LinkedList of type Section
	 * 
	 * @return sectionList
	 */
	public LinkedList<Section> getSections() {
		return sectionList;
	}

	/**
	 * Returns the forum title
	 * 
	 * @return forum title
	 */
	public String getForumTitle() {
		return forumTitle;
	}
}
