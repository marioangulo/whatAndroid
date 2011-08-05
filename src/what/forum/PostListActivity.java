package what.forum;

import java.io.IOException;
import java.util.ArrayList;

import what.gui.Notification;
import what.gui.R;
import what.gui.ReportSender;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import api.forum.Manager;
import api.soup.MySoup;

/**
 * The view of all posts in a section
 * 
 * @author Tim
 */

public class PostListActivity extends Activity implements OnClickListener, OnTouchListener, OnLongClickListener {
	ScrollView scrollView;
	LinearLayout linearLayout;
	LinearLayout mainLayout;
	LinearLayout topLayout;
	TextView threadTitleView;
	TextView threadPageView;
	TextView threadAuthorView;
	EditText replyTextField;
	Button replyButton;
	TextView quote;
	ArrayList<TextView> postAuthor = new ArrayList<TextView>();
	ArrayList<WebView> postBody = new ArrayList<WebView>();
	TextView previous, next;
	String[] user, id, body;
	String sectionTitle;
	String threadTitleString;
	String threadAuthor;
	String threadUrl;
	StringBuffer quotedText = new StringBuffer();
	int threadPosition;
	int numberOfPosts;
	int threadPage;
	Intent intent;
	Notification n = new Notification();

	ProgressDialog progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// hide keyboard when activity is started
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		createLayout();
		@SuppressWarnings("unused")
		ReportSender sender = new ReportSender(this);
		// Display progress dialog while loading
		progress = new ProgressDialog(this);
		progress.setIndeterminate(true);
		progress.setMessage(getString(R.string.loadposts));
		progress.show();

		Thread loadingThread = new Thread() {
			@Override
			public void run() {
				loadPosts();
				loadingHandler.sendEmptyMessage(0);
			}

			Handler loadingHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					populateView();
					idGenerator();
					progress.dismiss();
				}
			};
		};
		loadingThread.start();
	}

	/**
	 * Create the base layout
	 */
	public void createLayout() {
		this.setContentView(R.layout.posts);
		mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		topLayout = (LinearLayout) findViewById(R.id.topLayout);
		scrollView = (ScrollView) findViewById(R.id.postScrollView);
		linearLayout = (LinearLayout) findViewById(R.id.postLayout);
		threadTitleView = (TextView) findViewById(R.id.threadTitle);
		threadPageView = (TextView) findViewById(R.id.threadPage);
		threadAuthorView = (TextView) findViewById(R.id.threadAuthor);

		replyTextField = (EditText) findViewById(R.id.replyTextField);
		replyTextField.setSingleLine(false);
		replyTextField.setGravity(Gravity.TOP);
		replyTextField.setGravity(Gravity.LEFT);

		replyButton = (Button) findViewById(R.id.replyButton);
		replyTextField.setOnTouchListener(this);
		replyButton.setOnClickListener(this);

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
		threadUrl = Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).getThreadUrl();
	}

	/**
	 * Populate the view with posts
	 */
	public void populateView() {
		if (threadPage == 1) {
			// Disable the previous button on the first page
			Button prevButton = (Button) findViewById(R.id.prevButton);
			AlphaAnimation alphaDown = new AlphaAnimation(1.0f, 0.3f);
			alphaDown.setDuration(0);
			alphaDown.setFillAfter(true);
			prevButton.startAnimation(alphaDown);
			prevButton.setEnabled(false);
			prevButton.setVisibility(View.VISIBLE);
		}

		// threadTitle.setText(threadTitleString + "\t" + "  created by " + threadAuthor + ", page " + threadPage);
		// Set title text views
		threadTitleView.setText(threadTitleString);
		threadPageView.setText(String.valueOf(threadPage));
		threadAuthorView.setText(threadAuthor);

		// Create the post views
		for (int i = 0; i < numberOfPosts; i++) {
			postAuthor.add(new TextView(this));
			postAuthor.get(i).setText(user[i]);
			postAuthor.get(i).setTextSize(17);
			postAuthor.get(i).setOnClickListener(this);

			postBody.add(new WebView(this));
			postBody.get(i).getSettings().setJavaScriptEnabled(true);
			postBody.get(i).setBackgroundColor(Color.GRAY);

			postBody.get(i).loadData(body[i], "text/html", "utf-8");

			postBody.get(i).setPadding(45, 0, 0, 0);
			postBody.get(i).setClickable(true);
			postBody.get(i).setOnLongClickListener(this);

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

	public void prevPage(View v) {
		intent = new Intent(this, what.forum.PostListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		b.putInt("threadPosition", threadPosition);
		b.putString("threadTitle", threadTitleString);
		b.putString("threadAuthor", threadAuthor);
		b.putInt("threadPage", (threadPage - 1));
		intent.putExtras(b);
		Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).clearPosts();
		startActivity(intent);
	}

	/**
	 * Go to the next page
	 */
	public void nextPage(View v) {
		intent = new Intent(this, what.forum.PostListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		b.putInt("threadPosition", threadPosition);
		b.putString("threadTitle", threadTitleString);
		b.putString("threadAuthor", threadAuthor);
		b.putInt("threadPage", (threadPage + 1));
		intent.putExtras(b);
		Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).clearPosts();
		Manager.getForum().getSectionByName(sectionTitle).getThreads().get(threadPosition).startActivity(intent);

		startActivity(getIntent());
		finish();
	}

	/**
	 * Reply to a thread
	 */
	private void reply() {
		MySoup.postReply(threadUrl, replyTextField.getText().toString());
		n.displayToast("Replied", Notification.LENGTH_SHORT, this);

		// refresh page
		startActivity(getIntent());
		finish();
	}

	/**
	 * Quote post and set textfield with quoted text
	 * 
	 * @param i
	 *            post to quote
	 */
	private void quote(int i) {
		n.displayToast("Quoted", Toast.LENGTH_SHORT, this);
		String s = "[quote=" + user[i] + "]" + MySoup.toQuotableString(body[i]) + "[/quote]";
		quotedText.append(s + "\n");
		replyTextField.setText(quotedText.toString());
	}

	public void scrollUp(View v) {
		scrollView.fullScroll(ScrollView.FOCUS_UP);
	}

	@Override
	public void onClick(View v) {
		for (int i = 0; i < numberOfPosts; i++) {
			if (v.getId() == postAuthor.get(i).getId()) {
				openUser(i);
			}
		}
		if (v.getId() == replyButton.getId()) {
			reply();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		for (int i = 0; i < numberOfPosts; i++) {
			if ((v.getId() == postBody.get(i).getId()) && (event.getAction() == MotionEvent.ACTION_DOWN)) {
				quote(i);
			}
		}
		return false;
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
			intent = new Intent(this, what.main.MainPageActivity.class);
			startActivity(intent);
			break;
		case R.id.refreshItem:
			// refresh activity
			startActivity(getIntent());
			finish();
			break;
		case R.id.nextItem:
			nextPage(null);
			break;
		case R.id.backItem:
			finish();
			break;
		case R.id.reportItem:
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("http://v0lu.me/mantis/bug_report_page.php"));
			startActivity(i);
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

	@Override
	public boolean onLongClick(View v) {
		for (int i = 0; i < numberOfPosts; i++) {
			if ((v.getId() == postBody.get(i).getId())) {
				quote(i);
			}
		}
		return false;
	}
}
