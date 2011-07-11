package what.login;

import java.io.IOException;

import what.gui.ActivityStack;
import what.gui.Notification;
import what.gui.R;
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

		username = (TextView) this.findViewById(R.id.username);
		password = (TextView) this.findViewById(R.id.password);
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
		if (MySoup.isLoggedIn()) {
			ActivityStack.push(what.forum.SectionListActivity.class);
			// TODO more suitable location
			Manager.createForum("what.cd Forum");
			Intent intent = new Intent(this, what.forum.SectionListActivity.class);
			startActivity(intent);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated catch block
			break;
		}
	}
}