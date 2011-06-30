package what.forum;
import java.util.ArrayList;
import java.util.Random;

import what.gui.Notification;
import what.gui.OptionsMenu;
import what.gui.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.util.Log;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;
import api.forum.Manager;

public class ThreadListActivity extends OptionsMenu implements OnClickListener {

	ArrayList<TableRow> row = new ArrayList<TableRow>();
	ArrayList<TextView> textview1 = new ArrayList<TextView>();
	ArrayList<TextView> textview2 = new ArrayList<TextView>();
	ArrayList<TextView> textview3 = new ArrayList<TextView>();
	Notification n = new Notification();
	int counter = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setup the layout
		setContentView(R.layout.threads);
		addButtons();

		for(int i=0; i<Manager.getForum().getSectionByName("The Lounge").getThreads().size(); i++) {
			populateTable(Manager.getForum().getSectionByName("The Lounge").getThreadsTitleArray()[i],
					Manager.getForum().getSectionByName("The Lounge").getThreadsLastPosterArray()[i],
					Manager.getForum().getSectionByName("The Lounge").getThreadsAuthorArray()[i]);
		}
	}
	public void populateTable(String a, String b, String c) {
		Random random = new Random(19580427);

		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		
		row.add(new TableRow(this));

		textview1.add(new TextView(this));
		textview1.get(counter).setText(a);
		textview1.get(counter).setOnClickListener(this);
		textview1.get(counter).setId(counter*random.nextInt(1000));

		textview2.add(new TextView(this));
		textview2.get(counter).setText(b +"\n");
		textview2.get(counter).setOnClickListener(this);
		textview2.get(counter).setId(counter*random.nextInt(1000));


		textview3.add(new TextView(this));
		textview3.get(counter).setText(c);
		textview3.get(counter).setOnClickListener(this);
		textview3.get(counter).setId(counter*random.nextInt(1000));

		row.get(counter).addView(textview1.get(counter));
		row.get(counter).addView(textview2.get(counter));
		row.get(counter).addView(textview3.get(counter));

		table.addView(row.get(counter),new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		counter++;
	}
	public void onClick(View v) {
		setMenuButtonsListener(v);
		for(int i=0; i<row.size(); i++) {
			if(v.getId() == textview1.get(i).getId()) {
				n.displayToast(textview1.get(i).getText().toString(), Toast.LENGTH_SHORT, this);
			}
			if(v.getId() == textview2.get(i).getId()) {
				n.displayToast(textview1.get(i).getText().toString(), Toast.LENGTH_SHORT, this);
			}
			if(v.getId() == textview3.get(i).getId()) {
				n.displayToast(textview1.get(i).getText().toString(), Toast.LENGTH_SHORT, this);
			}
		}
		/*if(v.getId() == textview.get(0).getId() ) {
			n.displayToast("Pressed 0", Toast.LENGTH_SHORT, this);
		}
		if(v.getId() == textview.get(1).getId() ) {
			n.displayToast("Pressed 1", Toast.LENGTH_SHORT, this);
		}
		if(v.getId() == textview2.get(2).getId() ) {
			n.displayToast("SUCESSSSSS", Toast.LENGTH_SHORT, this);
		}
		if(v.getId() == textview.get(3).getId() ) {
			n.displayToast("Pressed 3", Toast.LENGTH_SHORT, this);
		}*/
	}
}
