package api.soup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import api.util.RegexTools;

/**
 * JSoup methods to login and get pages
 * 
 * @author Tim
 * 
 */
public class MySoup {
	private static String sessionId;
	private static String authKey;
	private static RegexTools regex = new RegexTools();

	/**
	 * Login to a site
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public static void login(String url, String username, String password) {
		try {
			// login
			Connection.Response res =
					Jsoup.connect(url).data("username", username, "password", password).method(Method.POST).timeout(30000).execute();
			// set cookie
			sessionId = res.cookie("session");

			// get the authkey
			String s =
					scrape("http://what.cd/index.php").getElementById("header").getElementById("userinfo").getElementById("userinfo_username")
							.getElementById("nav_logout").getElementsByTag("a").get(0).toString();
			authKey = regex.splitAuthKey(s);

		} catch (IOException e) {
		}
	}

	public static String getAuthKey() {
		return authKey;
	}

	/**
	 * Check if user was able to login
	 * 
	 * @return true if logged in
	 */
	public static boolean isLoggedIn() {
		if (sessionId != null)
			return true;
		return false;
	}

	/**
	 * Return a Document of a page that has a cookie
	 * 
	 * @param page
	 * @return Document
	 */
	public static Document scrape(String page) {
		Document doc = null;
		try {
			doc = Jsoup.connect(page).cookie("session", sessionId).timeout(30000).get();
		} catch (IOException e) {
		}
		return doc;
	}

	/**
	 * Method for testing on local copies of pages
	 * 
	 * @param file
	 * @return
	 */
	public static Document scrape(File file) {
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");
		} catch (IOException e) {
		}
		return doc;
	}

	public static void postReply(String url, String reply) {
		String threadID = regex.splitThreadID(url);
		try {
			@SuppressWarnings("unused")
			Connection.Response res =
					Jsoup.connect(url).cookie("session", sessionId).data("action", "reply", "auth", authKey, "thread", threadID, "body", reply)
							.method(Method.POST).followRedirects(false).timeout(10000).execute();
		} catch (IOException e) {
		}
	}

	public static String toQuotableString(String html) {
		return Jsoup.parse(html).text();
	}
}