package what.forum;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import what.parser.*;
import what.gui.*;
/**
 * Sections of the forum
 * @author Tim
 *
 */
public class SectionActivity extends ListActivity implements OnClickListener {

	String[] sections;
	Button optionsButton;
	Button backButton, forwardButton;
	Notification notif = new Notification();
	OptionsMenu optionsMenu = new OptionsMenu();
	

	public void onCreate(Bundle b) {
		super.onCreate(b);

		sections = SectionParser.parseSections();
		// Use your own layout and point the adapter to the UI elements which
		// contains the label
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.sections,
				R.id.list, sections));

		optionsButton = (Button) this.findViewById(R.id.OptionButton);
		optionsButton.setOnClickListener(this);
		
		backButton = (Button)this.findViewById(R.id.backButton);
		backButton.setOnClickListener(this);
		
		forwardButton = (Button)this.findViewById(R.id.forwardButton);
		forwardButton.setOnClickListener(this);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
		Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.OptionButton:
			optionsMenu.openOptionsMenu();
			break;
		}
	}
	
}
