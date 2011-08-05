//Arbitrary location for the todo list of major things
//TODO 2) pages for threads and last read
//TODO 3) serializible subscriptions/new threads
//TODO 4) GUI recode
//TODO 5) A mainpage that displays dynamic feed of forum
//TODO 6) Jump to last read post
//TODO 7) music searching
//TODO 8) downloading/sending to seedbox
//TODO 9) User Settings
//TODO 10) IRC client

// Gui to-dos

//TODO GUI -1) scroll in replyTextField and highlight qouting 
//TODO GUI 0) jump to last read arrow
//TODO GUI 1) Make sure all loading processes are threaded
//TODO GUI 2) Change post quote to tap and hold
//TODO GUI 3) Add reply box at bottom of posts activity
//TODO GUI 4) Finish converting all layouts to XML
//TODO GUI 5) Finish revamping each activity's GUI
//TODO GUI 6) Add landing page with "what's new"
//TODO GUI 7) Create widget GUI
//TODO GUI 8) Convert all Activities to fragments
//TODO GUI 9) Create layouts for tablets
//TODO GUI 10) Move most functions from menus onto screen for better UX
//TODO GUI 11) Have post activity save info so no need to reload on orientation switch
//TODO GUI 12) Have post activity reload itself on page change instead of loading new activity

package what.login;

import java.io.IOException;

import what.gui.Notification;
import what.gui.R;
import what.gui.ReportSender;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
	private String VERSION = "0.6";
	private String SITE = "http://timmikeladze.github.com/whatAndroid/index.html";
	TextView username;
	TextView password;
	Button login;
	CheckBox checkbox;
	Notification notification = new Notification();

	SharedPreferences settings;
	SharedPreferences.Editor settingsEditor;

	/**
	 * Called when the activity is first created.
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		@SuppressWarnings("unused")
		ReportSender sender = new ReportSender(this);
		try {
			checkForUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Set UI component references
		username = (TextView) this.findViewById(R.id.username);
		password = (TextView) this.findViewById(R.id.password);
		checkbox = (CheckBox) this.findViewById(R.id.checkbox);
		checkbox.setOnClickListener(this);
		login = (Button) this.findViewById(R.id.login);
		login.setOnClickListener(this);

		// Setup saved settings
		settings = getSharedPreferences("settings", MODE_PRIVATE);
		settingsEditor = settings.edit();
		String savedUsername = settings.getString("username", "");
		if (!savedUsername.equals("")) {
			username.setText(savedUsername);
			checkbox.setChecked(true);
		}

	}

	/**
	 * Check if update exists by comparing versions
	 */
	private void checkForUpdate() {
		double updateversion = Double.parseDouble(MySoup.getUpdateVersion(SITE));
		double currentversion = Double.parseDouble(VERSION);
		if (updateversion > currentversion) {
			displayAlert("", "Update available, would you like to install it?", this);
		}
	}

	/**
	 * Open the download link to the update
	 */
	private void openUpdate() {
		String url = MySoup.getUpdateLink(SITE);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		finish();
	}

	public void displayAlert(String title, String message, Context context) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(message)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						openUpdate();
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				}).show();
	}

	/**
	 * Login to what.cd
	 * 
	 * @throws IOException
	 */
	private void login() throws IOException {
		// Save username
		if (checkbox.isChecked()) {
			settingsEditor.putString("username", username.getText().toString());
			settingsEditor.commit();
		} else {
			settingsEditor.putString("username", "");
		}

		@SuppressWarnings("unused")
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
					// Start the next activity
					loginHandler.sendEmptyMessage(2);
				} else {
					// Display the error message
					loginHandler.sendEmptyMessage(3);
				}
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
						Intent intent = new Intent(WhatAndroidActivity.this, what.forum.SectionListActivity.class);
						startActivity(intent);
					} else if (msg.what == 3) {
						dialog.dismiss();
						notification.displayError("Error", "Login failed, wrong username/password or a timeout, try again",
								WhatAndroidActivity.this);
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
