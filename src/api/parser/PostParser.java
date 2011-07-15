package api.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.forum.Threads;
import api.soup.MySoup;
import api.subscriptions.SubscribedThreads;
import api.util.RegexTools;
import api.util.Triple;

public class PostParser {
	static RegexTools regex = new RegexTools();

	/**
	 * Parse posts in a thread
	 * 
	 * @param page
	 * 
	 * @throws IOException
	 */
	public static LinkedList<Triple<String, String, String>> parsePosts(Threads t, int page) throws IOException {
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

	public static LinkedList<Triple<String, String, String>> parsePosts(SubscribedThreads t, int page) {
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
