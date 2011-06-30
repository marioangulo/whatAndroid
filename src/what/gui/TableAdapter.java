/**
 * 
 */
package what.gui;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Unfinished class
 * @author Tim
 *
 */
public class TableAdapter implements OnClickListener {
	ArrayList rowsList;
	ArrayList columnsList;
	public TableAdapter(int columns) {
		columnsList = new ArrayList(columns);
	}
	public void addRows(ArrayList<String> rowElements, Context context) {
		ArrayList<TextView> textview = new ArrayList<TextView>();
		for(int i=0; i<rowElements.size(); i++) {
			textview.add(new TextView(context));
			textview.get(i).setText(rowElements.get(i));
			textview.get(i).setOnClickListener(this);
		}
	}
	private void decompose(ArrayList list) {
		
	}
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
