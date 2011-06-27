package what.gui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//import what.jsoup.*;
/**
 * Login screen
 * @author Tim
 *
 */
public class WhatAndroidActivity extends Activity implements OnClickListener 
{
	TextView username;
	TextView password;
	Button login;
	private static final String TAG = "WhatAndroidActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //create widgets
        username = (TextView)this.findViewById(R.id.username);
        password = (TextView)this.findViewById(R.id.password);
        login = (Button)this.findViewById(R.id.login);
        login.setOnClickListener(this);
    }
    public void login() {
    	String usernameString = username.getText().toString();
    	String passwordString = password.getText().toString();
    	String loginURL = "http://what.cd/login.php";
    	Log.v(TAG,loginURL + "," + usernameString +", " + passwordString);
    	//MySoup.login(loginURL, usernameString, passwordString);
    	
    }	
    @Override
	public void onClick(View e) {
		if(username.getText() != null && password.getText() != null) {
			login();
		}

	}
}