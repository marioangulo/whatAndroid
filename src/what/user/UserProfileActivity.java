package what.user;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import api.profile.UserProfile;

/**
 * A popup dialog of a user's profile
 * 
 * @author Tim
 * 
 */
public class UserProfileActivity extends Activity implements OnClickListener {
	LinearLayout mainLayout;
	ImageView avatarView;
	Bitmap avatarImg;
	TextView basicStatsView;
	Button moreButton;
	Button closeButton;
	String userName, userID;
	UserProfile userProfile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createLayout();
		loadProfile();
	}

	private void createLayout() {

		mainLayout = new LinearLayout(this);

		avatarView = new ImageView(this);
		avatarView.setAdjustViewBounds(true);
		avatarView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		avatarView.setAdjustViewBounds(true);
		avatarView.setMaxHeight(400);
		avatarView.setMaxWidth(150);
		mainLayout.addView(avatarView);

		basicStatsView = new TextView(this);
		mainLayout.addView(basicStatsView);

		moreButton = new Button(this);
		moreButton.setText("More Stats");
		moreButton.setId(0);
		moreButton.setOnClickListener(this);

		closeButton = new Button(this);
		closeButton.setText("Close");
		moreButton.setId(1);
		closeButton.setOnClickListener(this);

		this.setContentView(mainLayout);
	}

	private void loadProfile() {
		Bundle b = this.getIntent().getExtras();
		userName = b.getString("userName");
		userID = b.getString("userID");
		try {
			userProfile = new UserProfile(userName, userID);
			userProfile.addAvatar();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle(userName + "'s Profile");
		downloadAvatar();
		loadBasicStats();

	}

	private void loadBasicStats() {
		userProfile.getStats().addBasicStats();
		StringBuffer buffer = new StringBuffer();
		for (String s : userProfile.getStats().getBasicStats()) {
			buffer.append(s + "\n");
		}
		String text = buffer.toString();
		basicStatsView.setText(text);
	}

	private void loadExtraStats() {
		TextView extraStatsView = new TextView(this);
		mainLayout.addView(extraStatsView);

	}

	private void downloadAvatar() {
		String avatarURL = userProfile.getAvatarURL();
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(avatarURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			avatarImg = BitmapFactory.decodeStream(is);
			avatarView.setImageBitmap(avatarImg);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == moreButton.getId()) {
			loadExtraStats();
		}
		if (v.getId() == closeButton.getId()) {
			finish();
		}
	}
}
