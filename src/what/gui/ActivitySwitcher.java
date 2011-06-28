package what.gui;

import android.content.Context;
import android.content.Intent;

/**
 * Switch Acitivies
 * @author Tim
 *
 */
public class ActivitySwitcher {
	static Class<?> currentClass;
	
	public enum ActivityEnum {
		LOGIN, SECTION,
	}
	ActivityEnum activityEnum;
	public void setClass() {
		switch(activityEnum) {
		case LOGIN:
			break;
		case SECTION:
			break;
		}
	}
	public static void switchActivity(ActivityEnum e) {
		
	}
	/**
	 * Switch Activity
	 * @param context context
	 * @param c class
	 */
	public static void switchActivity(Context context, Class<?> c) {
		@SuppressWarnings("unused")
		Intent myIntent = new Intent(context, c);
		currentClass = c;
	}
	public static Class<?> getCurrentActivity() {
		return currentClass;
	}
}
