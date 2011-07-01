package what.forum;
import java.io.IOException;
import java.util.ArrayList;
import what.gui.GUITools;
import what.gui.R;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;
import api.forum.Manager;
/**
 * View of all the threads in a section
 * @author Tim
 *
 */
public class ThreadListActivity extends GUITools implements OnClickListener {

	ArrayList<TableRow> rowList = new ArrayList<TableRow>();
	ArrayList<TextView> titleList = new ArrayList<TextView>();
	ArrayList<TextView> lastPosterList = new ArrayList<TextView>();
	ArrayList<TextView> authorList = new ArrayList<TextView>();
	private int counter;

	String[] title, lastposter, author;
	String sectionTitle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threads);
		addButtons();

		getSectionTitle();
		loadThreads();
		populateTable();
		idGenerator();
	}
	/**
	 * Gets the title of section from the sent bundle
	 */
	private void getSectionTitle() {
		Bundle b = this.getIntent().getExtras();
		sectionTitle = b.getString("sectionTitle");
	}
	/**
	 * Load the threads from the Parser
	 */
	private void loadThreads() {
		try {
			Manager.getForum().getSectionByName(sectionTitle).addThreads();
		} catch (IOException e) {
			e.printStackTrace();
		}
		title = Manager.getForum().getSectionByName(sectionTitle).getThreadsTitleArray();
		lastposter = Manager.getForum().getSectionByName(sectionTitle).getThreadsLastPosterArray();
		author = Manager.getForum().getSectionByName(sectionTitle).getThreadsAuthorArray();
	}
	/**
	 * Populate the table with the thread
	 */
	private void populateTable() {
		for(int i=0; i<Manager.getForum().getSectionByName(sectionTitle).getThreads().size(); i++) {
			addRow(title[i],lastposter[i],author[i]);
		}
	}
	/**
	 * Add a row to the table
	 * @param a thread title
	 * @param b thread last poster
	 * @param c thread author
	 */
	private void addRow(String a, String b, String c) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

		rowList.add(new TableRow(this));

		titleList.add(new TextView(this));
		titleList.get(counter).setText(a);
		titleList.get(counter).setOnClickListener(this);

		lastPosterList.add(new TextView(this));
		lastPosterList.get(counter).setText(b +"\n");
		lastPosterList.get(counter).setOnClickListener(this);

		authorList.add(new TextView(this));
		authorList.get(counter).setText(c);
		authorList.get(counter).setOnClickListener(this);

		rowList.get(counter).addView(titleList.get(counter));
		rowList.get(counter).addView(lastPosterList.get(counter));
		rowList.get(counter).addView(authorList.get(counter));

		table.addView(rowList.get(counter),new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		counter++;
	}
	/**
	 * Generate ids for each element in the table
	 */
	private void idGenerator() {
		for(int i=0;i<counter;i++) {
			titleList.get(i).setId(i);
			lastPosterList.get(i).setId(i+counter);
			authorList.get(i).setId(i+(2*counter));
		}
	}
	public void onClick(View v) {
		setMenuButtonsListener(v);
		for(int i=0; i<rowList.size(); i++) {
			if(v.getId() == titleList.get(i).getId()) {
				notification.displayToast(Integer.toString(titleList.get(i).getId()), Toast.LENGTH_SHORT, this);
			}
			if(v.getId() == lastPosterList.get(i).getId()) {
				notification.displayToast(Integer.toString(lastPosterList.get(i).getId()), Toast.LENGTH_SHORT, this);
			}
			if(v.getId() == authorList.get(i).getId()) {
				notification.displayToast(Integer.toString(authorList.get(i).getId()), Toast.LENGTH_SHORT, this);
			}
		}
	}
}
