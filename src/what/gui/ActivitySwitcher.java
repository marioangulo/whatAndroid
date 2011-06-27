package what.gui;

import android.content.Context;
import android.content.Intent;

/**
 * Switch Acitivies
 * @author Tim
 *
 */
public class ActivitySwitcher {
	
	/**
	 * Switch Activity
	 * @param context context
	 * @param c class
	 */
	public static void switchActivity(Context context, Class<?> c) {
		@SuppressWarnings("unused")
		Intent myIntent = new Intent(context, c);
	}
}
