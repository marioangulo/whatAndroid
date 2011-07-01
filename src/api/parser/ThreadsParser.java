package api.parser;
import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import api.soup.MySoup;
import api.util.*;
import api.forum.*;

/**
 * @author Tim
 * Parses threads depending on sections
 */
public class ThreadsParser {
	static RegexTools regex = new RegexTools();
	public static LinkedList<Triple<String, String, String>> parseThreads(Section s) throws IOException {
		LinkedList<Triple<String,String,String>> threadList = new LinkedList<Triple<String,String,String>>();
		if(s.getSectionTitle().equalsIgnoreCase("Announcements")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=19");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("What.CD")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=13");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Projects")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=45");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("The Laboratory")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=43");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Suggestions / Ideas")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=9");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Bugs")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=27");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("The Lounge")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=7");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("The Library")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=26");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Power Users")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=10");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Comics")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=37");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Technology")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=20");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Invites")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=23");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Music")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=17");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		//////
		if(s.getSectionTitle().equalsIgnoreCase("Vanity House")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=25");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}	
		if(s.getSectionTitle().equalsIgnoreCase("The Studio")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=40");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Offered")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=15");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}	if(s.getSectionTitle().equalsIgnoreCase("Vinyl")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=36");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}	if(s.getSectionTitle().equalsIgnoreCase("Help")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=8");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}	if(s.getSectionTitle().equalsIgnoreCase("Tutorials")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=18");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("BitTorrent")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=14");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Resolved Bugs")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=32");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		if(s.getSectionTitle().equalsIgnoreCase("Trash")) {
			Document doc = MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=12");
			Elements rowsa = doc.getElementsByClass("rowa");
			Elements rowsb = doc.getElementsByClass("rowb");
			for(int i = 0; i<rowsb.size(); i++) {
				//get author
				String authora = regex.splitUser((rowsa.get(i).getElementsByTag("td").get(3)).toString());
				String authorb = regex.splitUser((rowsb.get(i).getElementsByTag("td").get(3)).toString());
				//gets last poster
				String lastPostera = regex.splitUser((rowsa.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a")).toString());
				String lastPosterb = regex.splitUser(rowsb.get(i).getElementsByClass("last_poster").get(0).getElementsByTag("a").toString());
				//get thread title
				String titlea = regex.splitUser(rowsa.get(i).getElementsByTag("a").get(0).toString());
				String titleb = regex.splitUser(rowsb.get(i).getElementsByTag("a").get(0).toString());

				threadList.add(new Triple<String,String,String>(titlea,authora,lastPostera));
				threadList.add(new Triple<String,String,String>(titleb,authorb,lastPosterb));
			}
		}
		return threadList;
	}
}
