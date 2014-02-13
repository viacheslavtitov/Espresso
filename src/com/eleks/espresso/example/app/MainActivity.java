package com.eleks.espresso.example.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity
{

	private String[] mArray;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initControls();
	}

	private void initControls()
	{
		// Main container
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame, MainFragment.newInstance(null));
		ft.commit();
		mArray = getResources().getStringArray(R.array.list_drawer_data);
		// DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
		ListView lvDrawerMenu = (ListView) findViewById(R.id.lvDrawerMenu);
		lvDrawerMenu.setAdapter(new ListAdapter(this, mArray));
		lvDrawerMenu.setOnItemClickListener(mOnItemClickListener);
	}

	private OnItemClickListener mOnItemClickListener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			CustomDialogFragment dialog = new CustomDialogFragment();
			dialog.setCancelable(false);
			Bundle bundle = new Bundle();
			bundle.putString(CustomDialogFragment.TEXT, mArray[position]);
			dialog.setArguments(bundle);
			dialog.show(getSupportFragmentManager(), CustomDialogFragment.TAG);
		}
	};

}
