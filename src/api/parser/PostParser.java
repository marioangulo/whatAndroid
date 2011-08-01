package api.parser;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.forum.AbstractThread;
import api.soup.MySoup;
import api.util.RegexTools;
import api.util.Triple;

/**
 * Parses posts
 * 
 * @author Tim
 * 
 */

public class PostParser {
	static RegexTools regex = new RegexTools();

	/**
	 * Parse posts in a thread
	 * 
	 * @param t
	 *            AbstractThread
	 * @param page
	 *            page to parse
	 * @return
	 */
	public static LinkedList<Triple<String, String, String>> parsePosts(AbstractThread t, int page) {
		String url = t.getThreadUrl();
		url = url.concat("&page=" + page);
		LinkedList<Triple<String, String, String>> postList = new LinkedList<Triple<String, String, String>>();
		Document doc = MySoup.scrape(url);
		Elements posters = doc.getElementsByClass("forum_post");
		for (int i = 0; i < posters.size(); i++) {
			// gets posters
			String poster[] = regex.splitPoster(posters.get(i).getElementsByTag("a").get(1).toString());
			String body = (posters.get(i).getElementsByClass("body").get(0).getElementsByTag("div").toString());
			postList.add(new Triple<String, String, String>(poster[0], poster[1], body));
		}
		postList.removeLast();
		return postList;
	}
}
