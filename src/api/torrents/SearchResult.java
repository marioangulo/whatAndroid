package api.torrents;

import java.util.LinkedList;

public class SearchResult {
	Artist artist;
	Album album;
	LinkedList<Format> formatList;

	/**
	 * Create a new search result
	 * 
	 * @param artist
	 *            artist in the search result
	 * @param album
	 *            album in the search result
	 * @param formatList
	 *            list of formats in the search result
	 */
	public SearchResult(Artist artist, Album album, LinkedList<Format> formatList) {
		this.artist = artist;
		this.album = album;
		this.formatList = formatList;
	}
}
