package what.forum;

import java.io.IOException;
import java.util.ArrayList;

import what.gui.Notification;
import what.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
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
	ArrayList<WebView> postBody = new ArrayList<WebView>();
	TextView previous, next;
	String[] user, id, body;
	String sectionTitle;
	String threadTitleString;
	String threadAuthor;
	int threadPosition;
	int numberOfPosts;
	int threadPage;
	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createLayout();
		loadPosts();
		populateView();
		idGenerator();
		// addButtons();
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
		threadPage = b.getInt("threadPage");
		Log.v("TAG", sectionTitle + threadPosition);

		try {
			Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).addPosts(threadPage);
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
		threadTitle.setText(threadTitleString + "\t" + "  created by " + threadAuthor + ", page " + threadPage);
		threadTitle.setTextSize(22);
		threadTitle.setOnClickListener(this);
		// arbitrary id that doesn't colide with anyothers
		threadTitle.setId(7795);
		for (int i = 0; i < numberOfPosts; i++) {

			postAuthor.add(new TextView(this));
			postAuthor.get(i).setText(user[i]);
			postAuthor.get(i).setTextSize(17);
			postAuthor.get(i).setOnClickListener(this);

			postBody.add(new WebView(this));
			postBody.get(i).getSettings().setJavaScriptEnabled(true);
			postBody.get(i).setBackgroundColor(Color.GRAY);

			postBody.get(i).loadData(body[i], "", "utf-8");
			postBody.get(i).setPadding(45, 0, 0, 0);
			postBody.get(i).setOnClickListener(this);

			linearLayout.addView(postAuthor.get(i));
			linearLayout.addView(postBody.get(i));
		}
	}

	/**
	 * Adds a row of "buttons" to the end of the table
	 */
	private void addButtons() {
		// TODO make this more better
		previous = new TextView(this);
		previous.setText("Previous");
		previous.setTextSize(20);
		previous.setTextColor(Color.RED);
		previous.setId(1000);
		previous.setOnClickListener(this);

		next = new TextView(this);
		next.setText("next");
		next.setTextSize(20);
		next.setTextColor(Color.RED);
		next.setId(1000);
		next.setOnClickListener(this);

		linearLayout.addView(previous);
		linearLayout.addView(next);
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

	/**
	 * Go to the next page
	 */
	private void nextPage() {
		intent = new Intent(this, what.forum.PostListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		b.putInt("threadPosition", threadPosition);
		b.putString("threadTitle", threadTitleString);
		b.putString("threadAuthor", threadAuthor);
		threadPage = threadPage + 1;
		b.putInt("threadPage", threadPage);
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
		/*
		 * if (v.getId() == previous.getId()) { finish(); } if (v.getId() == next.getId()) { nextPage(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.postlistmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.closeItem:
			closeOptionsMenu();
			break;
		case R.id.subscriptionsItem:
			// TODO open subscriptions
			break;
		case R.id.refreshItem:
			// refresh activity
			startActivity(getIntent());
			finish();
			break;
		case R.id.nextItem:
			nextPage();
			break;
		case R.id.backItem:
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void openOptionsMenu() {
		super.openOptionsMenu();
	}

	@Override
	public void closeOptionsMenu() {
		super.closeOptionsMenu();
	}
}
