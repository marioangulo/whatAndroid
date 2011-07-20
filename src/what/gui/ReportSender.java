package what.gui;

import android.content.Context;

public class ReportSender {
	public ReportSender(Context context) {
		ErrorReporter errReporter = new ErrorReporter();
		errReporter.Init(context);
		errReporter.CheckErrorAndSendMail(context);
	}
}
