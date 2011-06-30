package what.gui;

import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Options Menu
 * @author Tim	
 *
 */
public class GUITools extends ListActivity implements OnClickListener {
	//TODO rename
	Button optionsButton;
	Button backButton, forwardButton;

	public Notification notification = new Notification();
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.closeItem:
			closeOptionsMenu();
			break;
		case R.id.searchItem:
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
	/**
	 * Adds buttons
	 */
	public void addButtons() {
		optionsButton = (Button)this.findViewById(R.id.menuButton);
		optionsButton.setOnClickListener(this);

		backButton = (Button)this.findViewById(R.id.backButton);
		backButton.setOnClickListener(this);

		forwardButton = (Button)this.findViewById(R.id.forwardButton);
		forwardButton.setOnClickListener(this);
	}
	public void setMenuButtonsListener(View v) {
		switch(v.getId()) {
		case R.id.menuButton:
			this.openOptionsMenu();
			break;
		case R.id.backButton:
			finish();
			break;
		case R.id.forwardButton:
			/*Intent intent;
			Class<?> c = ActivityStack.peek();
			intent = new Intent(this,c);
			startActivity(intent);*/
			break;
		}
	}
	@Override
	public void onClick(View v) {
		setMenuButtonsListener(v);
	}
}