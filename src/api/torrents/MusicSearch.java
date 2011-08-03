package api.torrents;

import java.util.LinkedList;

public class MusicSearch {
	String searchString;
	int page = 1;
	LinkedList<SearchResult> searchResultList = new LinkedList<SearchResult>();

	public MusicSearch(String searchString, int page) {
		this.searchString = searchString;
		this.page = page;
		addSearchResults(searchString, page);
	}

	private void addSearchResults(String searchString, int page) {
		searchResultList.clear();

	}

	public void clear() {
		searchResultList.clear();
	}

	public void nextPage() {
		page++;
		addSearchResults(searchString, page);
	}
}
