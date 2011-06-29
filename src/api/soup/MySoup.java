package api.soup;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
/**
 * JSoup methods to login and get pages
 * @author Tim
 *
 */
public class MySoup {
	private static String sessionId;
	/**
	 * Login to a site
	 * @param url
	 * @param username 
	 * @param password
	 */
	public static void login(String url, String username, String password) {
		try {
			//login
			Connection.Response res = Jsoup.connect(url)
			.data("username", username, "password", password)
			.method(Method.POST)
			.execute();	
			//set cookie
			sessionId = res.cookie("session");
		} catch (IOException e) {
			/*System.err.println("Couldn't login");
			e.printStackTrace();*/
		}
	}
	/**
	 * Return a Document of a page that has a cookie
	 * @param page
	 * @return Document
	 */
	public static Document scrape(String page) {
		Document doc=null;
		try {
			doc = Jsoup.connect(page)
			.cookie("session", sessionId)
			.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		/*	e.printStackTrace();*/
		}
		return doc;
	}
}