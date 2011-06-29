/**
 * 
 */
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
	
	public String toString() {
		//TODO add managers
		return "Managing: ";
	}
}
