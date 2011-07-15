package api.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.util.Tuple;

public class SubscriptionsParser {
	static api.util.RegexTools regex = new api.util.RegexTools();

	public static LinkedList<Tuple<String, String>> parseSubscriptions() throws IOException {
		LinkedList<Tuple<String, String>> threadList = new LinkedList<Tuple<String, String>>();
		Document doc = MySoup.scrape("http://what.cd/userhistory.php?action=subscriptions");

		Elements rowsa = doc.getElementsByClass("rowa");
		Elements rowsb = doc.getElementsByClass("rowb");
		for (int i = 0; i < rowsb.size(); i++) {
			// get thread title
			String titlea = (rowsa.get(i).getElementsByTag("a").get(0).text());
			String titleb = (rowsb.get(i).getElementsByTag("a").get(0).text());

			String urla = regex.splitThreadUrl((rowsa.get(i).getElementsByClass("last_topic").get(0).getElementsByTag("a")).get(0).toString());
			String urlb = regex.splitThreadUrl((rowsb.get(i).getElementsByClass("last_topic").get(0).getElementsByTag("a")).get(0).toString());

			threadList.add(new Tuple<String, String>(titlea, urla));
			threadList.add(new Tuple<String, String>(titleb, urlb));

		}
		return threadList;
	}
}
