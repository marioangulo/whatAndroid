package api.parser;

import java.io.File;

import org.jsoup.nodes.Document;

import api.soup.MySoup;

public class ArtistParser {
	private static Document doc;

	public static void init(String url) {
		doc = MySoup.scrape(new File("assets/artist.html"));
	}

	public static String parseTitle() {
		String e = doc.getElementById("content").getElementsByClass("thin").get(0).getElementsByTag("h2").text();
		return e;
	}

}
