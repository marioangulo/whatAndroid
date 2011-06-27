package what.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
/**
 * Display an alert pop up
 * @author Tim
 *
 */
public class AlertPopUp {
	/**
	 * Display popup
	 * @param title title of popup
	 * @param message message of popup
	 * @param context
	 */
	public void display(String title, String message, Context context) {
		new AlertDialog.Builder(context)
	    .setTitle(title)
	    .setMessage(message)
	    .setPositiveButton("Open", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
	     })
	    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) { 
	        	arg0.dismiss();
	        }
	     })
	     .show();
	}
}
