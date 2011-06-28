package what.login;

import what.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Login screen
 * @author Tim
 *
 */
@SuppressWarnings("unused")
public class WhatAndroidActivity extends Activity implements OnClickListener 
{
	TextView username;
	TextView password;
	Button login;
	CheckBox checkbox;
	private static final String TAG = "WhatAndroidActivity";

	/** 
	 * Called when the activity is first created. 
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//create widgets
		username = (TextView)this.findViewById(R.id.username);
		password = (TextView)this.findViewById(R.id.password);
		checkbox = (CheckBox)this.findViewById(R.id.checkbox);
		checkbox.setOnClickListener(this);
		login = (Button)this.findViewById(R.id.login);
		login.setOnClickListener(this);
	}

	/**
	 * Login to what.cd
	 */
	private void login() {
		String usernameString = username.getText().toString();
		String passwordString = password.getText().toString();
		String loginURL = "http://what.cd/login.php";

		//MySoup.login(loginURL, usernameString, passwordString);
		
		//ActivitySwitcher.switchActivity(this, what.forum.SectionActivity.class);
	}	
	@Override
	public void onClick(View v) {
		/*Notification notification = new Notification();
    	notification.displayAlert("New Subscriptions", "2 unread threads", this);*/
		switch(v.getId()) {
		
		case R.id.checkbox:
			checkbox.setSelected(true);
			//TODO read/write to settings file
			break;
		case R.id.login:
			  Intent prefIntent = new Intent(this,what.forum.SectionListActivity.class);
		      startActivity(prefIntent);
			if(username.getText() != null && password.getText() != null) {
				login();	
			}
			break;
		}
	}
}