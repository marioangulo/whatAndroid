package what.settings;

import android.app.Activity;
import android.content.SharedPreferences;

public class Settings extends Activity {
	public static final String NAME = "what.cd_app_preference_file";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String REMEMBERME = "rememberme";

	public void saveSettings(String username, String password, boolean rememberMe) {
		getSharedPreferences(NAME, MODE_PRIVATE).edit().putString(USERNAME, username).putString(PASSWORD, password).putBoolean(REMEMBERME, rememberMe).commit();
	}

	public String getUserName() {
		SharedPreferences pref = getSharedPreferences(NAME, MODE_PRIVATE);
		return pref.getString(USERNAME, null);
	}

	public String getPassword() {
		SharedPreferences pref = getSharedPreferences(NAME, MODE_PRIVATE);
		return pref.getString(PASSWORD, null);
	}

	public boolean getRememberMe() {
		SharedPreferences pref = getSharedPreferences(NAME, MODE_PRIVATE);
		return pref.getBoolean(REMEMBERME, false);
	}
}
