package api.forum;

import java.io.IOException;

public class Tester {
	public Tester() throws IOException {
		Manager.createForum("forum");
		Manager.getForum().getSectionByName("The Lounge").addThreads(1);
		System.out.println(Manager.getForum().getSectionByName("The Lounge").getThreads().toString());

	}

	public static void main(String[] args) throws IOException {
		new Tester();
	}
}
