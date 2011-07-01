package what.forum;
import what.gui.ListAdapter;
import what.gui.GUITools;
import what.gui.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import api.parser.SectionParser;

/**
 * View of all the Sections 
 * @author Tim
 *
 */
public class SectionListActivity extends GUITools implements OnClickListener   {

	//TODO have api fully handle this
	private static String[] site = SectionParser.parseSiteSections();
	private static String[] community = SectionParser.parseCommunitySections();
	private static String[] music = SectionParser.parseMusicSections();
	private static String[] help = SectionParser.parseHelpSections();
	private static String[] trash = SectionParser.parseTrashSections();
	private static String[] masterList = SectionParser.getMasterList();
	Intent intent;
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.section);

		adapter.addSection("Site",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,site));
		adapter.addSection("Community",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,community));
		adapter.addSection("Music",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,music));
		adapter.addSection("Help",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,help));
		adapter.addSection("Trash",	new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trash));
		setListAdapter(adapter);
		
		addButtons();

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		openSection(position);
		super.onListItemClick(l, v, position, id);
	}
	/**
	  Opens a section corresponding to it's position in the list
	  @param j position in list
	 */
	private void openSection(int j) {
		//TODO make this more beutiful
		String sectionTitle = null;
		if(j < 7) {
			sectionTitle = masterList[j-1];
		}
		if(j > 7 && j < 14) {
			sectionTitle = masterList[j-2];
		}
		if(j > 14 && j < 20) {
			sectionTitle = masterList[j-3];
		}
		if(j > 20 && j < 24) {
			sectionTitle = masterList[j-4];
		}
		if(j > 24) {
			sectionTitle = masterList[j-5];
		}
		for(int i=0;i<masterList.length;i++) {
			Log.v("TAG",masterList[i]);
		}
		//start new intent and send sectiontitle in the bundle
		intent = new Intent(this,what.forum.ThreadListActivity.class);
		Bundle b = new Bundle();
		b.putString("sectionTitle", sectionTitle);
		intent.putExtras(b);
		startActivityForResult(intent, 0);
	}
	
	ListAdapter adapter=new ListAdapter() {
		protected View getHeaderView(String caption, int index, View convertView, ViewGroup parent) {
			TextView result=(TextView)convertView;

			if (convertView==null) {
				result=(TextView)getLayoutInflater().inflate(R.layout.header,null);
			}

			result.setText(caption);
			return(result);
		}
	};
}
