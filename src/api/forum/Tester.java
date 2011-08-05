package api.forum;

import java.io.IOException;

import api.parser.ArtistParser;

public class Tester {
	public Tester() throws IOException {
		// System.out.println(MusicParser.parseSearchResult("Gentleman", 0).get(0).getFormatList().get(0).get);
		ArtistParser.init("");
		System.out.println(ArtistParser.parseTitle());
	}

	public static void main(String[] args) throws IOException {
		new Tester();
	}
}
