package what.forum;

import java.io.IOException;
import java.util.ArrayList;

import what.gui.Notification;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import api.forum.Manager;

/**
 * @author Tim The view of all posts in a section
 */

public class PostListActivity extends Activity implements OnClickListener {
	ScrollView scrollView;
	LinearLayout linearLayout;
	LinearLayout mainLayout;
	FrameLayout topLayout;
	TextView threadTitle;
	ArrayList<TextView> postAuthor = new ArrayList<TextView>();
	ArrayList<TextView> postBody = new ArrayList<TextView>();
	String[] user, id, body;
	String sectionTitle;
	String threadTitleString;
	String threadAuthor;
	int threadPosition;
	int numberOfPosts;
	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// addButtons();
		createLayout();
		loadPosts();
		populateView();
		idGenerator();
	}

	/**
	 * Create the base layout
	 */
	public void createLayout() {
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);

		scrollView = new ScrollView(this);
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(linearLayout);

		topLayout = new FrameLayout(this);
		threadTitle = new TextView(this);
		topLayout.addView(threadTitle);

		mainLayout.addView(topLayout);
		mainLayout.addView(scrollView);
		this.setContentView(mainLayout);
	}

	/**
	 * Loads posts and unload the bundle
	 */
	public void loadPosts() {
		Bundle b = this.getIntent().getExtras();
		sectionTitle = b.getString("sectionTitle");
		threadPosition = b.getInt("threadPosition");
		threadTitleString = b.getString("threadTitle");
		threadAuthor = b.getString("threadAuthor");
		Log.v("TAG", sectionTitle + threadPosition);

		try {
			Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).addPosts();
		} catch (IOException e) {
			e.printStackTrace();
		}

		numberOfPosts = Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).getPost().size();
		user = Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).getPostUserArray();
		id = Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).getPostUserIDArray();
		body = Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).getPostBodyArray();

	}

	/**
	 * Populate the view with posts
	 */
	public void populateView() {
		threadTitle.setText(threadTitleString + "\t" + "  created by " + threadAuthor);
		threadTitle.setTextSize(22);
		threadTitle.setOnClickListener(this);
		// arbitrary id that doesn't colide with anyothers
		threadTitle.setId(7795);
		for (int i = 0; i < numberOfPosts; i++) {
			postAuthor.add(new TextView(this));
			postAuthor.get(i).setText(user[i]);
			postAuthor.get(i).setTextSize(17);
			postAuthor.get(i).setOnClickListener(this);

			postBody.add(new TextView(this));
			postBody.get(i).setText(body[i]);
			postBody.get(i).setPadding(45, 0, 0, 0);
			postBody.get(i).setOnClickListener(this);

			linearLayout.addView(postAuthor.get(i));
			linearLayout.addView(postBody.get(i));
		}
	}

	/**
	 * Generate ids for all elements
	 */
	private void idGenerator() {
		for (int i = 0; i < numberOfPosts; i++) {
			postAuthor.get(i).setId(i);
			postBody.get(i).setId(i + numberOfPosts);
		}
	}

	/**
	 * Opens the user profile in a popup
	 * 
	 * @param j
	 * @param k
	 *            identifies between which userlist to choose from
	 * 
	 */
	private void openUser(int j) {
		intent = new Intent(this, what.user.UserProfileActivity.class);
		Bundle b = new Bundle();
		b.putString("userName", user[j]);
		b.putString("userID", id[j]);
		intent.putExtras(b);
		startActivityForResult(intent, 0);

	}

	@Override
	public void onClick(View v) {
		Notification n = new Notification();
		if (v.getId() == threadTitle.getId()) {
			scrollView.fullScroll(ScrollView.FOCUS_UP);
		}
		for (int i = 0; i < numberOfPosts; i++) {
			if (v.getId() == postAuthor.get(i).getId()) {
				openUser(i);
			}
			if (v.getId() == postBody.get(i).getId()) {
				n.displayToast("Quoted", Toast.LENGTH_SHORT, this);
			}

		}
	}
}
