/**
 * 
 */
package api.parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import api.util.*;
import api.forum.*;

/**
 * @author Tim
 *
 */
public class ThreadsParser {
	static RegexTools regex = new RegexTools();
	public static LinkedList<Triple<String, String, String>> parseThreads(Section s) throws IOException {
		LinkedList<Triple<String,String,String>> threadList = new LinkedList<Triple<String,String,String>>();
		if(s.getSectionTitle().equalsIgnoreCase("The Lounge")) {
			//TODO Scrape
			File input = new File("assets/sectiontest.html");
			Document doc = Jsoup.parse(input, "UTF-8", "");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		return threadList;
	}
}
