package api.parser;

import api.forum.Section;
import api.soup.MySoup;
import api.util.RegexTools;
import api.util.Sextuple;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Parses threads depending on section Returns a list threads, each thread contains <title, author, author id, last
 * poster, last poster id, thread url>
 * 
 * @author Tim
 */
public class ThreadsParser {
	static RegexTools regex = new RegexTools();

	public static LinkedList<Sextuple<String, String, String, String, String, String>> parseThreads(Section s, int page) throws IOException {
		LinkedList<Sextuple<String, String, String, String, String, String>> threadList =
				new LinkedList<Sextuple<String, String, String, String, String, String>>();
		String sectionId = regex.split(s.getSectionUrl(), "viewforum&forumid=", "");
		Document doc = MySoup.scrape("http://what.cd/forums.php?page=" + page + "&action=viewforum&forumid=" + sectionId);

		Elements rowsa = doc.getElementsByClass("rowa");
		Elements rowsb = doc.getElementsByClass("rowb");
		for (int i = 0; i < rowsb.size(); i++) {
			// get author
			String authora = ((rowsa.get(i).getElementsByTag("td").get(3)).text());
			String authorb = ((rowsb.get(i).getElementsByTag("td").get(3)).text());

			String authorIDa[] = regex.splitPoster((rowsa.get(i).getElementsByTag("td").get(3)).toString());
			String authorIDb[] = regex.splitPoster((rowsb.get(i).getElementsByTag("td").get(3)).toString());

			// gets last poster
			String lastPostera = ((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).text());
			String lastPosterb = (rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").text());

			String lastPosterIDa[] = regex.splitPoster(rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
			String lastPosterIDb[] = regex.splitPoster(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());

			// get thread title
			String titlea = (rowsa.get(i).getElementsByTag("a").get(0).text());
			String titleb = (rowsb.get(i).getElementsByTag("a").get(0).text());

			// get thread url
			String urla = regex.splitThreadUrl((rowsa.get(i).getElementsByClass("last_topic").get(0).getElementsByTag("a")).get(0).toString());
			String urlb = regex.splitThreadUrl((rowsb.get(i).getElementsByClass("last_topic").get(0).getElementsByTag("a")).get(0).toString());

			// try to get last read url
			try {
				urla = regex.splitLastReadThreadUrl(rowsa.get(i).getElementsByClass("last_read").get(0).getElementsByTag("a").get(0).toString());
				urlb = regex.splitLastReadThreadUrl(rowsb.get(i).getElementsByClass("last_read").get(0).getElementsByTag("a").get(0).toString());
			} catch (Exception e) {
			}

			threadList.add(new Sextuple<String, String, String, String, String, String>(titlea, authora, authorIDa[1], lastPostera, lastPosterIDa[1],
					urla));
			threadList.add(new Sextuple<String, String, String, String, String, String>(titleb, authorb, authorIDb[1], lastPosterb, lastPosterIDb[1],
					urlb));
		}
		return threadList;
	}
}