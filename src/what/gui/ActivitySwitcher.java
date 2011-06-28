package what.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Switch Acitivies
 * @author Tim
 *
 */
public class ActivitySwitcher {
	 Activity activity = new Activity();
	 Class<?> currentClass;
	
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
	public  void switchActivity(ActivityEnum e) {
		
	}
	/**
	 * Switch Activity
	 * @param context context
	 * @param c class
	 */
	public void switchActivity(Context context, Class<?> c) {
		@SuppressWarnings("unused")
		Intent myIntent = new Intent(context, c);
		activity.startActivity(myIntent);
		currentClass = c;
	}
	public  Class<?> getCurrentActivity() {
		return currentClass;
	}
}
