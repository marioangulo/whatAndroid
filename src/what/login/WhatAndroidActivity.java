package what.login;

import java.io.IOException;

import what.gui.ActivityStack;
import what.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import api.forum.Manager;
import api.soup.MySoup;

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

		username = (TextView)this.findViewById(R.id.username);
		password = (TextView)this.findViewById(R.id.password);
		checkbox = (CheckBox)this.findViewById(R.id.checkbox);
		checkbox.setOnClickListener(this);
		login = (Button)this.findViewById(R.id.login);
		login.setOnClickListener(this);
	}

	/**
	 * Login to what.cd
	 * @throws IOException 
	 */
	private void login() throws IOException {
		
		String usernameString = username.getText().toString();
		String passwordString = password.getText().toString();
		String loginURL = "http://what.cd/login.php";

		MySoup.login(loginURL, usernameString, passwordString);
		ActivityStack.push(what.forum.SectionListActivity.class);
		//TODO more suitable location
		Manager.createForum("what.cd Forum");
		
		Intent intent = new Intent(this,what.forum.SectionListActivity.class);
		startActivity(intent);
		
	}	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.checkbox:
			checkbox.setSelected(true);
			//TODO read/write to settings file
			break;
		case R.id.login:
			//TODO fix null problem
			if(username.getText() != null && password.getText() != null) {
				try {
					login();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			break;
		}
	}
}