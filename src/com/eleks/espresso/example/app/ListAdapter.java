package com.eleks.espresso.example.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{

	private Context mContext;
	private String[] mData;

	public ListAdapter(Context context, String[] data)
	{
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount()
	{
		return mData.length;
	}

	@Override
	public String getItem(int position)
	{
		return mData[position];
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		if (convertView != null)
		{
			view = convertView;
		}
		else
		{
			view = View.inflate(mContext, R.layout.drawer_list_item, null);
		}
		TextView tv = (TextView) view.findViewById(R.id.tvItem);
		tv.setText(getItem(position));
		return view;
	}

}
