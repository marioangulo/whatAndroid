package api.parser;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.torrents.Album;
import api.torrents.Artist;
import api.torrents.Format;
import api.util.Quintuple;
import api.util.RegexTools;
import api.util.Triple;

public class MusicParser {
	RegexTools regex = new RegexTools();

	public static LinkedList<Quintuple<String, String, String, String, String>> parseAlbums() {
		return null;

	}

	public static LinkedList<Triple<String, String, String>> parseArtist(String id) {
		Document doc = MySoup.scrape("http://what.cd/artist.php?id=" + id);

		return null;
	}

	public static LinkedList<Triple<String, String, String>> parseArtist() {
		return null;
	}

	public static LinkedList<Triple<Artist, Album, LinkedList<Format>>> parseSearchResult(String searchString, int page) {
		LinkedList<Triple<Artist, Album, LinkedList<Format>>> list = new LinkedList<Triple<Artist, Album, LinkedList<Format>>>();
		Document doc = MySoup.scrape("http://what.cd/torrents.php?page=" + page + "&searchstr=" + searchString);
		String artistTitle, artistId, artistUrl;
		String albumTitle, albumId, albumUrl, albumYear;
		String formatTitle, formatId, formatUrl;

		Elements elements = doc.getElementsContainingOwnText("group");
		System.out.println(elements.toString());
		return null;

	}
}
