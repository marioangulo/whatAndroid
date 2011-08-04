package api.torrents;

/**
 * A format is a torrent that is part of an Album
 * 
 * @author Tim
 * 
 */
public class Format {

	String title;
	String id;
	String url;
	String downloadLink;

	/**
	 * @param title
	 *            of format
	 * @param id
	 *            id of format
	 * @param url
	 *            url of format
	 * @param downloadLink
	 *            download link to torrent
	 */
	public Format(String title, String id, String url, String downloadLink) {
		this.title = title;
		this.id = id;
		this.url = url;
		this.downloadLink = downloadLink;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the downloadLink
	 */
	public String getDownloadLink() {
		return downloadLink;
	}

	/**
	 * @param downloadLink
	 *            the downloadLink to set
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Format [title=" + title + ", id=" + id + ", url=" + url + "]";
	}

}
