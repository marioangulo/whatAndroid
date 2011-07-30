package what.forum;

import java.util.ArrayList;

import what.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import api.parser.SectionParser;

/**
 * View of all the Sections
 * 
 * @author Tim
 * 
 */
public class SectionListActivity extends Activity implements OnClickListener {
	// TODO have api fully handle this
	private static String[] site = SectionParser.listToArray(SectionParser.parseSiteSections());
	private static String[] community = SectionParser.listToArray(SectionParser.parseCommunitySections());
	private static String[] music = SectionParser.listToArray(SectionParser.parseMusicSections());
	private static String[] help = SectionParser.listToArray(SectionParser.parseHelpSections());
	private static String[] trash = SectionParser.listToArray(SectionParser.parseTrashSections());
	private static String[] masterList = SectionParser.listToArray(SectionParser.parseMasterList());
	TextView forumsView;
	LinearLayout mainLayout, linearLayout;
	ScrollView scrollView;
	Intent intent;
	FrameLayout topLayout;
	ArrayList<TextView> sectionTitle = new ArrayList<TextView>();
	ArrayList<TextView> siteSection = new ArrayList<TextView>();
	ArrayList<TextView> communitySection = new ArrayList<TextView>();
	ArrayList<TextView> musicSection = new ArrayList<TextView>();
	ArrayList<TextView> helpSection = new ArrayList<TextView>();
	ArrayList<TextView> trashSection = new ArrayList<TextView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createLayout();
		populateView();
		idGenerator();

	}

	/**
	 * Create the base layout
	 */
	private void createLayout() {
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		mainLayout.setBackgroundColor(Color.DKGRAY);

		scrollView = new ScrollView(this);
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(linearLayout);

		topLayout = new FrameLayout(this);
		forumsView = new TextView(this);
		forumsView.setText("Forums");
		forumsView.setTextSize(22);
		topLayout.addView(forumsView);

		mainLayout.addView(topLayout);
		mainLayout.addView(scrollView);
		this.setContentView(mainLayout);
	}

	private void populateView() {
		sectionTitle.add(new TextView(this));
		sectionTitle.get(0).setText("Site");
		sectionTitle.get(0).setTextSize(20);
		linearLayout.addView(sectionTitle.get(0));

		for (int i = 0; i < site.length; i++) {
			siteSection.add(new TextView(this));
			siteSection.get(i).setText(site[i]);
			siteSection.get(i).setTextSize(16);
			siteSection.get(i).setPadding(45, 0, 0, 0);
			siteSection.get(i).setOnClickListener(this);
			linearLayout.addView(siteSection.get(i));
		}

		sectionTitle.add(new TextView(this));
		sectionTitle.get(1).setText("Community");
		sectionTitle.get(1).setTextSize(20);
		linearLayout.addView(sectionTitle.get(1));

		for (int i = 0; i < community.length; i++) {
			communitySection.add(new TextView(this));
			communitySection.get(i).setText(community[i]);
			communitySection.get(i).setTextSize(16);
			communitySection.get(i).setPadding(45, 0, 0, 0);
			communitySection.get(i).setOnClickListener(this);
			linearLayout.addView(communitySection.get(i));
		}

		sectionTitle.add(new TextView(this));
		sectionTitle.get(2).setText("Music");
		sectionTitle.get(2).setTextSize(20);
		linearLayout.addView(sectionTitle.get(2));

		for (int i = 0; i < music.length; i++) {
			musicSection.add(new TextView(this));
			musicSection.get(i).setText(music[i]);
			musicSection.get(i).setTextSize(16);
			musicSection.get(i).setPadding(45, 0, 0, 0);
			musicSection.get(i).setOnClickListener(this);
			linearLayout.addView(musicSection.get(i));
		}

		sectionTitle.add(new TextView(this));
		sectionTitle.get(3).setText("Help");
		sectionTitle.get(3).setTextSize(20);
		linearLayout.addView(sectionTitle.get(3));

		for (int i = 0; i < help.length; i++) {
			helpSection.add(new TextView(this));
			helpSection.get(i).setText(help[i]);
			helpSection.get(i).setTextSize(16);
			helpSection.get(i).setPadding(45, 0, 0, 0);
			helpSection.get(i).setOnClickListener(this);
			linearLayout.addView(helpSection.get(i));
		}

		sectionTitle.add(new TextView(this));
		sectionTitle.get(4).setText("Trash");
		sectionTitle.get(4).setTextSize(20);
		linearLayout.addView(sectionTitle.get(4));

		for (int i = 0; i < trash.length; i++) {
			trashSection.add(new TextView(this));
			trashSection.get(i).setText(trash[i]);
			trashSection.get(i).setTextSize(16);
			trashSection.get(i).setPadding(45, 0, 0, 0);
			trashSection.get(i).setOnClickListener(this);
			linearLayout.addView(trashSection.get(i));
		}
	}

	private void idGenerator() {
		int counter = 0;
		for (int i = 0; i < site.length; i++) {
			siteSection.get(i).setId(i);
			counter++;
		}
		for (int i = 0; i < community.length; i++) {
			communitySection.get(i).setId(counter);
			counter++;
		}
		for (int i = 0; i < music.length; i++) {
			musicSection.get(i).setId(counter);
			counter++;
		}
		for (int i = 0; i < help.length; i++) {
			helpSection.get(i).setId(counter);
			counter++;
		}
		for (int i = 0; i < trash.length; i++) {
			trashSection.get(i).setId(counter);
			counter++;
		}
	}

	/**
	 * Opens a section corresponding to its position in the list
	 * 
	 * @param j
	 *            position in list
	 */
	private void openSection(int j) {
		String sectionTitle = masterList[j];
		// start new intent and send sectiontitle in the bundle
		intent = new Intent(this, what.forum.ThreadListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		// send the page number
		b.putInt("page", 1);
		intent.putExtras(b);
		startActivityForResult(intent, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sectionlistmenu, menu);
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
	public void onClick(View v) {
		// this.setContentView(topLayout);
		openSection(v.getId());
	}
}
