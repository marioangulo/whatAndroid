package what.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import api.soup.MySoup;

public class Update extends Activity {
	private String version;
	private String site = "http://timmikeladze.github.com/whatAndroid/index.html";

	public Update(String version) throws IOException {
		this.version = version;
		checkForUpdate();
	}

	private void checkForUpdate() {
		if (!MySoup.getUpdateVersion(site).equalsIgnoreCase(version)) {
			displayAlert("", "Update available, would you like to install it?", this);
		}
	}

	private void openUpdate() {
		String url = MySoup.getUpdateLink(site);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
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
		new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				openUpdate();
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		}).show();
	}

	@SuppressWarnings("unused")
	private void writeToFile(String string) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(openFileOutput("version.txt", 0));
		out.write(string);
		out.close();
	}

	@SuppressWarnings("unused")
	private String readFromFile() throws IOException {
		InputStream instream = openFileInput("version.txt");
		InputStreamReader inputreader = new InputStreamReader(instream);
		BufferedReader buffreader = new BufferedReader(inputreader);

		String line = null;
		StringBuffer buffer = new StringBuffer();
		while ((line == buffreader.readLine())) {
			buffer.append(line);
		}
		instream.close();
		return buffer.toString();
	}
}
