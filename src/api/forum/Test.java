package api.forum;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/*
import settings.Settings;
import user.UserProfile;
*/
public class Test {
	/*String username = Settings.getUsername();
	String password = Settings.getPassword();
	String userID = Settings.getID();
	Forum forum;*/
	public Test() throws IOException {
	/*	forum = new Forum("what.cd");
		System.out.println(forum.getSectionByName("The Lounge").getThreads().get(19).getPost().get(3).getUserInForum());
		UserProfile userProfile = new UserProfile(forum.getSectionByName("The Lounge").getThreads().get(19).getPost().get(3).getUserInForum());
		System.out.println(userProfile.toString());

		Connection.Response res = Jsoup.connect("http://www.what.cd/login.php")
		.data("username", "Gwindow", "password", "t2ustUdE")
		.method(Method.POST)
		.execute();

		//System.out.println(res.cookies());
		String sessionId = res.cookie("session"); // you will need to check what th
		Document doc2 = Jsoup.connect("http://what.cd/user.php?action=edit&userid=135837")
		.cookie("session", sessionId).data("info", "test").data("userform").post()
		
		Connection.Response res2 = Jsoup.connect("http://what.cd/user.php?action=edit&userid=135837").cookie("session", sessionId)
		.data("info", "testing")
		.method(Method.POST)
		.execute();
		
		System.out.println(res2.body());
	}
	public static void main (String[]args) throws IOException {
		new Test();
	}*/
}
}
