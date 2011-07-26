package api.forum;

import java.io.IOException;

public class Tester {
	public Tester() throws IOException {
		Manager.createForum("what.cd");
		Manager.createSubscriptions("Subscriptions");
		System.out.println(Manager.getManagers());
	}

	public static void main(String[] args) throws IOException {
		new Tester();
	}
}
