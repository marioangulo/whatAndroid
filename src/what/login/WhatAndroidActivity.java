//Arbitrary location for the todo list of major things
//TODO 3) serializible subscriptions/new threads
//TODO 4) GUI recode
//TODO 5) A mainpage that displays dynamic feed of forum
//TODO 6) Jump to last read post
//TODO 7) music searching
//TODO 8) downloading/sending to seedbox
//TODO 9) User Settings
//TODO 10) IRC client

package what.login;

import java.io.IOException;

import what.gui.Notification;
import what.gui.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class WhatAndroidActivity extends Activity implements OnClickListener {
	TextView username;
	TextView password;
	Button login;
	CheckBox checkbox;
	Notification notification = new Notification();

	/**
	 * Called when the activity is first created.
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

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
		ProgressDialog dialog = new ProgressDialog(this);

		Thread loadingThread = new Thread() {
			ProgressDialog dialog = new ProgressDialog(WhatAndroidActivity.this);
			String usernameString = username.getText().toString();
			String passwordString = password.getText().toString();
			String loginURL = "http://what.cd/login.php";

			@Override
			public void run() {
				// Display the progress dialog
				loginHandler.sendEmptyMessage(1);

				// Do the log in
				MySoup.login(loginURL, usernameString, passwordString);
				if (MySoup.isLoggedIn()) {
					try {
						Manager.createForum("what.cd forum");
						Manager.createSubscriptions("subscriptions");
					} catch (IOException e) {
						e.printStackTrace();
					}
					Intent intent = new Intent(WhatAndroidActivity.this, what.forum.SectionListActivity.class);

					startActivity(intent);
				} else {
					notification.displayError("Error", "Login failed, wrong username/password or a timeout, try again", WhatAndroidActivity.this);
				}

				// Dismiss the dialog
				loginHandler.sendEmptyMessage(2);
			}

			private Handler loginHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 1) {
						dialog.setIndeterminate(true);
						dialog.setMessage(getString(R.string.loggingin));
						dialog.show();
					} else if (msg.what == 2) {
						dialog.dismiss();
					}
				}
			};
		};
		loadingThread.start();
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
			}
			break;
		}
	}
}