package api.soup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.util.Log;

/**
 * JSoup methods to login and get pages
 * 
 * @author Tim
 * 
 */
public class MySoup {
	private static String sessionId;
	private static DefaultHttpClient httpclient = new DefaultHttpClient();

	/**
	 * Login to a site
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public static void login(String url, String username, String password) {
		try {
			// login
			Connection.Response res =
					Jsoup.connect(url).data("username", username, "password", password)
							.method(Method.POST).timeout(30000).execute();
			// set cookie
			sessionId = res.cookie("session");
			httpClientLogin(username, password);
		} catch (IOException e) {
		}
	}

	private static void httpClientLogin(String username, String password)
			throws ClientProtocolException, IOException {

		HttpGet httpget = new HttpGet("http://what.cd/");

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		Log.v("httpclient", ("Login form get: " + response.getStatusLine()));
		if (entity != null) {
			entity.consumeContent();
		}
		Log.v("httpclient", ("Initial set of cookies:"));
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		if (cookies.isEmpty()) {
			Log.v("httpclient", ("None"));
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				Log.v("httpclient", ("- " + cookies.get(i).toString()));
			}
		}

		HttpPost httpost = new HttpPost("http://what.cd/login.php");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		response = httpclient.execute(httpost);
		entity = response.getEntity();

		Log.v("httpclient", ("Login form get: " + response.getStatusLine()));
		if (entity != null) {
			entity.consumeContent();
		}

		Log.v("httpclient", ("Post logon cookies:"));
		cookies = httpclient.getCookieStore().getCookies();
		if (cookies.isEmpty()) {
			Log.v("httpclient", ("None"));
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				Log.v("httpclient", ("- " + cookies.get(i).toString()));
			}
		}
		// HttpGet httpget2 = new HttpGet("http://what.cd/userhistory.php?action=subscriptions");

		// httpclient.getConnectionManager().shutdown();
	}

	public static void httpClientScrape(String page) {
		HttpGet httpget = new HttpGet(page);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inputStreamToString(response.getEntity().getContent());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static StringBuilder inputStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("httpclient", total.toString());
		// Return full string
		return total;
	}

	/**
	 * Check if user was able to login
	 * 
	 * @return true if logged in
	 */
	public static boolean isLoggedIn() {
		if (sessionId != null)
			return true;
		return false;
	}

	/**
	 * Return a Document of a page that has a cookie
	 * 
	 * @param page
	 * @return Document
	 */
	public static Document scrape(String page) {
		Document doc = null;
		try {
			doc = Jsoup.connect(page).cookie("session", sessionId).timeout(30000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/* e.printStackTrace(); */
		}
		return doc;
	}
}