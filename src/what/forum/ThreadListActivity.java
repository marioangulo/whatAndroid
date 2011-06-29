/***
	Copyright (c) 2008-2011 CommonsWare, LLC
	Licensed under the Apache License, Version 2.0 (the "License"); you may not
	use this file except in compliance with the License. You may obtain	a copy
	of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
	by applicable law or agreed to in writing, software distributed under the
	License is distributed on an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
	OF ANY KIND, either express or implied. See the License for the specific
	language governing permissions and limitations under the License.

	From _The Busy Coder's Guide to Advanced Android Development_
		http://commonsware.com/AdvAndroid
 */

package what.forum;
import java.util.LinkedList;

import what.gui.ListAdapter;
import what.gui.OptionsMenu;
import what.gui.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import api.forum.Manager;


public class ThreadListActivity extends OptionsMenu implements OnClickListener   {
	Intent threadIntent;
	private String[] createItems() {
		String[] threadsList = Manager.getForum().getSectionByName("The Lounge").getThreadsArray();
		return threadsList;
	}
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.threads);

		adapter.addSection("Threads",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,createItems()));
		setListAdapter(adapter);
		
		addButtons();
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		openSection(position);
		super.onListItemClick(l, v, position, id);
	}
	/**
	 * Opens a section corresponding to it's position in the list
	 * @param j position in list
	 */
	private void openSection(int j) {
		LinkedList<?> list = Manager.getForum().getSections();
		for(int i=0; i<list.size(); i++) {
			if((j-1)==i) {
			}
		}
	}
	ListAdapter adapter=new ListAdapter() {
		protected View getHeaderView(String caption, int index, View convertView, ViewGroup parent) {
			TextView result=(TextView)convertView;

			if (convertView==null) {
				result=(TextView)getLayoutInflater().inflate(R.layout.header,null);
			}

			result.setText(caption);
			return(result);
		}
	};
}
