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
import api.parser.SectionParser;
import api.soup.MySoup;


public class SectionListActivity extends OptionsMenu implements OnClickListener   {

	//TODO have api fully handle this
	private static String[] site = SectionParser.parseSiteSections();
	private static String[] community = SectionParser.parseCommunitySections();
	private static String[] music = SectionParser.parseMusicSections();
	private static String[] help = SectionParser.parseHelpSections();
	private static String[] trash = SectionParser.parseTrashSections();

	Button optionsButton;
	Button backButton, forwardButton;
	Intent threadIntent;
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.section);

		adapter.addSection("Site",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,site));
		adapter.addSection("Community",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,community));
		adapter.addSection("Music",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,music));
		adapter.addSection("Help",new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,help));
		adapter.addSection("Trash",	new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trash));
		setListAdapter(adapter);

		optionsButton = (Button)this.findViewById(R.id.menuButton);
		optionsButton.setOnClickListener(this);

		backButton = (Button)this.findViewById(R.id.backButton);
		backButton.setOnClickListener(this);

		forwardButton = (Button)this.findViewById(R.id.forwardButton);
		forwardButton.setOnClickListener(this);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		/*Intent prefIntent = new Intent(this,what.login.WhatAndroidActivity.class);
		startActivity(prefIntent);*/
		openSection(position);
		super.onListItemClick(l, v, position, id);
	}
	/**
	 * Opens a section corresponding to it's position in the list
	 * @param j position in list
	 */
	private void openSection(int j) {
	//	String list[] = Manager.getForum().getSections().toArray().
		LinkedList<?> list = Manager.getForum().getSections();
		for(int i=0; i<list.size(); i++) {
			if((j-1)==i) {
				//Log.e("tag" ,MySoup.scrape("http://what.cd/forums.php?action=viewforum&forumid=7").text());
				Log.e("TAG", Manager.getForum().getSections().toString());
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

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.menuButton:
			this.openOptionsMenu();
			break;
		case R.id.backButton:
			finish();
			break;
		case R.id.forwardButton:
			break;
		}
	}
}
