package what.forum;

import java.io.IOException;
import java.util.ArrayList;

import what.gui.R;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import api.forum.Manager;

/**
 * View of all the threads in a section
 * 
 * @author Tim
 * 
 */
public class ThreadListActivity extends ListActivity implements OnClickListener {

	ArrayList<TableRow> rowList = new ArrayList<TableRow>();
	ArrayList<TextView> titleList = new ArrayList<TextView>();
	ArrayList<TextView> lastPosterList = new ArrayList<TextView>();
	ArrayList<TextView> authorList = new ArrayList<TextView>();
	TextView sectionTitleView;
	private int counter;
	private int page;
	String[] title, lastposter, author, threadurl, authoruserid, lastposteruserid;
	String sectionTitle;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threads);
		getBundle();
		loadThreads();
		populateTable();
		addButtons();
		idGenerator();

	}

	/**
	 * Gets the title of section from the sent bundle
	 */
	private void getBundle() {
		Bundle b = this.getIntent().getExtras();
		sectionTitle = b.getString("sectionTitle");
		sectionTitleView = (TextView) findViewById(R.id.sectionTitle);
		sectionTitleView.setOnClickListener(this);
		page = b.getInt("page");
		sectionTitleView.setText(sectionTitle + ", page " + page);
	}

	/**
	 * Load the threads from the Parser
	 */
	private void loadThreads() {
		try {
			Manager.getForum().getSectionByName(sectionTitle).addThreads(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
		title = Manager.getForum().getSectionByName(sectionTitle).getThreadsTitleArray();
		lastposter = Manager.getForum().getSectionByName(sectionTitle).getThreadsLastPosterArray();
		author = Manager.getForum().getSectionByName(sectionTitle).getThreadsAuthorArray();
		threadurl = Manager.getForum().getSectionByName(sectionTitle).getThreadsUrlArray();
		authoruserid = Manager.getForum().getSectionByName(sectionTitle).getThreadsAuthorIDArray();
		lastposteruserid = Manager.getForum().getSectionByName(sectionTitle).getThreadsLastPosterIDArray();
	}

	/**
	 * Populate the table with the thread
	 */
	private void populateTable() {
		for (int i = 0; i < Manager.getForum().getSectionByName(sectionTitle).getThreads().size(); i++) {
			addRow(title[i], lastposter[i], author[i]);
		}
	}

	/**
	 * Add a row to the table
	 * 
	 * @param a
	 *            thread title
	 * @param b
	 *            thread last poster
	 * @param c
	 *            thread author
	 */
	private void addRow(String a, String b, String c) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

		rowList.add(new TableRow(this));

		titleList.add(new TextView(this));
		titleList.get(counter).setText(a);
		titleList.get(counter).setOnClickListener(this);

		lastPosterList.add(new TextView(this));
		lastPosterList.get(counter).setText(b + "\n");
		lastPosterList.get(counter).setOnClickListener(this);

		authorList.add(new TextView(this));
		authorList.get(counter).setText(c);
		authorList.get(counter).setOnClickListener(this);

		rowList.get(counter).addView(titleList.get(counter));
		rowList.get(counter).addView(lastPosterList.get(counter));
		rowList.get(counter).addView(authorList.get(counter));

		table.addView(rowList.get(counter), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		counter++;
	}

	/**
	 * Adds a row of "buttons" to the end of the table
	 */
	private void addButtons() {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

		rowList.add(new TableRow(this));

		titleList.add(new TextView(this));
		titleList.get(titleList.size() - 1).setText("Previous Page");
		titleList.get(titleList.size() - 1).setTextSize(20);
		titleList.get(titleList.size() - 1).setTextColor(Color.RED);
		titleList.get(titleList.size() - 1).setOnClickListener(this);

		lastPosterList.add(new TextView(this));
		lastPosterList.get(lastPosterList.size() - 1).setText("" + "\n");
		lastPosterList.get(lastPosterList.size() - 1).setOnClickListener(this);

		authorList.add(new TextView(this));
		authorList.get(authorList.size() - 1).setText("Next Page");
		authorList.get(authorList.size() - 1).setTextSize(20);
		authorList.get(authorList.size() - 1).setTextColor(Color.RED);
		authorList.get(authorList.size() - 1).setOnClickListener(this);

		rowList.get(rowList.size() - 1).addView(titleList.get(titleList.size() - 1));
		rowList.get(rowList.size() - 1).addView(lastPosterList.get(lastPosterList.size() - 1));
		rowList.get(rowList.size() - 1).addView(authorList.get(authorList.size() - 1));

		table.addView(rowList.get(counter), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		counter++;
	}

	/**
	 * Generate ids for each element in the table
	 */
	private void idGenerator() {
		for (int i = 0; i < counter; i++) {
			titleList.get(i).setId(i);
			lastPosterList.get(i).setId(i + counter);
			authorList.get(i).setId(i + (2 * counter));
		}
	}

	/**
	 * Opens a thread in a new activity
	 * 
	 * @param j
	 *            position of thread in the list
	 */
	private void openThread(int j) {
		intent = new Intent(this, what.forum.PostListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		b.putInt("threadPosition", j);
		b.putString("threadTitle", title[j]);
		b.putString("threadAuthor", author[j]);
		b.putString("threadUrl", threadurl[j]);
		b.putInt("threadPage", 1);
		intent.putExtras(b);
		Log.v("TAG", sectionTitle + "," + j + "," + title[j] + "," + author[j] + "," + threadurl[j]);
		startActivityForResult(intent, 0);
	}

	/**
	 * Opens the user profile in a popup
	 * 
	 * @param j
	 * @param k
	 *            identifies between which userlist to choose from
	 * 
	 */
	private void openUser(int j, int k) {
		intent = new Intent(this, what.user.UserProfileActivity.class);
		Bundle b = new Bundle();
		if (k == 0) {
			b.putString("userName", lastposter[j]);
			b.putString("userID", lastposteruserid[j]);
		}
		if (k == 1) {
			b.putString("userName", author[j]);
			b.putString("userID", authoruserid[j]);
		}
		intent.putExtras(b);
		startActivityForResult(intent, 0);

	}

	/**
	 * Go to the next page
	 */
	private void nextPage() {
		intent = new Intent(this, what.forum.ThreadListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		// send the page number
		b.putInt("page", page + 1);
		intent.putExtras(b);
		startActivityForResult(intent, 0);
	}

	@Override
	public void onClick(View v) {

		for (int i = 0; i < (rowList.size() - 1); i++) {
			if (v.getId() == titleList.get(i).getId()) {
				openThread(i);
			}
			if (v.getId() == lastPosterList.get(i).getId()) {
				openUser(i, 0);
			}
			if (v.getId() == authorList.get(i).getId()) {
				openUser(i, 1);
			}
		}

		if (v.getId() == titleList.get(titleList.size() - 1).getId()) {
			finish();
		}
		if (v.getId() == authorList.get(authorList.size() - 1).getId()) {
			nextPage();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.threadlistmenu, menu);
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
