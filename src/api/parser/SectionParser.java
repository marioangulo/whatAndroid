package api.parser;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.util.RegexTools;
import api.util.Tuple;

/**
 * Parses the section
 * 
 * @author Tim
 * 
 */
public class SectionParser {
	static RegexTools regex = new RegexTools();
	static Document doc = MySoup.scrape("http://what.cd/forums.php");

	public static LinkedList<Tuple<String, String>> parseMasterList() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		for (int i = 0; i < a.size(); i++) {
			Elements b = a.get(i).getElementsByClass("min_padding");
			for (int j = 0; j < b.size(); j++) {
				String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
				String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
				sectionList.add(new Tuple<String, String>(title, url));
			}
		}
		return sectionList;
	}

	public static LinkedList<Tuple<String, String>> parseSiteSections() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		Elements b = a.get(0).getElementsByClass("min_padding");
		for (int j = 0; j < b.size(); j++) {
			String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
			String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
			sectionList.add(new Tuple<String, String>(title, url));
		}
		return sectionList;
	}

	public static LinkedList<Tuple<String, String>> parseCommunitySections() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		Elements b = a.get(1).getElementsByClass("min_padding");
		for (int j = 0; j < b.size(); j++) {
			String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
			String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
			sectionList.add(new Tuple<String, String>(title, url));
		}
		return sectionList;
	}

	public static LinkedList<Tuple<String, String>> parseMusicSections() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		Elements b = a.get(2).getElementsByClass("min_padding");
		for (int j = 0; j < b.size(); j++) {
			String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
			String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
			sectionList.add(new Tuple<String, String>(title, url));
		}
		return sectionList;
	}

	public static LinkedList<Tuple<String, String>> parseHelpSections() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		Elements b = a.get(3).getElementsByClass("min_padding");
		for (int j = 0; j < b.size(); j++) {
			String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
			String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
			sectionList.add(new Tuple<String, String>(title, url));
		}
		return sectionList;
	}

	public static LinkedList<Tuple<String, String>> parseTrashSections() {
		LinkedList<Tuple<String, String>> sectionList = new LinkedList<Tuple<String, String>>();
		Elements a = doc.getElementsByClass("thin").get(0).getElementsByClass("forum_index");
		Elements b = a.get(4).getElementsByClass("min_padding");
		for (int j = 0; j < b.size(); j++) {
			String title = (regex.splitSectionTitle(b.get(j).getElementsByTag("a").toString()));
			String url = (regex.splitSectionUrl(b.get(j).getElementsByTag("a").toString()));
			sectionList.add(new Tuple<String, String>(title, url));
		}
		System.out.println(sectionList);
		return sectionList;
	}

	public static String[] listToArray(LinkedList<Tuple<String, String>> list) {
		String[] s = new String[list.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = list.get(i).getA();
		}
		return s;

	}
}
