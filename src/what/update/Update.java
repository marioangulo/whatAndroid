package what.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;

public class Update extends Activity {
	public Update() {

	}

	public void checkForUpdate() {

	}

	public void writeToFile() throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(openFileOutput("version.txt", 0));
		// out.write(mySettings);
		out.close();
	}

	public void readFromFile() throws IOException {
		InputStream instream = openFileInput("version.txt");
		InputStreamReader inputreader = new InputStreamReader(instream);
		BufferedReader buffreader = new BufferedReader(inputreader);

		String line = null;

		while ((line == buffreader.readLine())) {
			// do something with the settings from the file
		}
		// close the file again
		instream.close();

	}
}
