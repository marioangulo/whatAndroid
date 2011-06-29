package what.forum;
import java.util.ArrayList;

import what.gui.Notification;
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
 
public class tablelayout extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
 
    //initialize a button and a counter
    Button btn;
    int counter = 0;
    ArrayList<TableRow> row = new ArrayList<TableRow>();
    ArrayList<TextView> textview = new ArrayList<TextView>();
    ArrayList<CheckBox> checkbox = new ArrayList<CheckBox>();
    Notification n = new Notification();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setup the layout
        setContentView(R.layout.sectiontable);
 
        // add a click-listener on the button
        btn = (Button) findViewById(R.id.Button01);
        btn.setOnClickListener(this);        
    }
    public void addElements(int size) {
    	 TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
    	 for(int i=0; i<size; i++) {
    		 row.add(new TableRow(this));
    	  	 textview.add(new TextView(this));
        	 textview.get(i).setText("a test");
          	 textview.get(i).setOnClickListener(this);
          	 textview.get(i).setId(i);
        	 checkbox.add(new CheckBox(this)); 
    		 row.get(i).addView(textview.get(i));
        	 row.get(i).addView(checkbox.get(i)); 
    		 table.addView(row.get(i),new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	 }
    }
    public void onClick(View v) {
    	if(v.getId() ==  R.id.Button01 ) {
    		addElements(4);
    	}
    	if(v.getId() == textview.get(0).getId() ) {
    		n.displayToast("Pressed 0", Toast.LENGTH_LONG, this);
    	}
    	if(v.getId() == textview.get(1).getId() ) {
    		n.displayToast("Pressed 1", Toast.LENGTH_LONG, this);
    	}
    	if(v.getId() == textview.get(2).getId() ) {
    		n.displayToast("Pressed 2", Toast.LENGTH_LONG, this);
    	}
    	if(v.getId() == textview.get(3).getId() ) {
    		n.displayToast("Pressed 3", Toast.LENGTH_LONG, this);
    	}
    }
}
