package what.main;

import java.io.IOException;
import java.util.ArrayList;

import what.gui.ReportSender;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import api.forum.Manager;

public class MainPageActivity extends Activity implements OnClickListener {
	LinearLayout mainLayout, linearLayoutTop, linearLayoutBottom;
	ScrollView scrollViewTop, scrollViewBottom;
	FrameLayout titleTop, titleBottom;
	TextView textTop, textBottom;
	ArrayList<TextView> threadListTop = new ArrayList<TextView>();
	String[] title, lastreadthreadurl;
	private int numberOfUnreadSubscribedThreads;
	private int counter;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		@SuppressWarnings("unused")
		ReportSender reportSender = new ReportSender(this);
		createLayout();
		loadSubscriptions();
		populateTopView();
		idGenerator();
	}

	private void createLayout() {
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);

		scrollViewTop = new ScrollView(this);
		linearLayoutTop = new LinearLayout(this);
		linearLayoutTop.setOrientation(LinearLayout.VERTICAL);
		scrollViewTop.addView(linearLayoutTop);

		titleTop = new FrameLayout(this);
		textTop = new TextView(this);
		textTop.setText("New Subscriptions");
		textTop.setTextSize(22);
		titleTop.addView(textTop);
		linearLayoutTop.addView(titleTop);

		scrollViewBottom = new ScrollView(this);
		linearLayoutBottom = new LinearLayout(this);
		linearLayoutBottom.setOrientation(LinearLayout.VERTICAL);
		scrollViewBottom.addView(linearLayoutBottom);

		titleBottom = new FrameLayout(this);
		textBottom = new TextView(this);
		textBottom.setText("New Forum Threads");
		textBottom.setTextSize(22);
		titleBottom.addView(textBottom);
		linearLayoutBottom.addView(titleBottom);

		mainLayout.addView(scrollViewTop);
		mainLayout.addView(scrollViewBottom);
		this.setContentView(mainLayout);
	}

	/**
	 * Load the subscriptions from the parser
	 */
	private void loadSubscriptions() {
		try {
			Manager.getSubscriptions().addThreads();
		} catch (IOException e) {
			e.printStackTrace();
		}
		title = Manager.getSubscriptions().getThreadsLastReadUrlArray();
		lastreadthreadurl = Manager.getSubscriptions().getThreadsLastReadUrlArray();
		numberOfUnreadSubscribedThreads = Manager.getSubscriptions().getNumberOfUnreadSubscribedThreads();
	}

	private void populateTopView() {
		for (int i = 0; i < numberOfUnreadSubscribedThreads; i++) {
			threadListTop.add(new TextView(this));
			threadListTop.get(i).setPadding(45, 0, 0, 0);
			threadListTop.get(i).setText(title[i]);
			threadListTop.get(i).setTextSize(17);
			threadListTop.get(i).setOnClickListener(this);

			linearLayoutTop.addView(threadListTop.get(i));
		}
	}

	/**
	 * Generate ids for each element in the table
	 */
	private void idGenerator() {
		for (int i = 0; i < counter; i++) {
			threadListTop.get(i).setId(i);
		}
	}

	private void openSubscribedThread(int i) {

	}

	@Override
	public void onClick(View v) {
		for (int i = 0; i < numberOfUnreadSubscribedThreads; i++) {
			if (v.getId() == threadListTop.get(i).getId()) {
				openSubscribedThread(i);
			}
		}
	}
}
