package api.forum;

import java.io.IOException;

import api.parser.MusicParser;

public class Tester {
	public Tester() throws IOException {
		System.out.println(MusicParser.parseSearchResult("Gentleman", 0).toString());
	}

	public static void main(String[] args) throws IOException {
		new Tester();
	}
}
