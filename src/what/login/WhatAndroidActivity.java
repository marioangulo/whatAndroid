//Arbitrary location for the todo list of major things
//TODO 1) authkey fix
//TODO 2) subscribed topics
//TODO 3) monitor sections for new posts
//TODO 4) GUI recode
//TODO 5) A mainpage that displays dynamic feed of forum
//TODO 6) Jump to last read post
//TODO 7) music searching
//TODO 8) downloading/sending to seedbox
//TODO 9) IRC client

package what.login;

import java.io.IOException;

import what.gui.Notification;
import what.gui.R;
import what.gui.ReportSender;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import api.forum.Manager;
import api.soup.MySoup;

/**
 * Login screen
 * 
 * @author Tim
 * 
 */
@SuppressWarnings("unused")
public class WhatAndroidActivity extends Activity implements OnClickListener {
	TextView username;
	TextView password;
	TextView authkey;
	Button login;
	CheckBox checkbox;
	Notification notification = new Notification();
	private static final String TAG = "WhatAndroidActivity";

	/**
	 * Called when the activity is first created.
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// send crash reports
		ReportSender reportSender = new ReportSender(this);

		username = (TextView) this.findViewById(R.id.username);
		password = (TextView) this.findViewById(R.id.password);
		authkey = (TextView) this.findViewById(R.id.authkey);
		checkbox = (CheckBox) this.findViewById(R.id.checkbox);
		checkbox.setOnClickListener(this);
		login = (Button) this.findViewById(R.id.login);
		login.setOnClickListener(this);

	}

	/**
	 * Login to what.cd
	 * 
	 * @throws IOException
	 */
	private void login() throws IOException {

		String usernameString = username.getText().toString();
		String passwordString = password.getText().toString();
		String loginURL = "http://what.cd/login.php";

		MySoup.login(loginURL, usernameString, passwordString);
		MySoup.setAuthKey(authkey.getText().toString());
		if (MySoup.isLoggedIn()) {
			// TODO more suitable location
			Manager.createForum("what.cd Forum");
			try {
				Intent intent = new Intent(this, what.forum.SectionListActivity.class);
				startActivity(intent);
			} catch (Exception e) {
				notification.displayToast("Logged in, but not able to go to next screen, tell Gwindow", Notification.LENGTH_LONG, this);
				e.printStackTrace();
			}

		} else {
			notification.displayError("Error", "Login failed, wrong username/password or a timeout, try again", this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.checkbox:
			checkbox.setSelected(true);
			// TODO read/write to settings file
			break;
		case R.id.login:
			try {
				login();
				// Intent intent = new Intent(this, what.main.MainPageActivity.class);
				// startActivity(intent);

			} catch (IOException e) {
			}
			break;
		}
	}
}