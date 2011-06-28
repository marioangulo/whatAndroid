package what.gui;

import android.app.ExpandableListActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
/**
 * Options Menu
 * @author Tim	
 *
 */
public class OptionsMenu extends ExpandableListActivity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options, menu);
	    return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.close:
	        closeOptionsMenu();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
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
