package what.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Different types of notifications
 * 
 * @author Tim
 * 
 */
public class Notification {
	public static int LENGTH_LONG = Toast.LENGTH_LONG;
	public static int LENGTH_SHORT = Toast.LENGTH_SHORT;

	/**
	 * Display toast notification
	 * 
	 * @param message
	 *            message to display
	 * @param duration
	 *            duration to display for
	 * @param context
	 *            context
	 */
	public void displayToast(String message, int duration, Context context) {
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();

	}

	/**
	 * Display alert dialog
	 * 
	 * @param title
	 *            title of popup
	 * @param message
	 *            message of popup
	 * @param context
	 *            context
	 */
	public void displayAlert(String title, String message, Context context) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton("Open", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		}).setNegativeButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		}).show();
	}

	/**
	 * Display error dialog
	 * 
	 * @param title
	 * @param message
	 * @param context
	 */
	public void displayError(String title, String message, Context context) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNegativeButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		}).show();
	}
}
