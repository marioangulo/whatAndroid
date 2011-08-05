package api.torrents;

import java.util.LinkedList;

import api.parser.MusicSearchParser;
import api.util.CouldNotLoadException;

/**
 * Search for Music
 * 
 * @author Tim
 * 
 */
public class MusicSearch {
	String searchString;
	int page = 1;
	LinkedList<SearchResult> searchResultList = new LinkedList<SearchResult>();

	/**
	 * Search for Music
	 * 
	 * @param searchString
	 *            String to search for
	 * @param page
	 *            the page to search on, should start on 1 and work up
	 * @throws CouldNotLoadException
	 */
	public MusicSearch(String searchString, int page) throws CouldNotLoadException {
		this.searchString = searchString;
		this.page = page;
		MusicSearchParser.init();
		addSearchResults(searchString, page);
	}

	private void addSearchResults(String searchString, int page) throws CouldNotLoadException {
		searchResultList.clear();
		try {
			searchResultList = MusicSearchParser.parseSearchResult(searchString, page);
		} catch (Exception e) {
			throw new CouldNotLoadException("YOUR SEARCH DID NOT MATCH ANYTHING");
		}
	}

	public LinkedList<SearchResult> getSearchResultList() {
		return searchResultList;
	}

	public void clear() {
		searchResultList.clear();
	}

	public void nextPage() throws CouldNotLoadException {
		page++;
		addSearchResults(searchString, page);
	}
}
