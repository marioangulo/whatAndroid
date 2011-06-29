/**
 * 
 */
package what.gui;

import java.util.Stack;

/**
 * @author Tim
 *
 */
public class ActivityStack {
	private static Stack<Class<?>> stack = new Stack<Class<?>>();
	
	/**
	 * Get the whole stack
	 * @return stack
	 */
	public static Stack<Class<?>> getStack() {
		return stack;
	}
	/**
	 * Remove element from stop of stack
	 * @return element removed
	 */
	public static Object pop() {
		return stack.pop();
	}
	/**
	 * Add element to top of stack
	 * @param c Class to add
	 */
	public static void push(Class<?> c) {
		stack.push(c);
	}
	/**
	 * Looks at top of stack but doesn't remove anything
	 * @return element at top of stack
	 */
	public static Object peek() {
		return stack.peek();
	}
	/**
	 * Gets the current activity
	 * @return current activity
	 */
	public static Class<?> getCurrentActivity() {
		return what.forum.ThreadListActivity.class;
	}
}
