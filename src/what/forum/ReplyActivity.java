package what.forum;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import api.soup.MySoup;

/**
 * A popup dialog of a user's profile
 * 
 * @author Tim
 * 
 */
public class ReplyActivity extends Activity implements OnClickListener {
	LinearLayout mainLayout;
	String threadUrl;
	String quotedText;
	Button replyButton, closeButton;
	EditText textInput;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getBundle();
		createLayout();
	}

	/**
	 * Create the layout
	 */
	private void createLayout() {
		mainLayout = new LinearLayout(this);
		textInput = new EditText(this);
		textInput.setGravity(Gravity.TOP);
		textInput.setGravity(Gravity.LEFT);
		textInput.setSingleLine(false);
		textInput.setMaxWidth(250);
		textInput.setMinimumHeight(200);
		textInput.setMinimumWidth(250);
		textInput.setText(quotedText);
		mainLayout.addView(textInput);
		replyButton = new Button(this);
		replyButton.setText("Reply");
		replyButton.setId(1);
		replyButton.setOnClickListener(this);
		mainLayout.addView(replyButton);
		closeButton = new Button(this);
		closeButton.setText("Close");
		closeButton.setId(0);
		closeButton.setOnClickListener(this);
		mainLayout.addView(closeButton);
		this.setContentView(mainLayout);
	}

	/**
	 * Get the bundle
	 */
	private void getBundle() {
		Bundle b = this.getIntent().getExtras();
		threadUrl = b.getString("threadUrl");
		quotedText = b.getString("quotedText");
		Log.v("threadurl", threadUrl);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == replyButton.getId()) {
			MySoup.postReply(threadUrl, textInput.getText().toString());
			finish();
		}
		if (v.getId() == closeButton.getId()) {
			finish();
		}
	}
}
