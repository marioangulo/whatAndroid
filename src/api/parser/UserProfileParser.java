package api.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.util.RegexTools;

public class UserProfileParser {
	static RegexTools regex = new RegexTools();

	public static String parseUserProfileAvatar(String userID) throws IOException {
		Document doc = MySoup.scrape("http://what.cd/user.php?id=" + userID);
		Elements profile = doc.getElementsByClass("box");
		return regex.splitAvatar(profile.get(0).getElementsByTag("img").toString());
	}

	public static LinkedList<String> parseUserProfileRecentSnatches(String userID) throws IOException {
		LinkedList<String> snatchesList = new LinkedList<String>();
		Document doc = MySoup.scrape("http://what.cd/user.php?id=" + userID);
		Elements profile = doc.getElementsByClass("recent").get(0).getElementsByTag("a");
		for (int i = 0; i < profile.size(); i++) {
			snatchesList.add(profile.get(i).toString());
		}
		return snatchesList;
	}

	public static LinkedList<String> parseUserProfileRecentUploads(String userID) throws IOException {
		LinkedList<String> uploadsList = new LinkedList<String>();
		Document doc = MySoup.scrape("http://what.cd/user.php?id=" + userID);
		Elements profile = doc.getElementsByClass("recent").get(1).getElementsByTag("a");
		for (int i = 0; i < profile.size(); i++) {
			uploadsList.add(profile.get(i).toString());
		}
		return uploadsList;
	}

	public static String parseUserProfileText(String userID) throws IOException {
		Document doc = MySoup.scrape("http://what.cd/user.php?id=" + userID);
		Elements profile = doc.getElementsByClass("box");
		return regex.splitProfileText(profile.get(5).getElementsByClass("pad").toString());

	}

}
