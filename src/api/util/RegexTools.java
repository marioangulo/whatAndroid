/**
 * 
 */
package api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tim
 *
 */
public class RegexTools {
	/**
	 * Split the url of a thread in a section
	 * @param string
	 * @return url of thread
	 */
	public String splitThreadUrl(String string) {
		Pattern pattern = Pattern.compile(".*<a href=\"(.*)\" title.*");
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			String s = matcher.group(1);
			s = s.replace("amp;", "");
			s = "http://what.cd/" + s;
			return s;
		}
		return null;
	}
	/**
	 * Split the user in a section
	 * @param string
	 * @return user name
	 */
	public String splitUser(String string) {
		//TODO include user id
		
		Pattern pattern = Pattern.compile(".*>(.*)</a>.*");
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}
	/**
	 * Split a string
	 * @param string String to split
	 * @param from staring at which substring
	 * @param to ending at which substring
	 * @return the split string
	 */
	public String split(String string, String from, String to) {
		Pattern pattern = Pattern.compile(".*"+from + "(.*)"+to + ".*");
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}
	/**
	 * Splits title of a thread
	 * @param string
	 * @return title of thread
	 */
	public  String splitTitle(String string) {
		Pattern pattern = Pattern.compile(".*title=\"(.*)\">.*");
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}
	/**
	 * Gets the poster's name and id
	 * @param string
	 * @return name and id
	 */
	public  String[] splitPoster(String string) {
		String[] toReturn = new String[2];
		Pattern pattern1 = Pattern.compile(".*\">(.*)</a>.*");
		Matcher matcher1 = pattern1.matcher(string);
		if(matcher1.matches()){;
		toReturn[0] = matcher1.group(1);
		}
		Pattern pattern2 = Pattern.compile(".*id=(.*)\">.*");
		Matcher matcher2 = pattern2.matcher(string);
		if(matcher2.matches()){
			toReturn[1] = matcher2.group(1);
		}
		return toReturn;

	}
	/**
	 * Splits the profile text of a user
	 * @param string
	 * @return profile text
	 */
	public  String splitProfileText(String string) {
		Pattern pattern = Pattern.compile(".*<div class=\"pad\">(.*)</div>.*",Pattern.DOTALL);
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}
	/**
	 * Gets the post body
	 * @param string
	 * @return the post body
	 */
	public  String splitPostBody(String string) {
		Pattern pattern = Pattern.compile(".*<div id=\"content.*\">\n(.*)</div>.*",Pattern.DOTALL);
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}
	/**
	 * Splits the avatar
	 * @param string
	 * @return the avatar url
	 */
	public  String splitAvatar(String string) {
		Pattern pattern = Pattern.compile(".*<img src=\"(.*)\" width=.*");
		Matcher matcher = pattern.matcher(string);
		if(matcher.matches()){
			System.out.println(matcher.group(1));
			return matcher.group(1);
		}
		return null;
	}
}
