package what.main;

import what.gui.ReportSender;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainPageActivity extends Activity {
	LinearLayout mainLayout, linearLayoutTop, linearLayoutBottom;
	ScrollView scrollViewTop, scrollViewBottom;
	FrameLayout titleTop, titleBottom;
	TextView textTop, textBottom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		@SuppressWarnings("unused")
		ReportSender reportSender = new ReportSender(this);
		createLayout();
		populateView();
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

	private void populateView() {
	}
}
