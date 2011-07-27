package api.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.util.Triple;

/**
 * Parses the subscriptions
 * 
 * @author Tim
 * 
 */
public class SubscriptionsParser {
	static api.util.RegexTools regex = new api.util.RegexTools();

	/**
	 * Parse thread in Subscriptions
	 * 
	 * @return
	 * @throws IOException
	 */
	public static LinkedList<Triple<String, String, String>> parseSubscriptions() throws IOException {
		LinkedList<Triple<String, String, String>> threadList = new LinkedList<Triple<String, String, String>>();
		Document doc = MySoup.scrape("http://what.cd/userhistory.php?action=subscriptions");
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByTag("table");

		String title, url, lastread;
		for (int i = 0; i < a.size(); i++) {
			title = (a.get(i).getElementsByTag("span").get(0).getElementsByTag("a").get(1).text());
			url = (regex.splitThreadUrl(a.get(i).getElementsByTag("span").get(0).getElementsByTag("a").get(1).toString()));
			lastread = regex.splitLastReadThreadUrl(a.get(i).getElementsByTag("span").get(2).getElementsByTag("a").toString());
			threadList.add(new Triple<String, String, String>(title, url, lastread));
		}
		return threadList;
	}
}
