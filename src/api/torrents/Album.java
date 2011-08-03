package api.torrents;

import java.util.LinkedList;

public class Album {
	String title;
	String artist;
	String id;
	String url;
	String year;
	String type;
	String info;
	LinkedList<String> tagList = new LinkedList<String>();
	LinkedList<Artist> artistList = new LinkedList<Artist>();
	LinkedList<Format> formatList = new LinkedList<Format>();

	public Album(String title, String id, String url, String year) {
		this.title = title;
		this.id = id;
		this.url = url;
		this.year = year;
	}

	public Album(String title, String id, String url) {
		this.title = title;
		this.id = id;
		this.url = url;
	}

	public Album(String id, String url) {
		this.id = id;
		this.url = url;
	}

	/**
	 * @param title
	 *            title of the album
	 * @param id
	 *            id of the album
	 * @param url
	 *            url of the album
	 * @param artist
	 *            artist of the album
	 * @param year
	 *            year the album was released
	 * @param type
	 *            whether the album is an "album", "compilation", etc...
	 */
	public Album(String title, String id, String url, String artist, String year, String type) {
		this.title = title;
		this.id = id;
		this.url = url;
		this.year = year;
		this.artist = artist;
		this.type = type;
	}

	/**
	 * Add formats to the album
	 */
	public void addFormats() {

	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @return the tagList
	 */
	public LinkedList<String> getTagList() {
		return tagList;
	}

	/**
	 * Add a tag to the album
	 * 
	 * @param string
	 */
	public void addTag(String string) {
		tagList.add(string);
	}

	/**
	 * @return the artistList
	 */
	public LinkedList<Artist> getArtistList() {
		return artistList;
	}

	/**
	 * @return the formatList
	 */
	public LinkedList<Format> getFormatList() {
		return formatList;
	}
}
