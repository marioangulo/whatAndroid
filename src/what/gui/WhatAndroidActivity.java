package what.gui;
import what.soup.MySoup;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
        login = (Button)this.findViewById(R.id.login);
        login.setOnClickListener(this);
    }
    /**
     * Login to what.cd
     */
    public void login() {
    	String usernameString = username.getText().toString();
    	String passwordString = password.getText().toString();
    	String loginURL = "http://what.cd/login.php";

    	//MySoup.login(loginURL, usernameString, passwordString);
    }	
    @Override
	public void onClick(View e) {
    	AlertPopUp p = new AlertPopUp();
    	p.display("New Subscriptions", "2 unread threads", this);
    	
    	if(username.getText() != null && password.getText() != null) {
			login();
		}
	}
}