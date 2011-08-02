package what.forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import api.parser.SectionParser;
import what.gui.R;
import what.gui.ReportSender;

import java.util.ArrayList;

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
	LinearLayout scrollLayout;
	Intent intent;
	ArrayList<TextView> sectionTitle = new ArrayList<TextView>();
	ArrayList<TextView> siteSection = new ArrayList<TextView>();
	ArrayList<TextView> communitySection = new ArrayList<TextView>();
	ArrayList<TextView> musicSection = new ArrayList<TextView>();
	ArrayList<TextView> helpSection = new ArrayList<TextView>();
	ArrayList<TextView> trashSection = new ArrayList<TextView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		@SuppressWarnings("unused")
		ReportSender sender = new ReportSender(this);
		createLayout();
		populateView();
		idGenerator();
	}

	/**
	 * Create the base layout
	 */
	private void createLayout() {
        setContentView(R.layout.sections);
        scrollLayout = (LinearLayout) findViewById(R.id.scrollLayout);
	}

	private void populateView() {
        // Site Section
        //
        // Title
		sectionTitle.add((TextView)getLayoutInflater().inflate(R.layout.forum_section_title, null));
		sectionTitle.get(0).setText("Site");
		scrollLayout.addView(sectionTitle.get(0));
        // Forums
		for (int i = 0; i < site.length; i++) {
            if (i % 2 == 0)
			    siteSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_even, null));
            else
                siteSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_odd, null));
			siteSection.get(i).setText(site[i]);
			siteSection.get(i).setOnClickListener(this);
			scrollLayout.addView(siteSection.get(i));
		}
        // Spacer
        scrollLayout.addView((TextView)getLayoutInflater().inflate(R.layout.forum_section_spacer, null));

        // Community Section
        //
        // Title
		sectionTitle.add((TextView)getLayoutInflater().inflate(R.layout.forum_section_title, null));
		sectionTitle.get(1).setText("Community");
		scrollLayout.addView(sectionTitle.get(1));
        // Forums
		for (int i = 0; i < community.length; i++) {
			if (i % 2 == 0)
			    communitySection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_even, null));
            else
                communitySection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_odd, null));
			communitySection.get(i).setText(community[i]);
			communitySection.get(i).setOnClickListener(this);
			scrollLayout.addView(communitySection.get(i));
		}
        // Spacer
        scrollLayout.addView((TextView)getLayoutInflater().inflate(R.layout.forum_section_spacer, null));

        // Music Section
        //
        // Title
		sectionTitle.add((TextView)getLayoutInflater().inflate(R.layout.forum_section_title, null));
		sectionTitle.get(2).setText("Music");
		scrollLayout.addView(sectionTitle.get(2));
        // Forums
		for (int i = 0; i < music.length; i++) {
			if (i % 2 == 0)
			    musicSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_even, null));
            else
                musicSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_odd, null));
			musicSection.get(i).setText(music[i]);
			musicSection.get(i).setOnClickListener(this);
			scrollLayout.addView(musicSection.get(i));
		}
        // Spacer
        scrollLayout.addView((TextView)getLayoutInflater().inflate(R.layout.forum_section_spacer, null));

        // Help Section
        //
        // Title
		sectionTitle.add((TextView)getLayoutInflater().inflate(R.layout.forum_section_title, null));
		sectionTitle.get(3).setText("Help");
		scrollLayout.addView(sectionTitle.get(3));
        // Forums
		for (int i = 0; i < help.length; i++) {
			if (i % 2 == 0)
			    helpSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_even, null));
            else
                helpSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_odd, null));
			helpSection.get(i).setText(help[i]);
			helpSection.get(i).setOnClickListener(this);
			scrollLayout.addView(helpSection.get(i));
		}
        // Spacer
        scrollLayout.addView((TextView)getLayoutInflater().inflate(R.layout.forum_section_spacer, null));

        // Trash Section
        //
        // Title
		sectionTitle.add((TextView)getLayoutInflater().inflate(R.layout.forum_section_title, null));
		sectionTitle.get(4).setText("Trash");
		scrollLayout.addView(sectionTitle.get(4));
        // Forums
		for (int i = 0; i < trash.length; i++) {
            if (i % 2 == 0)
			    trashSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_even, null));
            else
                trashSection.add((TextView)getLayoutInflater().inflate(R.layout.forum_name_odd, null));
			trashSection.get(i).setText(trash[i]);
			trashSection.get(i).setOnClickListener(this);
			scrollLayout.addView(trashSection.get(i));
		}
        // Spacer
        scrollLayout.addView((TextView)getLayoutInflater().inflate(R.layout.forum_section_spacer, null));
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
		case R.id.updateItem:
			intent = new Intent(this, what.update.Update.class);
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
