package api.forum;

import java.io.IOException;

/**
 * @author Tim
 * 
 */
public class Manager {
	static Forum forum;

	public static void createForum(String title) throws IOException {
		forum = new Forum(title);
	}

	public static Forum getForum() {
		return forum;
	}

	// TODO impleememnt this
	/*
	 * public static void threadListener(ArrayList<String> list) throws IOException { for (int i = 0; i < list.size();
	 * i++) { forum.getSectionByName(list.get(i)).addThreads(1);
	 * 
	 * } }
	 */

	@Override
	public String toString() {
		// TODO add managers
		return "Managing: ";
	}
}
