package api.parser;

import java.io.IOException;

public class SectionParser {
	
	
	/**
	 * Parse sections and return a string array of each section
	 * @return
	 * @throws IOException
	 */
	public static String[] parseSiteSections() {
		/*String[] section = {"Announcements", "What.CD", "Projects", "The Laboratory", "Suggestions / Ideas", "Bugs",
				"The Lounge","The Library","Power Users","Comics","Technology","Invites",
				"Music","Vanity House","The Studio","Offered","Vinyl",
				"Help","Tutorials","BitTorrent",
				"Resolved Bugs","Trash"};*/
		String[] section = {"Announcements", "What.CD", "Projects", "The Laboratory", "Suggestions / Ideas", "Bugs"};
		return section;
	}
	public static String[] parseCommunitySections() {
		String[] section = {
				"The Lounge","The Library","Power Users","Comics","Technology","Invites",};
		return section;
	}
	public static String[] parseMusicSections() {
		String[] section = {
				"Music","Vanity House","The Studio","Offered","Vinyl"};
		return section;
	}
	public static String[] parseHelpSections() {
		String[] section = {
				"Help","Tutorials","BitTorrent"};
		return section;
	}
	public static String[] parseTrashSections() {
		String[] section = {
				"Resolved Bugs","Trash"};
		return section;
	}
	public static String[] getMasterList() {
		String[] section = {"Announcements", "What.CD", "Projects", "The Laboratory", "Suggestions / Ideas", "Bugs",
		"The Lounge","The Library","Power Users","Comics","Technology","Invites",
		"Music","Vanity House","The Studio","Offered","Vinyl",
		"Help","Tutorials","BitTorrent",
		"Resolved Bugs","Trash"};
		return section;
	}
	
}


