package api.torrents;

import java.util.LinkedList;

import api.parser.ArtistParser;

public class Artist {
	String title;
	String id;
	String url;
	String imageUrl;
	int groups, torrents, seeders, leechers, snatches;
	String info;
	LinkedList<Artist> similiarArtistList = new LinkedList<Artist>();
	LinkedList<String> tagList = new LinkedList<String>();
	LinkedList<Album> albumList = new LinkedList<Album>();
	LinkedList<Request> requestList = new LinkedList<Request>();

	public Artist(String id, String url) {
		this.id = id;
		this.url = url;
		ArtistParser.init(url);
	}

	/**
	 * @param title
	 *            title of artist
	 * @param id
	 *            id of artist
	 * @param url
	 *            url of artist
	 */
	public Artist(String title, String id, String url) {
		this.title = title;
		this.id = id;
		this.url = url;
		ArtistParser.init(url);
	}

	/**
	 * Auto add all the data
	 */
	public void init() {
		addAlbums();
		addInfo();
	}

	/**
	 * Add albums to the artist
	 */
	public void addAlbums() {

	}

	public void addInfo() {

	}

	/**
	 * @return the title
	 */
	public String getName() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setName(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the groups
	 */
	public int getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(int groups) {
		this.groups = groups;
	}

	/**
	 * @return the torrents
	 */
	public int getTorrents() {
		return torrents;
	}

	/**
	 * @param torrents
	 *            the torrents to set
	 */
	public void setTorrents(int torrents) {
		this.torrents = torrents;
	}

	/**
	 * @return the seeders
	 */
	public int getSeeders() {
		return seeders;
	}

	/**
	 * @param seeders
	 *            the seeders to set
	 */
	public void setSeeders(int seeders) {
		this.seeders = seeders;
	}

	/**
	 * @return the leechers
	 */
	public int getLeechers() {
		return leechers;
	}

	/**
	 * @param leechers
	 *            the leechers to set
	 */
	public void setLeechers(int leechers) {
		this.leechers = leechers;
	}

	/**
	 * @return the snatches
	 */
	public int getSnatches() {
		return snatches;
	}

	/**
	 * @param snatches
	 *            the snatches to set
	 */
	public void setSnatches(int snatches) {
		this.snatches = snatches;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the similiarArtistList
	 */
	public LinkedList<Artist> getSimiliarArtistList() {
		return similiarArtistList;
	}

	/**
	 * @param similiarArtistList
	 *            the similiarArtistList to set
	 */
	public void setSimiliarArtistList(LinkedList<Artist> similiarArtistList) {
		this.similiarArtistList = similiarArtistList;
	}

	/**
	 * @return the tagList
	 */
	public LinkedList<String> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList
	 *            the tagList to set
	 */
	public void setTagList(LinkedList<String> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the albumList
	 */
	public LinkedList<Album> getAlbumList() {
		return albumList;
	}

	/**
	 * @param albumList
	 *            the albumList to set
	 */
	public void setAlbumList(LinkedList<Album> albumList) {
		this.albumList = albumList;
	}

	/**
	 * @return the requestList
	 */
	public LinkedList<Request> getRequestList() {
		return requestList;
	}

	/**
	 * @param requestList
	 *            the requestList to set
	 */
	public void setRequestList(LinkedList<Request> requestList) {
		this.requestList = requestList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Artist [title=" + title + ", id=" + id + ", url=" + url + ", groups=" + groups + ", torrents=" + torrents
				+ ", seeders=" + seeders + ", leechers=" + leechers + ", snatches=" + snatches + ", info=" + info
				+ ", similiarArtistList=" + similiarArtistList + ", tagList=" + tagList + ", albumList=" + albumList
				+ ", requestList=" + requestList + "]";
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
