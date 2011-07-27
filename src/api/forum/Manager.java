package api.forum;

import java.io.IOException;

import api.subscriptions.Subscriptions;

/**
 * Gives static access to the forum and subscriptions
 * 
 * @author Tim
 * 
 */
public class Manager {
	static Forum forum;
	static Subscriptions subscriptions;
	static boolean managingForum, managingSubscriptions;

	public static void createForum(String title) throws IOException {
		forum = new Forum(title);
		managingForum = true;
	}

	public static Forum getForum() {
		return forum;
	}

	public static void createSubscriptions(String title) {
		subscriptions = new Subscriptions(title);
		managingSubscriptions = true;
	}

	public static Subscriptions getSubscriptions() {
		return subscriptions;
	}

	public static String getManagers() {
		String toReturn = "Managing: ";
		if (managingForum) {
			toReturn = toReturn.concat("Forum; ");
		}
		if (managingSubscriptions) {
			toReturn = toReturn.concat("Subscriptions; ");
		}
		return toReturn;
	}
}
