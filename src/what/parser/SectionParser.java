package what.parser;

import java.io.IOException;

public class SectionParser {
	/**
	 * Parse sections and return a string array of each section
	 * @return
	 * @throws IOException
	 */
	public static String[] parseSections() {
		String[] section = {"Announcements", "What.CD", "Projects", "The Laboratory", "Suggestions / Ideas", "Bugs",
				"The Lounge","The Library","Power Users","Comics","Technology","Invites",
				"Music","Vanity House","The Studio","Offered","Vinyl",
				"Help","Tutorials","BitTorrent",
				"Resolved Bugs","Trash"};
		return section;
	}
}
