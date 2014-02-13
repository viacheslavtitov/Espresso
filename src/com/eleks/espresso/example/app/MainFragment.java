package com.eleks.espresso.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainFragment extends Fragment
{

	private EditText mEtEmail = null;
	private EditText mEtMessage = null;

	public static MainFragment newInstance(Bundle bundle)
	{
		MainFragment fragment = new MainFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.main_fragment_layout, null);
		initControls(view);
		return view;
	}

	private void initControls(View view)
	{
		if (view != null)
		{
			Button btn = (Button) view.findViewById(R.id.btnSend);
			btn.setOnClickListener(mOnClickListener);
			mEtEmail = (EditText) view.findViewById(R.id.etEmail);
			mEtMessage = (EditText) view.findViewById(R.id.etMessage);
		}
	}

	private OnClickListener mOnClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			String email = null;
			String message = null;
			if (mEtEmail != null)
			{
				email = mEtEmail.getText().toString();
			}
			if (mEtMessage != null)
			{
				message = mEtMessage.getText().toString();
			}
			try
			{
				if (WebServiceManager.getInstance().sendMessage(email, message))
				{

				}
				else
				{
					throw new Exception("Message is not send");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};

}
