package api.forum;

import java.io.IOException;

public class Tester {
	public Tester() throws IOException {
		Manager.createSubscriptions("subscriptions");
		Manager.getSubscriptions().addThreads();
		System.out.println(Manager.getSubscriptions().getThreads().toString());
	}

	public static void main(String[] args) throws IOException {
		new Tester();
	}
}
