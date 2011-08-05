package api.parser;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.torrents.Album;
import api.torrents.Artist;
import api.torrents.Format;
import api.torrents.SearchResult;
import api.util.Quintuple;
import api.util.RegexTools;
import api.util.Triple;

public class MusicSearchParser {
	static RegexTools regex = new RegexTools();
	static int pos = 0;

	public static void init() {
		pos = 0;
	}

	public static LinkedList<Quintuple<String, String, String, String, String>> parseAlbums() {
		return null;

	}

	public static LinkedList<Triple<String, String, String>> parseArtist(String id) {
		// Document doc = MySoup.scrape("http://what.cd/artist.php?id=" + id);
		return null;
	}

	public static LinkedList<Triple<String, String, String>> parseArtist() {
		return null;
	}

	public static LinkedList<SearchResult> parseSearchResult(String searchString, int page) {
		LinkedList<SearchResult> searchResultList = new LinkedList<SearchResult>();
		Document doc =
				MySoup.scrape("http://what.cd/torrents.php?page=" + page + "+&searchstr=" + searchString
						+ "&taglist=&tags_type=1&order_by=time&order_way=desc&filter_cat%5B1%5D=1");
		String artistTitle, artistId, artistUrl;
		String albumTitle, albumId, albumUrl;
		Elements searchResultElements = doc.getElementById("content").getElementById("torrent_table").getElementsByClass("group");
		for (int i = 0; i < searchResultElements.size(); i++) {
			// itterate through and add artists/albums, unless it is a "various artist", then do something tricky
			if (!(searchResultElements.get(i).getElementsByAttribute("colspan").get(0).getElementsByTag("a").get(0).toString()
					.contains("<a href=\"torrents.php?id="))) {

				artistTitle =
						(searchResultElements.get(i).getElementsByAttribute("colspan").get(0).getElementsByTag("a").get(0).text());
				artistId =
						regex.splitArtistId(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(0).toString());
				artistUrl =
						regex.splitArtistUrl(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(0).toString());

				albumTitle =
						searchResultElements.get(i).getElementsByAttribute("colspan").get(0).getElementsByTag("a").get(1).text();
				albumId =
						regex.splitAlbumId(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(1).toString());
				albumUrl =
						regex.splitAlbumUrl(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(1).toString());

			} else {
				artistTitle = "Various Artists";
				artistId = null;
				artistUrl = null;

				albumTitle =
						searchResultElements.get(i).getElementsByAttribute("colspan").get(0).getElementsByTag("a").get(0).text();
				albumId =
						regex.splitAlbumId(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(0).toString());
				albumUrl =
						regex.splitAlbumUrl(searchResultElements.get(i).getElementsByAttribute("colspan").get(0)
								.getElementsByTag("a").get(0).toString());
			}
			searchResultList.add(new SearchResult(new Artist(artistTitle, artistId, artistUrl), new Album(albumTitle, albumId,
					albumUrl), parseFormats(doc)));
		}
		return searchResultList;

	}

	private static LinkedList<Format> parseFormats(Document doc) {
		LinkedList<Format> formatList = new LinkedList<Format>();
		String formatTitle, formatId, formatUrl, formatDownloadLink;
		Elements formatElements =
				doc.getElementById("content").getElementById("torrent_table").getElementsByClass("group_torrent");
		// itterate through and add formats of each album to the formatList
		String currentClass, nextClass;
		currentClass = formatElements.get(pos).className();
		for (int i = pos; i < formatElements.size(); i++) {
			if (!formatElements.get(i).toString().contains("<td colspan=\"9\" class=\"edition_info\">")) {
				nextClass = formatElements.get(i).className();
				if (currentClass.equals(nextClass)) {
					formatTitle = formatElements.get(i).getElementsByTag("a").get(2).text();
					formatId = regex.splitFormatId(formatElements.get(i).getElementsByTag("a").get(2).toString());
					formatUrl = regex.splitFormatUrl(formatElements.get(i).getElementsByTag("a").get(2).toString());
					formatDownloadLink =
							regex.splitFormatDownloadLink(formatElements.get(i).getElementsByTag("a").get(0).toString());
					formatList.add(new Format(formatTitle, formatId, formatUrl, formatDownloadLink));
				} else {
					pos = i;
					return formatList;
				}
			}
		}
		return null;
	}
}
